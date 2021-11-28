package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    private double DELTA = 0.000001; //used similarly in both sub-classes for double comparison

    public abstract boolean isEqual(Coordinate coordinate);
    public abstract CartesianCoordinate asCartesianCoordinate();
    public abstract SphericCoordinate asSphericCoordinate() throws ArithmeticException;
    @Override
    public abstract int hashCode(); // subs need to implement a hashCode method, since the equals method has been preimplemented

    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate thisCoordinate = this.asCartesianCoordinate(); // both Coordinates needed to be forced to cartesian
        CartesianCoordinate otherCoordinate = coordinate.asCartesianCoordinate();

        double powX = Math.pow((thisCoordinate.getX()-otherCoordinate.getX()),2);
        double powY = Math.pow((thisCoordinate.getY()-otherCoordinate.getY()),2);
        double powZ = Math.pow((thisCoordinate.getZ()-otherCoordinate.getZ()),2);
        double distance = Math.sqrt(powX+powY+powZ);
        return distance;
    }

    public double getCentralAngle(Coordinate coordinate){
        SphericCoordinate thisCoord = this.asSphericCoordinate();
        SphericCoordinate otherCoord = coordinate.asSphericCoordinate();
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(thisCoord.getTheta())) * Math.sin(Math.toRadians(otherCoord.getTheta())) +
                Math.cos(Math.toRadians(thisCoord.getTheta())) * Math.cos(Math.toRadians(otherCoord.getTheta())) * Math.cos((Math.toRadians(thisCoord.getPhi() - otherCoord.getPhi())))));
        return centralAngle; //should be rounded properly on a 64 bit double.
    }

    public boolean equals(Object compare){
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

}
