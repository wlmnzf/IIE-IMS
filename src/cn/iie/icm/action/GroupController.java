package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import cn.iie.icm.util.DbDao;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Map<String, String> data;
        data = new HashMap<String, String>();
        data.put("id", "academy");
        data.put("parent", "root");
        data.put("text", "信工所");
        datas.add(data);
        try {
            DbDao dd = new DbDao();
            ResultSet rs = dd.query("select id, name from troom ");
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "troom" + rs.getString("id"));
                data.put("parent", "academy");
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, troom_id from tgroup ");
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "tgroup" + rs.getString("id"));
                data.put("parent", "troom" + rs.getString("troom_id"));
                data.put("text", rs.getString("name"));
                datas.add(data);
            }

            rs = dd.query("select id, name, tgroup_id from tperson ");
            while (rs.next()) {
                data = new HashMap<String, String>();
                data.put("id", "tperson" + rs.getString("id"));
                data.put("parent", "tgroup" + rs.getString("tgroup_id"));
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
    private Map<String, String> addGroup(@RequestParam(value = "name") String name, @RequestParam(value = "parentId") String parentId, @RequestParam(value = "parentTable") String parentTable ) {

        DbDao dd = new DbDao();
        ResultSet rs;
        Map<String, String> data = new HashMap<String, String>();
        String table="";
        if(parentTable.equals("academy"))
            table="troom";
        else if(parentTable.equals("troom"))
            table="tgroup";
        else
            table="tperson";
        try {
            if (table.equals("troom")) {
                dd.insert("insert into "+table+" (name) values (?)", name);
                rs = dd.query("select id from "+table+" where name=?", name);
                if (rs.next())
                    data.put("id", "troom" + rs.getString("id"));
            } else {
                dd.insert("insert into "+table+" (name, "+parentTable+"_id) values (?, ?)", name, parentId);
                rs = dd.query("select id from "+table+" where name=? and "+parentTable+"_id=?", name, parentId);
                if (rs.next())
                    data.put("id", table + rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping("/editGroup")
    @ResponseBody
    private void editGroup(@RequestParam(value="name") String name, @RequestParam(value="id") String id, @RequestParam(value="table") String table) {
        DbDao dd = new DbDao();
        try {
                dd.modify("update "+table+" set name=? where id=? ", name, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delGroup")
    @ResponseBody
    private void delGroup(@RequestParam(value="id") String id, @RequestParam(value="table") String table) {
        DbDao dd = new DbDao();
        try {
            dd.modify("delete from "+table+" where id=? ", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


@Controller
class GroupViewController {
    @RequestMapping("/groupManage")
    private String customForm(Map<String, Object> map, HttpServletRequest request, RedirectAttributes attr)
    {
        map.put("curPage",1);

        int res= comm.Login.validCheck(request,2,map);
        if(res==0) {
            return "groupManage";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }
    }
}