package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZuckPhotoManager extends PhotoManager{

    protected static final ZuckPhotoManager instance = new ZuckPhotoManager();

    public ZuckPhotoManager() {
        photoTagCollector = ZuckPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    protected ZuckPhoto createObject(ResultSet rset) throws SQLException {
        return ZuckPhotoFactory.getInstance().createPhoto(rset);
    }


}
