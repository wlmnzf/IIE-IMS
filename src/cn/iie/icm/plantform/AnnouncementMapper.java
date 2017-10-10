package cn.iie.icm.plantform;

import cn.iie.icm.Bean.AnnounceMent;

import javax.rmi.CORBA.StubDelegate;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementMapper implements org.springframework.jdbc.core.RowMapper{

    @Override
    public AnnounceMent mapRow(ResultSet rs, int i) throws SQLException {
        AnnounceMent announceMent = new AnnounceMent();
        announceMent.setText(rs.getString("text"));
        announceMent.setTitle(rs.getString("title"));
        announceMent.setGroupid(rs.getInt("groupid"));
        announceMent.setLevel(rs.getString("level"));
        announceMent.setDatestamp(rs.getTimestamp("datestamp"));
        announceMent.setAuthor(rs.getString("author"));
        announceMent.setType(rs.getString("type"));
        return announceMent;
    }
}
