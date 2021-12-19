package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    private final double radius; // radius from origin to point
    private final double theta; //angle
    private final double phi; //azimuth

    public static HashMap<Integer, SphericCoordinate> existingSphericCoordinates = new HashMap<>();
    /**
     * if the coordinate exists in hashmap, fetch it, otherwise create and store a new one
     * #TODO: test if instance gets stored as a Spheric and if that's an issue???
     */
    public static SphericCoordinate fetchSphericCoordinate(double radius, double theta, double phi){

        try{
            SphericCoordinate instance = new SphericCoordinate(radius,theta,phi);
            int hash = instance.hashCode();
            if (existingSphericCoordinates.containsKey(hash)){
                return existingSphericCoordinates.get(hash);
            }else{
                existingSphericCoordinates.put(hash,instance);
                return instance;
            }
        }catch(Exception e){
            throw new RuntimeException("Spheric Coordinate could not be fetched",e);
        }

    }

    /**
     * convenience fetcher
     *
     */
    public static SphericCoordinate fetchSphericCoordinate() {
        return fetchSphericCoordinate(0,0,0);
    }

    /**
     *
     * @methodtype constructor
     **/
    private SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
        assertClassInvariants(); //at the end, otherwise would have to check them one by one as preconditions and I decided this is sufficient.
    }

    public CartesianCoordinate asCartesianCoordinate(){

        assertClassInvariants();

        double x;
        double y;
        double z;

        x = this.radius * Math.cos(this.phi) * Math.sin(this.theta);
        y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        z = this.radius * Math.cos(this.theta);

        CartesianCoordinate temp = CartesianCoordinate.fetchCartesianCoordinate(x,y,z);
        temp.assertClassInvariants();
        return temp;

    }
    public SphericCoordinate asSphericCoordinate(){
        return this;
    }

    public boolean isEqual(Coordinate coordinate) throws Exception{
        assertNotNull(coordinate);
        assertClassInvariants();
        try {
            if (getClass() == coordinate.getClass()) {
                SphericCoordinate sCoord = coordinate.asSphericCoordinate();
                sCoord.assertClassInvariants();
                if (Math.abs(this.getRadius() - sCoord.getRadius()) > getDELTA() ||
                        Math.abs(this.getTheta() - sCoord.getTheta()) > getDELTA() ||
                        Math.abs(this.getPhi() - sCoord.getPhi()) > getDELTA()) {
                    return false;
                } else {
                    return true;
                }
            } else if (coordinate instanceof CartesianCoordinate) {
                CartesianCoordinate cCoord = this.asCartesianCoordinate();
                return cCoord.isEqual(coordinate);
            } else {
                return false;
            }
        }catch(Exception e){
            throw new Exception("Error at coordinate comparison", e);
        }
    }

    protected double doGetCentralAngle(SphericCoordinate otherCoord){
        assertClassInvariants();
        otherCoord.assertClassInvariants();

        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(this.getTheta())) * Math.sin(Math.toRadians(otherCoord.getTheta())) +
                Math.cos(Math.toRadians(this.getTheta())) * Math.cos(Math.toRadians(otherCoord.getTheta())) * Math.cos((Math.toRadians(this.getPhi() - otherCoord.getPhi())))));
        assertLessEqual2Pi(centralAngle);
        assertGreaterZero(centralAngle);
        return centralAngle; //should be rounded properly on a 64 bit double.
    }
    public int hashCode() {
        return this.asCartesianCoordinate().hashCode();
    }


    public double getRadius() {
        return radius;
    }

    public double getTheta() {
        return theta;
    }

    public double getPhi() {
        return phi;
    }

    protected void assertLessEqualPi(double x){
        if (x > Math.PI){
            throw new IllegalArgumentException ("Parameter " + x + " should not be greater than Pi");
        }
    }
    protected void assertLessEqual2Pi(double x){
        if (x > 2 * Math.PI){
            throw new IllegalArgumentException("Parameter " + x + " should not be greater than 2 * Pi");
        }
    }

    @Override
    protected void assertClassInvariants() {
        assertValidDouble(radius);
        assertValidDouble(phi);
        assertValidDouble(theta);

        assertGreaterEqualZero(radius); //radius cannot be negative

        assertGreaterZero(theta);
        assertLessEqualPi(theta);

        assertGreaterZero(phi);
        assertLessEqual2Pi(phi); //phi should be less or equal to 2*Pi radians
    }
}