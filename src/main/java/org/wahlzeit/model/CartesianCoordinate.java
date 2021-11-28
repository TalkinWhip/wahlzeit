package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate implements Coordinate {
    private double x;
    private double y;
    private double z;
    private double DELTA = 0.000001;

    /**
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     *
     * @methodtype constructor
     * @methodproperty convenience
     */
    // alternative constructor, since location has 0..1 multiplicity
    public CartesianCoordinate() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     *
     * @methodype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException{
        double radius;
        double theta;
        double phi;

        radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        theta = Math.acos((this.z/(radius)));
        if (this.x > 0){
            phi = Math.atan((this.y/this.x));
        } else if (this.x < 0){
            phi = Math.atan((this.y/this.x))+Math.PI;
        } else {
            phi = Math.PI/2;
        }

        SphericCoordinate temp = new SphericCoordinate(radius, theta, phi);
        return temp;
    }

    /**
     *
     * @methodtype boolean-query
     */
    public boolean isEqual(Coordinate coordinate){
        if (getClass() == coordinate.getClass()) {
            CartesianCoordinate cCoord = coordinate.asCartesianCoordinate();
            if (Math.abs(this.getX() - cCoord.getX())>DELTA ||
                    Math.abs(this.getY() - cCoord.getY())>DELTA ||
                    Math.abs(this.getZ() - cCoord.getZ())>DELTA) {
                return false;
            } else {
                return true;
            }
        } else if (coordinate instanceof SphericCoordinate){
            SphericCoordinate sCoord = this.asSphericCoordinate();
            return sCoord.isEqual(coordinate);
        } else {
            return false;
        }
    }
    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cCoordinate = coordinate.asCartesianCoordinate();
        return this.getDistance(cCoordinate);
    }
    public double getCentralAngle(Coordinate coordinate){
        SphericCoordinate sCoord = this.asSphericCoordinate();
        return sCoord.getCentralAngle(coordinate);
    }

    /**
     *
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     *
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     *
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     *
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }
    /**
     *
     * @methodtype boolean-query
     */
    //deep compare of class
    @Override
    public boolean equals(Object compare){
        if (this.getClass() == compare.getClass()) {
            CartesianCoordinate loc = (CartesianCoordinate) compare;
            return this.isEqual(loc);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }
    /**
     *
     * @methodtype get
     */
    public double getDistance(CartesianCoordinate compare){
        double powX = Math.pow((this.getX()-compare.getX()),2);
        double powY = Math.pow((this.getY()-compare.getY()),2);
        double powZ = Math.pow((this.getZ()-compare.getZ()),2);
        double distance = Math.sqrt(powX+powY+powZ);
        return distance;
    }
}
