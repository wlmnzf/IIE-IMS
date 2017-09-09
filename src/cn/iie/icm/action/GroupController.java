package cn.iie.icm.action;

import cn.iie.icm.util.DbDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/group")
public class GroupController {
    @RequestMapping("/list")
    @ResponseBody
    private List<Map<String, String>> list() {

        List<Map<String, String>> datas=new ArrayList<Map<String,String>>();
        Map<String, String> data;
        data=new HashMap<String, String>();
        data.put("id", "academy");
        data.put("parent", "root");
        data.put("text", "信工所");
        datas.add(data);
        try {
            DbDao dd = new DbDao();
            ResultSet rs = dd.query("select id, name from troom ");
            while (rs.next()) {
                data=new HashMap<String, String>();
                data.put("id", "room"+rs.getString("id"));
                data.put("parent", "academy");
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, room_id from tgroup ");
            while (rs.next()) {
                data=new HashMap<String, String>();
                data.put("id", "group"+rs.getString("id"));
                data.put("parent", "room"+rs.getString("room_id"));
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, group_id from tperson ");
            while (rs.next()) {
                data=new HashMap<String, String>();
                data.put("id", "person"+rs.getString("id"));
                data.put("parent", "group"+rs.getString("group_id"));
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return datas;
    }
}
