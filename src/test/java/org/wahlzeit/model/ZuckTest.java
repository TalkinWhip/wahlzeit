package org.wahlzeit.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;

import java.util.HashSet;

public class ZuckTest {
    ZuckManager zm = ZuckManager.getInstance();
    @Test
    public void testConstructor() {

        Zuck zucky = zm.ensureZuck("test");
        int hash = zucky.getZuckID();
        assertTrue(zm.getZuckInstances().containsKey(hash));
        int initialSize = zm.getZuckInstances().size();
        Zuck ducky = zm.ensureZuck("test");
        int newSize = zm.getZuckInstances().size();
        assertEquals(initialSize, newSize);

    }
    @Test
    public void testConvenienceConstructor(){
        ZuckPhoto zp = Mockito.mock(ZuckPhoto.class);
        HashSet<ZuckPhoto> hs = new HashSet<>();
        hs.add(zp);

        Zuck zucky = zm.ensureZuck("test", hs);
        int hash = zucky.getZuckID();
        assertTrue(zm.getZuckInstances().containsKey(hash));
        int initialSize = zm.getZuckInstances().size();
        Zuck ducky = zm.ensureZuck("test", hs);
        int newSize = zm.getZuckInstances().size();
        assertEquals(initialSize, newSize);

    }
    @Test
    public void testSetZuckPhotos(){
        ZuckPhoto zp = Mockito.mock(ZuckPhoto.class);
        HashSet<ZuckPhoto> hs = new HashSet<>();
        hs.add(zp);

        Zuck zucky = zm.ensureZuck("test");
        zucky.setZuckPhotos(hs);

        assertEquals(1,zucky.getZuckPhotos().size());

        zucky.removeZuckPhoto(zp);

        assertEquals(0,zucky.getZuckPhotos().size());

    }
    @Test
    public void testAddRemoveZuckPhoto(){
        ZuckPhoto zp = Mockito.mock(ZuckPhoto.class);
        HashSet<ZuckPhoto> hs = new HashSet<>();
        hs.add(zp);

        Zuck zucky = zm.ensureZuck("test");
        zucky.addZuckPhoto(zp);

        assertEquals(1,zucky.getZuckPhotos().size());

        zucky.removeZuckPhoto(zp);

        assertEquals(0,zucky.getZuckPhotos().size());



    }

}
