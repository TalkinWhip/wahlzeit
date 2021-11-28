package org.wahlzeit.model;


import org.junit.Test;
import static org.junit.Assert.*;
public class SphericCoordinateTest {
    SphericCoordinate firstCoord = new SphericCoordinate();
    SphericCoordinate secondCoord = new SphericCoordinate(1,2,3);
    SphericCoordinate thirdCoord = new SphericCoordinate(-100,-100,-100);
    CartesianCoordinate cartCoord = new CartesianCoordinate(1,2,3);

    @Test
    public void testAsSpheric(){
        SphericCoordinate temp;
        temp = cartCoord.asSphericCoordinate();
        assertEquals(temp.getRadius(),3.74,0.1);
        assertEquals(temp.getTheta(),0.64,0.1);
        assertEquals(temp.getPhi(),1.1,0.1);
    }

    @Test
    public void testCentralAngle(){
        assertTrue(secondCoord.getCentralAngle(thirdCoord)>0);

    }
    @Test
    public void testCentralAngleWithEmpty(){
        assertTrue(firstCoord.getCentralAngle(secondCoord)>0);
    }

    @Test
    public void testCartesianDistance(){
        CartesianCoordinate temp = new CartesianCoordinate();
        assertEquals(cartCoord.getCartesianDistance(firstCoord), cartCoord.getCartesianDistance(temp),0.0001);
    }

    @Test
    public void testSuper(){
        assertTrue(firstCoord.getClass().getSuperclass()==AbstractCoordinate.class);
    }
}

