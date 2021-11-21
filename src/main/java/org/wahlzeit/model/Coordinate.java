package org.wahlzeit.model;

public interface Coordinate {
    public CartesianCoordinate asCartesianCoordinate();
    public SphericCoordinate asSphericCoordinate();

    public double getCartesianDistance(Coordinate coordinate);
    public double getCentralAngle(Coordinate coordinate);

    public boolean isEqual(Coordinate coordinate);

}
