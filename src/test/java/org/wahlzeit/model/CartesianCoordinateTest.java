package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {
    CartesianCoordinate emptyCoord;
    CartesianCoordinate firstCoord;
    CartesianCoordinate secondCoord;
    Object fakeCoord;
    double res1;
    double res2;
    SphericCoordinate sphericCoord;

    @Before
    public void initCoordinates() {
        firstCoord = new CartesianCoordinate(2,2,2);
        secondCoord = new CartesianCoordinate(5,5,7);
        emptyCoord = new CartesianCoordinate();
        fakeCoord = new Object();
        sphericCoord = new SphericCoordinate(5,5,7);
        res1 = Math.sqrt(Math.pow((5-2),2)+Math.pow((5-2),2)+Math.pow((7-2),2));
        res2 = Math.sqrt(Math.pow(2,2)+Math.pow(2,2)+Math.pow(2,2));
        // all of this is just really stupid...
    }

    @Test
    public void TestDistanceOne(){
        assertEquals(firstCoord.getDistance(secondCoord),res1,0.00001);


    }
    @Test
    public void TestDistanceTwo(){
        assertEquals(firstCoord.getDistance(emptyCoord),res2,0.00001);
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
    @Test
    public void TestSphericEquals(){
        assertFalse(secondCoord.equals(sphericCoord));
    }
    @Test
    public void TestSphericisEqual(){
        assertFalse(secondCoord.isEqual(sphericCoord));
    }
    @Test
    public void TestAsCartesian(){
        CartesianCoordinate temp;
        temp = sphericCoord.asCartesianCoordinate();
        assertTrue(temp instanceof CartesianCoordinate);
        assertEquals(Math.abs(temp.getX()),3.6,0.1);
        assertEquals(Math.abs(temp.getY()),3.15,0.1);
        assertEquals(Math.abs(temp.getZ()),1.41,0.1);
    }
    @Test
    public void TesttoCartesian(){
        CartesianCoordinate temp;
        temp = firstCoord.asCartesianCoordinate();
        assertTrue(temp instanceof CartesianCoordinate);
    }
}
