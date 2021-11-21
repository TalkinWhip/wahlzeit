package org.wahlzeit.model;

public class Location {
    private Coordinate coordinate;
    private boolean isSpheric = false;

    /**
     *
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
        setSphericFlag(coordinate);
    }

    /**
     *
     * @methodtype constructor
     */
    //convenience constructor
    public Location() {
        this.coordinate = new CartesianCoordinate();
    }
    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
    /**
     *
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        setSphericFlag(coordinate);
    }

    public boolean isSpheric() {
        return isSpheric;
    }

    public void setSphericFlag(Coordinate coordinate){
        if (coordinate instanceof SphericCoordinate){
            this.isSpheric = true;
        } else {
            this.isSpheric = false;
        }
    }
}
