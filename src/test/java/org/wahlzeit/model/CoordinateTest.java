package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    Coordinate emptyCoord;
    Coordinate firstCoord;
    Coordinate secondCoord;
    Object fakeCoord;
    double res1;
    double res2;

    @Before
    public void initCoordinates() {
        firstCoord = new Coordinate(2,2,2);
        secondCoord = new Coordinate(5,5,7);
        emptyCoord = new Coordinate();
        fakeCoord = new Object();
        res1 = Math.sqrt(Math.pow((5-2),2)+Math.pow((5-2),2)+Math.pow((7-2),2));
        res2 = Math.sqrt(Math.pow(2,2)+Math.pow(2,2)+Math.pow(2,2));
        // all of this is just really stupid...
    }

    @Test
    public void TestDistanceOne(){
        assertEquals(firstCoord.getDistance(secondCoord),res1,0);


    }
    @Test
    public void TestDistanceTwo(){
        assertEquals(firstCoord.getDistance(emptyCoord),res2,0);
    }

    @Test
    public void TestisEqualOne() {
        assertFalse(firstCoord.isEqual(secondCoord));
    }
    public void TestisEqualTwo() {
        assertTrue(firstCoord.isEqual(firstCoord));
    }

    @Test
    public void TestEqualsOne() {
        assertFalse(firstCoord.equals(secondCoord));
    }
    @Test
    public void TestEqualsTwo() {
        assertTrue(firstCoord.equals(firstCoord));
    }
    @Test
    public void TestEqualsThree() {
        assertFalse(emptyCoord.equals(fakeCoord));
    }
}
