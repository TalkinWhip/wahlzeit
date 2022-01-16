package org.wahlzeit.model;

// a zuck can associate with zuckphotos

import java.util.HashSet;
import java.util.Objects;


public class Zuck {

    private ZuckManager.ZuckType type;
    private static ZuckManager manager;
    private int zuckID;
    HashSet<ZuckPhoto> zuckPhotos;

    protected Zuck(ZuckManager.ZuckType zuckType) { // empty HashSet
        assertIsNotNull(zuckType);
        this.type = zuckType;
        this.manager = ZuckManager.getInstance();
        this.zuckPhotos = new HashSet<>();
        this.zuckID = hashCode(zuckType, zuckPhotos);
    }
    protected Zuck(ZuckManager.ZuckType zuckType, HashSet<ZuckPhoto> zuckPhotos) { // predefined hashset
        assertIsNotNull(zuckPhotos);
        assertIsNotNull(zuckType);
        this.type = zuckType;
        this.manager = ZuckManager.getInstance();
        this.zuckID = hashCode(zuckType, zuckPhotos);
        this.zuckPhotos = zuckPhotos;
    }

    public HashSet<ZuckPhoto> getZuckPhotos() {
        return zuckPhotos;
    }

    public void setZuckPhotos(HashSet<ZuckPhoto> zuckPhotos) {
        assertIsNotNull(zuckPhotos);
        this.zuckPhotos = zuckPhotos;
        for (ZuckPhoto p: zuckPhotos){
            p.setZuck(this);
        }
    }
    public void addZuckPhoto(ZuckPhoto zp){
        assertIsNotNull(zp);
        this.zuckPhotos.add(zp);
        zp.setZuck(this); // keep reference of the zuck in the zuckphotos
    }
    public void removeZuckPhoto(ZuckPhoto zp){
        assertIsNotNull(zp);
        this.zuckPhotos.remove(zp);
        zp.setZuck(null); //remove reference on deletion (i guess this goes in the direction of aggregation but it makes sense to me in this case even though UML only has association.
    }
    public int getZuckID() {
        return this.zuckID;
    }

    public int hashCode(ZuckManager.ZuckType t, HashSet<ZuckPhoto> zp) {
        return Objects.hash(t, zp);
    }
    private void assertIsNotNull(Object o){
        if (o == null){
            throw new IllegalArgumentException("argument is null.");
        }
    }
}



