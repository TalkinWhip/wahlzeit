package org.wahlzeit.model;


import net.bytebuddy.pool.TypePool;
import org.junit.Test;
import static org.junit.Assert.*;
public class SphericCoordinateTest {
    SphericCoordinate firstCoord = SphericCoordinate.fetchSphericCoordinate();
    SphericCoordinate secondCoord = SphericCoordinate.fetchSphericCoordinate(1,2,3);
    SphericCoordinate emptyCoord = SphericCoordinate.fetchSphericCoordinate();
    CartesianCoordinate cartCoord = CartesianCoordinate.fetchCartesianCoordinate(1,2,3);

    @Test
    public void testAsSpheric() throws Exception{
        SphericCoordinate temp;
        temp = cartCoord.asSphericCoordinate();
        assertEquals(temp.getRadius(),3.74,0.1);
        assertEquals(temp.getTheta(),0.64,0.1);
        assertEquals(temp.getPhi(),1.1,0.1);
    }

    @Test
    public void testCentralAngle() throws Exception{
        assertTrue(secondCoord.getCentralAngle(cartCoord.asSphericCoordinate())>0);

    }
    @Test
    public void testCentralAngleWithEmpty() throws Exception{
        assertTrue(firstCoord.getCentralAngle(secondCoord)>0);
    }

    @Test
    public void testCartesianDistance() throws Exception{
        CartesianCoordinate temp = CartesianCoordinate.fetchCartesianCoordinate();
        assertEquals(cartCoord.getCartesianDistance(firstCoord), cartCoord.getCartesianDistance(temp),0.0001);
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

   @Test(expected = RuntimeException.class)
    public void testNaNCoordinate(){
        SphericCoordinate nanCoord = SphericCoordinate.fetchSphericCoordinate(1,1,Double.NaN);

    }
    @Test(expected = Exception.class)
    public void testFalseCoordinate(){
        SphericCoordinate testCoord = SphericCoordinate.fetchSphericCoordinate(1,1,9);
    }

    @Test(expected = Exception.class)
    public void testCheckedExceptionsAngle() throws Exception{
        firstCoord.getCentralAngle(null);

    }

    @Test
    public void testSharing() throws Exception{
        SphericCoordinate newEmptyCoord = SphericCoordinate.fetchSphericCoordinate();
        int hc = emptyCoord.hashCode();
        assertTrue(emptyCoord == newEmptyCoord);
        int new_hc = newEmptyCoord.hashCode();
        assertTrue(hc==new_hc);

    }
}

