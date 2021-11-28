package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    private double radius; // radius from origin to point
    private double theta; //angle
    private double phi; //azimuth

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

    public boolean isEqual(Coordinate coordinate){
        if (getClass() == coordinate.getClass() ) {
            SphericCoordinate sCoord = coordinate.asSphericCoordinate();
            if (Math.abs(this.getRadius() - sCoord.getRadius())>getDELTA() ||
                    Math.abs(this.getTheta() - sCoord.getTheta())>getDELTA() ||
                    Math.abs(this.getPhi() - sCoord.getPhi())>getDELTA()){
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
    public int hashCode() {
        return Objects.hash(getRadius(), getTheta(), getPhi());
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


}
