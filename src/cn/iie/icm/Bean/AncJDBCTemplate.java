package cn.iie.icm.Bean;

import cn.iie.icm.DAO.AncDao;
import cn.iie.icm.plantform.AnnouncementMapper;
//import com.sun.tools.corba.se.idl.StringGen;
import net.sf.ehcache.concurrent.Sync;
import oracle.sql.TIMESTAMP;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import cn.iie.icm.plantform.AnnouncementMapper;
import cn.iie.icm.DAO.*;

public class AncJDBCTemplate implements AncDao{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    /**
     * 设置数据源
     * @param ds
     */
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * 往tanc_manger表中插入一条记录
     * @param title
     * @param text
     * @param groupid
     * @param level
     */
    @Override
    public void create(String title, String text, Integer groupid, Integer level,Timestamp datestamp,String author) {
        String sql = "insert into tanc_manager (title,text,groupid,level,datestamp,author) values (?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql,title,text,groupid,level,datestamp,author);
        System.out.println("Created record title = " + title + "  text = " + text + "  groupid = " +groupid + "  level = " + level + "time = " + datestamp + "author = " + author);


    }

    /**
     * 从tanc_manger表获取一条记录
     * @param id
     * @return
     */

    @Override
    public AnnounceMent getAnnouncement(Integer id) {
        String sql = "select * from tanc_manager where id = ?";
        AnnounceMent announceMent = (AnnounceMent) jdbcTemplateObject.queryForObject(sql,new Object[]{id},new AnnouncementMapper());
        return announceMent;
    }

    /**
     * 从tanc_manger表中获取多条记录
     * @return
     */
    @Override
    public List<AnnounceMent> listAnnouncements() {
        String sql = "select * from tanc_manager order by datestamp ASC ";
        List<AnnounceMent> announceMents = jdbcTemplateObject.query(sql,new AnnouncementMapper());
        return announceMents;
    }

    /**
     * 从tanc_manger表删除一条记录
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String sql = "delete from tanc_manager where id = ?";
        jdbcTemplateObject.update(sql,id);
        System.out.println("Deleted Record with ID = " + id);
    }

    /**
     * 更新tanc_manger表中的一条记录
     * @param title
     * @param text
     * @param groupid
     * @param level
     * @param id
     */
    @Override
    public void update(String title, String text, Integer groupid, Integer level,Integer id) {
        String sql = "update tanc_manger set title = ? text = ? groupid = ? level = ? where id = ?";
        jdbcTemplateObject.update(sql,title,text,groupid,level);
        System.out.println("Updated Record with Id = " + id);

    }


    /**
     * 根据公告标题删除一条记录
     * @param title
     */
    public void deleteByTitle(String title){
        String sql = "delete from tanc_manager where title = ?";
        jdbcTemplateObject.update(sql,title);

    }
}
