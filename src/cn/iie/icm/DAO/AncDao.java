package cn.iie.icm.DAO;

import cn.iie.icm.Bean.AnnounceMent;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AncDao {

    public void setDataSource(DataSource ds);

    public void create(String title, String text, Integer groupid, Integer level, Timestamp datestamp,String author);

    public AnnounceMent getAnnouncement(Integer id);

    public List<AnnounceMent> listAnnouncements();

    public void delete(Integer id);

    public void deleteByTitle(String title);

    public void update(String title,String text,Integer groupid,Integer level,Integer id);
}
