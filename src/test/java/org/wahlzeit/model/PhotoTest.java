package org.wahlzeit.model;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
public class PhotoTest {
    private Photo photo;
    private Location testLocation = new Location(new Coordinate(1,2,3));;
    private ResultSet rset;
    //private PhotoId photoId = PhotoId.getIdFromInt(99);
    @Before
    public void initPhoto(){
        photo = new Photo();
    }
    /**
     *
     * Location tests
     */
    @Test
    public void TestLocationSetter(){

        photo.setLocation(testLocation);
        assertNotNull(photo.getLocation());
    }
    @Test
    public void TestLocationGetter(){
        photo.setLocation(testLocation);
        assertEquals(photo.getLocation(),testLocation);
    }
    @Test
    public void TestWriteOnLocation() throws SQLException {
        //System.out.println(photo.getIdAsString());
        //photo.setLocation(testLocation);
        //System.out.println(photo.getLocation().getCoordinate().getY());

        //photo.writeOn(rset);
        //photo.readFrom(rset);
        //assertEquals(photo.location,testLocation);
    }
}
