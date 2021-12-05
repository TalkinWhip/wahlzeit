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
        secondCoord = new CartesianCoordinate(5,3,6);
        emptyCoord = new CartesianCoordinate();
        fakeCoord = new Object();
        sphericCoord = new SphericCoordinate(5,3,6);
        res1 = Math.sqrt(Math.pow((5-2),2)+Math.pow((3-2),2)+Math.pow((6-2),2));
        res2 = Math.sqrt(Math.pow(2,2)+Math.pow(2,2)+Math.pow(2,2));
        // all of this is just really stupid...
    }

    @Test
    public void TestDistanceOne(){
        assertEquals(firstCoord.getCartesianDistance(secondCoord),res1,0.00001);


    }
    @Test
    public void TestDistanceTwo(){
        assertEquals(firstCoord.getCartesianDistance(emptyCoord),res2,0.00001);
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
        assertEquals(Math.abs(temp.getX()),0.67749619295,0.1);
        assertEquals(Math.abs(temp.getY()),0.1971555867,0.1);
        assertEquals(Math.abs(temp.getZ()),4.94996248,0.1);
    }
    @Test
    public void TesttoCartesian(){
        CartesianCoordinate temp;
        temp = firstCoord.asCartesianCoordinate();
        assertTrue(temp instanceof CartesianCoordinate);
    }
    @Test
    public void testSuper(){
        assertTrue(firstCoord.getClass().getSuperclass()==AbstractCoordinate.class);
    }

    @Test(expected = NullPointerException.class)
    public void testNullCoord() throws NullPointerException {
        Coordinate testCoord = null;
        firstCoord.equals(testCoord);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNCoordinate(){
        firstCoord.setX(Double.NaN);
    }

}
