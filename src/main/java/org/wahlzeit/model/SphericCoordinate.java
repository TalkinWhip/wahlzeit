package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements Coordinate{

    private double radius; // radius from origin to point
    private double theta; //angle
    private double phi; //azimuth
    private double DELTA = 0.000001;

    /**
    *
    * @methodtype constructor
     **/
    public SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }
    /**
     *
     * @methodtype constructor
     * @methodproperty convenience
     **/
    public SphericCoordinate(){
        this.radius = 0;
        this.theta = 0;
        this.phi = 0;
    }



    public CartesianCoordinate asCartesianCoordinate(){
        double x;
        double y;
        double z;

        x = this.radius * Math.cos(this.phi) * Math.sin(this.theta);
        y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        z = this.radius * Math.cos(this.theta);

        CartesianCoordinate temp = new CartesianCoordinate(x,y,z);
        return temp;

    }
    public SphericCoordinate asSphericCoordinate(){
        return this;
    }

    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cCoord = this.asCartesianCoordinate();
        return cCoord.getCartesianDistance(coordinate);
    }
    public double getCentralAngle(Coordinate coordinate){
        SphericCoordinate sCoord = coordinate.asSphericCoordinate();
        return this.doGetAngle(sCoord);
    }

    public boolean isEqual(Coordinate coordinate){
        if (getClass() == coordinate.getClass() ) {
            SphericCoordinate sCoord = coordinate.asSphericCoordinate();
            if (Math.abs(this.getRadius() - sCoord.getRadius())>DELTA ||
                    Math.abs(this.getTheta() - sCoord.getTheta())>DELTA ||
                    Math.abs(this.getPhi() - sCoord.getPhi())>DELTA){
                return false;
            }
            else { return true; }
        } else if (coordinate instanceof CartesianCoordinate){
            CartesianCoordinate cCoord = this.asCartesianCoordinate();
            return cCoord.isEqual(coordinate);
        } else {
            return false;
        }
    }

    public double doGetAngle(SphericCoordinate compare){
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(this.theta)) * Math.sin(Math.toRadians(compare.getTheta())) +
                Math.cos(Math.toRadians(this.theta)) * Math.cos(Math.toRadians(compare.getTheta())) * Math.cos((Math.toRadians(this.phi - compare.getPhi())))));
        return centralAngle; //should be rounded properly on a 64 bit double.
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SphericCoordinate that = (SphericCoordinate) o;
        return Double.compare(that.getRadius(), getRadius()) == 0 && Double.compare(that.getTheta(), getTheta()) == 0 && Double.compare(that.getPhi(), getPhi()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadius(), getTheta(), getPhi());
    }
}
