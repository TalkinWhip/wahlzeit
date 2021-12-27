package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
        patternName = "Bridge",
        participants = { "Abstraction" }
)

public interface Coordinate {
    public CartesianCoordinate asCartesianCoordinate() throws Exception;
    public SphericCoordinate asSphericCoordinate() throws Exception;

    public double getCartesianDistance(Coordinate coordinate) throws Exception;
    public double getCentralAngle(Coordinate coordinate) throws Exception;

    public boolean isEqual(Coordinate coordinate) throws Exception;

}
