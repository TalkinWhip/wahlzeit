package org.wahlzeit.model;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class ZuckManager extends ObjectManager {

    public ZuckManager(){
    }

    protected static ZuckManager instance;

    private static HashMap<Integer, Zuck> zuckInstances = new HashMap<>();
    private static HashMap<Integer, ZuckType> zuckTypeInstances = new HashMap<>(); // each zucktype can have it's own subtypes, this is done in each ZuckType class. Here is a flat hm of ZuckTypes.

    public Zuck ensureZuck(String zuckType) { //
        assertIsNotNull(zuckType);
        ZuckType zt = this.ensureZuckType(zuckType);
        Zuck zuckInstance = zt.createInstance();
        int zuckHash = zuckInstance.getZuckID();
        if (zuckInstances.containsKey(zuckHash)){
            return zuckInstances.get(zuckHash);
        }else {
            zuckInstances.put(zuckHash, zuckInstance);
            return zuckInstance;
        }
    }
    public Zuck ensureZuck(String zuckType, HashSet<ZuckPhoto> zuckPhotos) { //
        assertIsNotNull(zuckType);
        assertIsNotNull(zuckPhotos);
        ZuckType zt = this.ensureZuckType(zuckType);
        Zuck zuckInstance = zt.createInstance(zuckPhotos);
        int zuckHash = zuckInstance.getZuckID();
        if (zuckInstances.containsKey(zuckHash)) {
            return zuckInstances.get(zuckHash);
        } else {
            zuckInstances.put(zuckHash, zuckInstance);
            return zuckInstance;
        }
    }
    public ZuckType ensureZuckType(String zuckType) { // get or create a base zucktype
        assertIsNotNull(zuckType);
        return null;

    }
    public ZuckType ensureZuckTypeSub(String zuckType) { // get or create a sub zucktype
        assertIsNotNull(zuckType);
        return null;

    }

    public static final ZuckManager getInstance() {
        return instance;
    }

    public HashMap<Integer, ZuckType> getZuckSubtypes(String zuckType){
        return null;
    }


    private void assertIsNotNull(Object o){
        if (o == null){
            throw new IllegalArgumentException("argument is null.");
        }
    }

    @Override //not implementing persistence here, but needed to satisfy the inheritance
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }
    

}
