package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    /**
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
     * @methodype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    public SphericCoordinate asSphericCoordinate() {
        double radius;
        double theta;
        double phi;

        radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        assertGreaterZero(radius); // to avoid dividing by 0
        theta = Math.acos((this.z / (radius)));
        if (this.x > 0) {
            phi = Math.atan((this.y / this.x));
        } else if (this.x < 0) {
            phi = Math.atan((this.y / this.x)) + Math.PI;
        } else {
            phi = Math.PI / 2;
        }

        SphericCoordinate temp = new SphericCoordinate(radius, theta, phi);
        temp.assertClassInvariants();
        return temp;
    }

    /**
     * @methodtype boolean-query
     */
    public boolean isEqual(Coordinate coordinate) {
        assertNotNull(coordinate);
        assertClassInvariants();
        if (getClass() == coordinate.getClass()) {
            CartesianCoordinate cCoord = coordinate.asCartesianCoordinate();
            cCoord.assertClassInvariants();
            if (Math.abs(this.getX() - cCoord.getX()) > getDELTA() ||
                    Math.abs(this.getY() - cCoord.getY()) > getDELTA() ||
                    Math.abs(this.getZ() - cCoord.getZ()) > getDELTA()) {
                return false;
            } else {
                return true;
            }
        } else if (coordinate instanceof SphericCoordinate) {
            SphericCoordinate sCoord = this.asSphericCoordinate();
            return sCoord.isEqual(coordinate);
        } else {
            return false;
        }
    }

    protected double doGetCartesianDistance(CartesianCoordinate otherCoordinate) {
        assertNotNull(otherCoordinate);
        double powX = Math.pow((this.getX() - otherCoordinate.getX()), 2);
        double powY = Math.pow((this.getY() - otherCoordinate.getY()), 2);
        double powZ = Math.pow((this.getZ() - otherCoordinate.getZ()), 2);
        double distance = Math.sqrt(powX + powY + powZ);
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

    /**
     * @methodtype set
     */
    public void setX(double x) {
        assertValidDouble(x);
        this.x = x;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        assertValidDouble(y);
        this.y = y;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        assertValidDouble(z);
        this.z = z;
    }

    @Override
    protected void assertClassInvariants() {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);

    }
}
