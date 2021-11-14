package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZuckPhoto extends Photo{

    private int yearOfPhoto;
    private String mood;

    public ZuckPhoto(int yearOfPhoto) {
        super();
        this.yearOfPhoto = yearOfPhoto;
    }

    public ZuckPhoto() {
        super();
    }
    public ZuckPhoto(PhotoId myId){
        super(myId);
    }

    public ZuckPhoto(ResultSet rs) throws SQLException {
        super(rs);
    }

    public int getYearOfPhoto() {
        return yearOfPhoto;
    }

    public void setYearOfPhoto(int yearOfPhoto) {
        this.yearOfPhoto = yearOfPhoto;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateInt("yearOfPhoto", this.getYearOfPhoto());
        rset.updateString("mood", this.getMood());
    }
    public void readFrom(ResultSet rset) throws SQLException{
        super.readFrom(rset);
        this.setMood(rset.getString("mood"));
        this.setYearOfPhoto(rset.getInt("yearOfPhoto"));
    }
}
