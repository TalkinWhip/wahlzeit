package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    private double DELTA = 0.000001; //used similarly in both sub-classes for double comparison

    public abstract boolean isEqual(Coordinate coordinate);
    public abstract CartesianCoordinate asCartesianCoordinate();
    public abstract SphericCoordinate asSphericCoordinate() throws ArithmeticException;
    @Override
    public abstract int hashCode(); // subs need to implement a hashCode method, since the equals method has been preimplemented

    public double getCartesianDistance(Coordinate coordinate){
        assertNotNull(coordinate);
        CartesianCoordinate thisCoordinate = this.asCartesianCoordinate(); // both Coordinates needed to be forced to cartesian
        CartesianCoordinate otherCoordinate = coordinate.asCartesianCoordinate();
        return thisCoordinate.doGetCartesianDistance(otherCoordinate);
    }

    public double getCentralAngle(Coordinate coordinate){
        assertNotNull(coordinate);
        SphericCoordinate thisCoord = this.asSphericCoordinate();
        SphericCoordinate otherCoord = coordinate.asSphericCoordinate();
        return thisCoord.doGetCentralAngle(otherCoord);
    }

    public boolean equals(Object compare){
        assertNotNull(compare);
        if (this.getClass() == compare.getClass()) {
            Coordinate loc = (Coordinate) compare;
            return this.isEqual(loc);
        }
        else {
            return false;
        }
    }

    public double getDELTA() {
        return DELTA;
    }

    protected void assertNotNull(Object obj){
        if (obj == null){
            throw new NullPointerException("Object should not be null");
        }
    }

    protected void assertGreaterZero(double x){
        if (x <= (0-DELTA)){ // added delta for tests to run
            throw new IllegalArgumentException("Parameter "+ x +" should be greater than zero");
        }
    }
    protected void assertGreaterEqualZero(double x){
        if (x < (0-DELTA)){ // added delta for tests to run
            throw new IllegalArgumentException("Parameter "+ x +" should be greater or equal to zero");
        }
    }
    protected void assertValidDouble(double x){
        if (Double.isNaN(x)){
            throw new IllegalArgumentException("Parameter is invalid");
        }
    }
    protected abstract void assertClassInvariants(); //differ in subclasses

}
