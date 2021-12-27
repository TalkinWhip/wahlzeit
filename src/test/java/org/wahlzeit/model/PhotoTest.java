package org.wahlzeit.model;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
public class PhotoTest {
    private Photo photo;
    private Location testCartLocation = new Location(CartesianCoordinate.ensureCartesianCoordinate(1,2,3));
    private Location testSphLocation = new Location(SphericCoordinate.ensureSphericCoordinate(1,2,3));
    private ResultSet rset = Mockito.mock(ResultSet.class);
    URL ownerHomePage;

    {
        try {
            ownerHomePage = new URL("http://wahlzeit.org/filter?userName=test-admin2");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

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

        photo.setLocation(testSphLocation);
        assertNotNull(photo.getLocation());
        assertTrue(photo.getLocation().isSpheric());
    }
    @Test
    public void TestLocationGetter(){
        photo.setLocation(testCartLocation);
        assertEquals(photo.getLocation(),testCartLocation);
        assertFalse(photo.getLocation().isSpheric());
    }
    @Test
    public void TestWriteOnLocation() throws SQLException {

        photo.setLocation(testCartLocation);
        System.out.println(photo.getLocation().isSpheric());
        photo.setOwnerHomePage(ownerHomePage);
        photo.writeOn(rset);
        // here is the issue, doesn't properly write or read to from resultset
        photo.readFrom(rset);
        System.out.println(photo.getLocation().getCoordinate().getClass());
        //assertEquals(photo.location.getCoordinate().asCartesianCoordinate().getX(),testCartLocation.getCoordinate().asCartesianCoordinate().getX(),0.001);

    }
}
