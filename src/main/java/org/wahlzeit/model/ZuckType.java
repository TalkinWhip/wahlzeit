package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class ZuckType {
    private String zuckType;
    private int ztID;
    private ZuckManager manager;
    private ZuckType superType; // if there isn't a supertype leave at null, makes checking isSubtype/isLeaf easier.
    private static HashMap<Integer, ZuckType> zuckSubtypes = new HashMap<>(); // each zucktype can have it's own subtypes, this is saved here. manager manages a flat hierarchy of all subtypes.


    protected ZuckType(String typeName){ // create base zuck type (super)
        ztID = hashCode(typeName);
        this.manager = ZuckManager.getInstance();
        this.superType  = null;

        //manager.ensureZuckType(typeName);
    }
    protected ZuckType(String typeName, ZuckType parent){ // create sub zuck type.
        ztID = hashCode(typeName);
        this.manager = ZuckManager.getInstance();
        this.superType  = parent;

    }

    private int hashCode(String typeName) {
        return Objects.hash(typeName);
    }

    public ZuckType(String typeName, String parent){ //create subtype
        this.ztID = hashCode(typeName);

    }

    public Zuck createInstance(HashSet<ZuckPhoto> zp){ //create zuck instance with existing set of photos - flyweight in manager.
        //TODO: create an instance of zuck corresponding to this type
        assertIsNotNull(zp);
        return new Zuck(this, zp);
    }
    public Zuck createInstance(){ //convenience create method - flyweight in manager.
        //TODO: create an instance of zuck corresponding to this type
        return new Zuck(this);
    }

    public boolean isSubtype(){
        if (this.superType == null){
            return false;
        }else{
            return true;
        }
    }

    public boolean isLeaf(){
        if (this.zuckSubtypes.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
    public String getZuckType() {
        return zuckType;
    }
    private void assertIsNotNull(Object o){
        if (o == null){
            throw new IllegalArgumentException("argument is null.");
        }
    }
}
