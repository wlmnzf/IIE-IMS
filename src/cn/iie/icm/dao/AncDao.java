package cn.iie.icm.dao;

import cn.iie.icm.Bean.AnnounceMent;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public interface AncDao {

    public void setDataSource(DataSource ds);

    public void create(String title, String text, Integer groupid, String level, Timestamp datestamp,String author,String type);

    public AnnounceMent getAnnouncement(Integer id);

    public List<AnnounceMent> listAnnouncements();

    public void delete(Integer id);

    public void deleteByTitle(String title);

    public void update(String title,String text,Integer groupid,String level,Integer id);

    public int groupAccess(String usr);

    public void updateStickly(Integer stickly , String title);
}
