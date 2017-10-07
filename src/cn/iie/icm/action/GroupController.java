package cn.iie.icm.action;

import cn.iie.icm.util.DbDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/group")
public class GroupController {

    private String[] tables={"academy", "troom", "tgroup", "tperson"};

    @RequestMapping("/list")
    @ResponseBody
    private List<Map<String, String>> list() {

        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Map<String, String> data;
        data = new HashMap<String, String>();
        data.put("id", "0_0");
        data.put("parent", "root");
        data.put("text", "信工所");
        datas.add(data);
        try {
            DbDao dd = new DbDao();
            ResultSet rs = dd.query("select id, name from "+tables[1]);
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "1_" + rs.getString("id"));
                data.put("parent", "0_0");
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, troom_id from "+tables[2]);
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "2_" + rs.getString("id"));
                data.put("parent", "1_" + rs.getString("troom_id"));
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, tgroup_id from "+tables[3]);
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "3_" + rs.getString("id"));
                data.put("parent", "2_" + rs.getString("tgroup_id"));
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    @RequestMapping("/addGroup")
    @ResponseBody
    public Map<String, String> addGroup(String name, int parentId, int parentTable ) {

        DbDao dd = new DbDao();
        ResultSet rs;
        Map<String, String> data = new HashMap<String, String>();
        int table=parentTable+1;
        try {
            if (parentTable==0) {
                dd.insert("insert into "+tables[table]+" (name) values (?)", name);
                rs = dd.query("select id from "+tables[table]+" where name=?", name);
                if (rs.next())
                    data.put("id", table+"_"+ rs.getString("id"));
            } else {
                dd.insert("insert into "+tables[table]+" (name, "+tables[parentTable]+"_id) values (?, ?)", name, parentId);
                rs = dd.query("select id from "+tables[table]+" where name=? and "+tables[parentTable]+"_id=?", name, parentId);
                if (rs.next())
                    data.put("id", table + "_"+rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping("/editGroup")
    @ResponseBody
    private void editGroup(String name, int id, int table) {
        DbDao dd = new DbDao();
        try {
            dd.modify("update "+tables[table]+" set name=? where id=? ", name, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delGroup")
    @ResponseBody
    private Map<String, String> delGroup(String id, int table) {
        Map<String, String> result=new HashMap<String, String>();
        String status="true";
        DbDao dd = new DbDao();
        try {
            dd.modify("delete from "+tables[table]+" where id=? ", id);
        } catch (Exception e) {

            status="false";
        }
        result.put("isok", status);
        return result;
    }

    @RequestMapping("/moveGroup")
    @ResponseBody
    private Map<String, String> moveGroup(int id, int table, int parentId, int parentTable) {
        Map<String, String> result=new HashMap<String, String>();
        String status="true";
        if(table!=parentTable+1) {
            result.put("status", "false");
            return result;
        }
        DbDao dd = new DbDao();
        try {
            dd.modify("update "+tables[table]+" set "+tables[parentTable]+"_id =? where id=? ", parentId, id);
        } catch (Exception e) {

            status="false";
        }
        result.put("isok", status);
        return result;
    }

}