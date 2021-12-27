package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
@PatternInstance(
        patternName = "Abstract Factory",
        participants = { "ConcreteFactory" }
)
public class ZuckPhotoFactory extends PhotoFactory{
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static ZuckPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized ZuckPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting non-generic ZuckPhotoFactory");
            setInstance(new ZuckPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(ZuckPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     *
     */
    protected ZuckPhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public ZuckPhoto createPhoto() {
        return new ZuckPhoto();
    }

    /**
     *
     */
    public ZuckPhoto createPhoto(PhotoId id) {
        return new ZuckPhoto(id);
    }

    /**
     *
     */
    public ZuckPhoto createPhoto(ResultSet rs) throws SQLException {
        return new ZuckPhoto(rs);
    }

    /**
     *
     */
    public PhotoFilter createPhotoFilter() {
        return new PhotoFilter();
    }

    /**
     *
     */
    public PhotoTagCollector createPhotoTagCollector() {
        return new PhotoTagCollector();
    }
}
