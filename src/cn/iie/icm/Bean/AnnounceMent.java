package cn.iie.icm.Bean;

import oracle.sql.TIMESTAMP;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//此类作为公告类
public class AnnounceMent implements Serializable{
    private String title;
    private String text;
    private String author;
    private Timestamp datestamp;
    private Integer groupid;
    private String level;
    private Integer id;
    private String type;



    public AnnounceMent() {}

    public AnnounceMent(String title, String text, String author, Timestamp datestamp, Integer groupid, String level, Integer id,String type) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.datestamp = datestamp;
        this.groupid = groupid;
        this.level = level;
        this.id = id;
        this.type = type;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Timestamp datestamp) {
        this.datestamp = datestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String timeFormat(){

        String timeString = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            timeString = sdf.format(datestamp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return timeString;
    }


}
