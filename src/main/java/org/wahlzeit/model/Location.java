package org.wahlzeit.model;

public class Location {
    public Coordinate coordinate;

    /**
     *
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

}
