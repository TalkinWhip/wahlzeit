package org.wahlzeit.model;

public class Coordinate {
    private double x;
    private double y;
    private double z;


    /**
     *
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     *
     * @methodtype constructor
     */
    // alternative constructor, since location has 0..1 multiplicity
    public Coordinate() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
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
    public boolean isEqual(Coordinate compare){
        if (this.getX() != compare.getX() || this.getY() != compare.getY() || this.getZ() != compare.getZ()){
            return false;
        }
        else { return true; }
    }
    /**
     *
     * @methodtype boolean-query
     */
    //deep compare of class
    public boolean equals(Object compare){
        if (this.getClass() == compare.getClass()) {
            Coordinate loc = (Coordinate) compare;
            this.isEqual(loc);
        }
        return false;
    }
    /**
     *
     * @methodtype get
     */
    public double getDistance(Coordinate compare){
        double powX = Math.pow((this.getX()-compare.getX()),2);
        double powY = Math.pow((this.getY()-compare.getY()),2);
        double powZ = Math.pow((this.getZ()-compare.getZ()),2);
        double distance = Math.sqrt(powX+powY+powZ);
        return distance;
    }
}
