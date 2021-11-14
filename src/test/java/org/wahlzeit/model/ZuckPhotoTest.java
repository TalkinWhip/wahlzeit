package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.SessionManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class ZuckPhotoTest {
@Test
    public void testConstructor() {
    //arrange
    int year = 2019;
    //act
    ZuckPhoto photo = new ZuckPhoto(year);
    //assert
    assertEquals(photo.getYearOfPhoto(),year);
}
@Test
public void testInstance(){
    //arrange
    PhotoFactory photoFactory;
    //act
    photoFactory = PhotoFactory.getInstance();
    Object photo = photoFactory.createPhoto();
    //assert
    assertEquals(ZuckPhoto.class, photo.getClass());
}
/*
@Test
    public void testSerialization() throws SQLException, MalformedURLException {
    //arrange
    String mood = Mockito.mock(String.class);
    ResultSet rset = Mockito.mock(ResultSet.class);
    Mockito.mock(SessionManager.class);
    ZuckPhotoFactory photoFactory;
    photoFactory = ZuckPhotoFactory.getInstance();
    ZuckPhoto photoWrite = photoFactory.createPhoto();
    ZuckPhoto photoRead = photoFactory.createPhoto();
    photoWrite.setMood(mood);
    //URL url = new URL("http://wahlzeit.org/filter?userName=test-admin2");
    //URL url = Mockito.mock(URL.class);
    //photoWrite.setOwnerHomePage(url);
    //EmailAddress emailAddress = EmailAddress.getFromString("vasilm@web.de");
    //System.out.println(rset.getClass());
    //photoWrite.setOwnerEmailAddress(emailAddress);
    //System.out.println(photoWrite.getOwnerEmailAddress().asString());
    try{
        photoWrite.writeOn(rset);
    } catch (IllegalArgumentException ignored){  }
    photoRead.readFrom(rset);

    //assertEquals(photoRead.getMood(),mood);
    //verify(mood, Mockito.times(1)).writeOn(rset);
} */
}
