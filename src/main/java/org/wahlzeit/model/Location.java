package org.wahlzeit.model;

public class Location {
    private Coordinate coordinate;
    private boolean isSpheric = false;

    /**
     *
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        if (coordinate == null) { throw new IllegalArgumentException("invalid coordinate"); }
        this.coordinate = coordinate;
        setSphericFlag(coordinate);
    }

    /**
     *
     * @methodtype constructor
     */
    //convenience constructor
    public Location() {
        this.coordinate = CartesianCoordinate.ensureCartesianCoordinate();
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
        if (coordinate == null) { throw new IllegalArgumentException("invalid coordinate"); }
        this.coordinate = coordinate;
        setSphericFlag(coordinate);
    }

    public boolean isSpheric() {
        return isSpheric;
    }

    public void setSphericFlag(Coordinate coordinate){
        if (coordinate == null) { throw new IllegalArgumentException("invalid coordinate"); }
        if (coordinate instanceof SphericCoordinate){
            this.isSpheric = true;
        } else {
            this.isSpheric = false;
        }
    }
}
