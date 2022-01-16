package org.wahlzeit.model;
import org.junit.Test;

import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ZuckManagerTest {
    ZuckManager zm = ZuckManager.getInstance();
    @Test
    public void testEnsureZuckType() {
        ZuckManager.ZuckType zucky = zm.ensureZuckType("test");
        int hash = zucky.getZtID();
        assertTrue(zm.getZuckTypeInstances().containsKey(hash));
        int initialSize = zm.getZuckTypeInstances().size();
        ZuckManager.ZuckType ducky = zm.ensureZuckType("test");
        int newSize = zm.getZuckTypeInstances().size();
        assertEquals(initialSize, newSize);
    }
    @Test
    public void testEnsureZuckTypeParent(){
        ZuckManager.ZuckType zucky = zm.ensureZuckType("test");
        ZuckManager.ZuckType ducky = zm.ensureZuckType("test2",zucky);

        assertEquals(ducky.getSuperType(), zucky);
        assertEquals(2,zm.getZuckTypeInstances().size());
        assertEquals(zucky.getZuckSubtypes().get(ducky.getZtID()), ducky);
    }
    @Test
    public void testZuckTypeSuperSub(){
        ZuckManager.ZuckType zucky = zm.ensureZuckType("test");
        ZuckManager.ZuckType ducky = zm.ensureZuckType("test2");

        ducky.setSuperType(zucky);
        assertEquals(zucky.getZuckSubtypes().get(ducky.getZtID()), ducky);
        assertEquals(ducky.getSuperType(), zucky);
        assertTrue(ducky.isSubtype());
        assertTrue(ducky.isLeaf());

        ducky.removeSuperType();
        assertFalse(ducky.isSubtype());
        assertNotEquals(zucky.getZuckSubtypes().get(ducky.getZtID()), ducky);

        zucky.addSubType(ducky);
        assertEquals(zucky.getZuckSubtypes().get(ducky.getZtID()), ducky);
        assertEquals(ducky.getSuperType(), zucky);
        assertTrue(ducky.isSubtype());
        assertTrue(ducky.isLeaf());

        zucky.removeSubType(ducky);
        assertFalse(ducky.isSubtype());


    }

}
