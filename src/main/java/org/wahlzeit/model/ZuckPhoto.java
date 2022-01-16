package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
//a
@PatternInstance(
        patternName = "AbstractFactory",
        participants = { "ConcreteProduct" }
)
public class ZuckPhoto extends Photo{

    private int yearOfPhoto;
    private String mood;
    private Zuck zuck;
    public ZuckPhoto(int yearOfPhoto) throws Exception{
        super();
        if ( 1900 > yearOfPhoto || yearOfPhoto > Year.now().getValue()) { throw new Exception("invalid yearOfPhoto"); }
        this.yearOfPhoto = yearOfPhoto;
        this.zuck = null;
    }
    public ZuckPhoto(int yearOfPhoto, Zuck zuck) throws Exception{
        super();
        if ( 1900 > yearOfPhoto || yearOfPhoto > Year.now().getValue()) { throw new Exception("invalid yearOfPhoto"); }
        this.yearOfPhoto = yearOfPhoto;
        this.zuck = zuck;
    }

    public ZuckPhoto() {
        super();
        this.zuck = null;
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

    public void setYearOfPhoto(int yearOfPhoto) throws Exception{
        if ( 1900 > yearOfPhoto || yearOfPhoto > Year.now().getValue()) { throw new Exception("invalid yearOfPhoto"); }
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
        try{
            this.setYearOfPhoto(rset.getInt("yearOfPhoto"));
        }catch(Exception e){
            throw new IllegalArgumentException("Error during reading yearOfPhoto from DB",e);
        }
    }
    public Zuck getZuck() {
        return zuck;
    }

    public void setZuck(Zuck zuck) {
        this.zuck = zuck;
    }
}
