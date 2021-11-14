package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZuckPhotoFactoryTest {
    @Test
    public void testInstance(){
        //arrange
        PhotoFactory checkInstance;
        //act
        checkInstance = PhotoFactory.getInstance();
        System.out.println(checkInstance.getClass());
        //assert
        assertEquals(ZuckPhotoFactory.class, checkInstance.getClass());
    }
}
