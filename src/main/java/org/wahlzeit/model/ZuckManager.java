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
    /**
     * Collaboration Specification:
     * Manager = { Manager, Element }
     * Manager.Manager → ZuckManager
     * Manager.Element → Zuck
     */
    protected static ZuckManager instance = new ZuckManager();

    private static HashMap<Integer, Zuck> zuckInstances = new HashMap<>();
    private static HashMap<Integer, ZuckManager.ZuckType> zuckTypeInstances = new HashMap<>(); // each zucktype can have it's own subtypes, this is done in each ZuckManager.ZuckType class. Here is a flat hm of ZuckTypes.
    // tracing Zuck: Step 1 (multiple overloaded methods)
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
    public ZuckType ensureZuckType(String zuckType) { //get or create a base zucktype
        assertIsNotNull(zuckType);
        ZuckType zt = new ZuckType(zuckType);
        int zuckHash = zt.getZtID();
        if (zuckTypeInstances.containsKey(zuckHash)) {
            return zuckTypeInstances.get(zuckHash);
        } else {
            zuckTypeInstances.put(zuckHash, zt);
            return zt;
        }
    }
    public ZuckType ensureZuckType(String zuckType, ZuckType parent) { //get or create a sub zucktype
        assertIsNotNull(zuckType);
        assertIsNotNull(parent);
        ZuckType zt = new ZuckType(zuckType, parent);
        int zuckHash = zt.getZtID();
        if (zuckTypeInstances.containsKey(zuckHash)) {
            return zuckTypeInstances.get(zuckHash);
        } else {
            zuckTypeInstances.put(zuckHash, zt);
            return zt;
        }
    }

    public static final ZuckManager getInstance() {
        return instance;
    }

    public static HashMap<Integer, Zuck> getZuckInstances() {
        return zuckInstances;
    }

    public static HashMap<Integer, ZuckType> getZuckTypeInstances() {
        return zuckTypeInstances;
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
    public class ZuckType {
        /**
         * Collaboration Specification:
         * TypeObject = { Type, BaseObject }
         * TypeObject.Type → ZuckType
         * TypeObject.BaseObject → Zuck
         */
        private String zuckType;
        private int ztID;
        private ZuckManager.ZuckType superType; // if there isn't a supertype leave at null, makes checking isSubtype/isLeaf easier.
        private HashMap<Integer, ZuckManager.ZuckType> zuckSubtypes = new HashMap<>(); // each zucktype can have it's own subtypes, this is saved here. manager manages a flat hierarchy of all subtypes.


        protected ZuckType(String typeName){ // create base zuck type (super)
            assertIsNotNull(typeName);
            this.zuckType = typeName;
            this.ztID = hashCode(typeName);
            this.superType  = null;
        }
        protected ZuckType(String typeName, ZuckType parent){ // create sub zuck type.
           assertIsNotNull(parent);
           assertIsNotNull(typeName);
            this.zuckType = typeName;
            this.ztID = hashCode(typeName);
            this.superType  = parent;
            this.superType.addSubType(this); //also add this as subtype to the parent

        }

        private int hashCode(String typeName) {
            return Objects.hash(typeName);
        }

        // tracing Zuck: Step 2 (multiple overloaded methods)
        protected Zuck createInstance(HashSet<ZuckPhoto> zp){ //create zuck instance with existing set of photos - flyweight in manager.
            assertIsNotNull(zp);
            return new Zuck(this, zp);
        }
        protected Zuck createInstance(){ //convenience create method - flyweight in manager.
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

        public int getZtID() {
            return ztID;
        }

        public ZuckType getSuperType() {
            return superType;
        }

        public HashMap<Integer, ZuckType> getZuckSubtypes() {
            return zuckSubtypes;
        }

        public void setSuperType(ZuckType superType) {
            assertIsNotNull(superType);
            this.superType = superType;
            if (!superType.zuckSubtypes.containsKey(this.getZtID())){
                superType.addSubType(this);
            }

        }

        public void addSubType(ZuckType subType){
            assertIsNotNull(subType);
            this.zuckSubtypes.put(hashCode(subType.getZuckType()), subType);
            if (!this.isSubtype()){ //set super if no super
                subType.setSuperType(this);
            }
            if (!subType.superType.equals(this)){ //if previously another super, set to current
                subType.setSuperType(this);
            }
        }

        public void removeSubType(ZuckType subType){
            assertIsNotNull(subType);
            if (subType.superType.equals(this)){
                subType.superType = null;
            }
            this.zuckSubtypes.remove(hashCode(subType.getZuckType()));

        }
        public void removeSuperType(){
            assertIsNotNull(this.superType);
            this.superType.removeSubType(this);
            this.superType = null;
        }

        private void assertIsNotNull(Object o){
            if (o == null){
                throw new IllegalArgumentException("argument is null.");
            }
        }
    }

}
