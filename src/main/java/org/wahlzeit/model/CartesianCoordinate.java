package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.util.HashMap;
import java.util.Objects;

@PatternInstance(
        patternName = "Bridge",
        participants = { "ConcreteImplementorA" }
)

public class CartesianCoordinate extends AbstractCoordinate {
    private final double x;
    private final double y;
    private final double z;

    public static HashMap<Integer, CartesianCoordinate> existingCartesianCoordinates = new HashMap<>();

    /**
     * if the coordinate exists in hashmap, fetch it, otherwise create and store a new one
     * #TODO: test if instance gets stored as a Cartesian and if that's an issue???
     */
    @PatternInstance(
            patternName = "Flyweight",
            participants = { "FlyweightFactory", "Flyweight" }
    )
    public static synchronized CartesianCoordinate ensureCartesianCoordinate(double x, double y, double z){

        try{
            CartesianCoordinate instance = new CartesianCoordinate(x,y,z);
            int hash = instance.hashCode();
            if (existingCartesianCoordinates.containsKey(hash)){
                return existingCartesianCoordinates.get(hash);
            }else{
                existingCartesianCoordinates.put(hash,instance);
                return instance;
            }
        }catch(Exception e){
            throw new RuntimeException("Cartesian Coordinate could not be fetched",e);
        }

    }

    /**
     * convenience fetcher
     *
     */
    public static CartesianCoordinate ensureCartesianCoordinate() {
        return ensureCartesianCoordinate(0,0,0);
    }

    /**
     * @methodtype constructor
     * private --> immutable value object
     */
    private CartesianCoordinate(double x, double y, double z) {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    /**
     * @methodype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    public SphericCoordinate asSphericCoordinate() throws Exception {
        double radius;
        double theta;
        double phi;

        radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        try {
            theta = Math.acos((this.z / (radius)));
            if (this.x > 0) {
                phi = Math.atan((this.y / this.x));
            } else if (this.x < 0) {
                phi = Math.atan((this.y / this.x)) + Math.PI;
            } else {
                phi = Math.PI / 2;
            }
            SphericCoordinate temp = SphericCoordinate.ensureSphericCoordinate(radius, theta, phi);
            temp.assertClassInvariants();
            return temp;
        }catch (Exception e){
            throw new Exception("Error in the conversion as a Spheric Coordinate", e);
        }
    }

    /**
     * @methodtype boolean-query
     */
    public boolean isEqual(Coordinate coordinate) throws Exception{
        assertNotNull(coordinate);
        assertClassInvariants();
        try {
            if (getClass() == coordinate.getClass()) {
                CartesianCoordinate cCoord = coordinate.asCartesianCoordinate();
                cCoord.assertClassInvariants();
                return !(Math.abs(this.getX() - cCoord.getX()) > getDELTA()) &&
                        !(Math.abs(this.getY() - cCoord.getY()) > getDELTA()) &&
                        !(Math.abs(this.getZ() - cCoord.getZ()) > getDELTA());
            } else if (coordinate instanceof SphericCoordinate) {
                SphericCoordinate sCoord = this.asSphericCoordinate();
                return sCoord.isEqual(coordinate);
            } else {
                return false;
            }
        }catch (Exception e){
            throw new Exception("Error at coordinate comparison", e);
        }
    }
    @PatternInstance(
            patternName = "Template Method",
            participants = { "Concrete Class - Primitive Operation" }
    )
    protected double doGetCartesianDistance(CartesianCoordinate otherCoordinate) throws Exception{
        assertNotNull(otherCoordinate);
        double powX = Math.pow((this.getX() - otherCoordinate.getX()), 2);
        double powY = Math.pow((this.getY() - otherCoordinate.getY()), 2);
        double powZ = Math.pow((this.getZ() - otherCoordinate.getZ()), 2);
        double distance = Math.sqrt(powX + powY + powZ);
        assertGreaterZero(distance);
        return distance;
    }

    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    @Override
    protected void assertClassInvariants() {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);

    }
}
