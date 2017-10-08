package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import cn.iie.icm.util.DbDao;
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
@RequestMapping("/personal")
public class PersonalController {

    @RequestMapping("/list")
    @ResponseBody
    private Map<String, Object> list(@RequestParam(value="pageSize" ,required=false) Integer pageSize, @RequestParam(value="pageNumber" ,required=false) Integer pageNumber, String roomId) {

        String selectSql="";
        if(!roomId.equals("")){
            selectSql="where troom.id = "+roomId;
        }
        Map<String, Object> result=new HashMap<String, Object>();
        List<Map<String, Object>> persons=new ArrayList<Map<String,Object>>();
        Map<String, Object> person;
        int total=0, i=0;
        try {
            DbDao dd = new DbDao();
            ResultSet rs;
            if(pageSize!=null)
                rs = dd.query("select tperson.id, num, tperson.name, troom.name, tgroup.name from tperson inner join tgroup on tgroup_id=tgroup.id inner join troom on troom_id=troom.id "+selectSql+" order by id limit ?, ?", (pageNumber - 1) * pageSize, pageSize);
            else
                rs = dd.query("select tperson.id, num, tperson.name, troom.name, tgroup.name from tperson inner join tgroup on tgroup_id=tgroup.id inner join troom on troom_id=troom.id "+selectSql+"order by id");
            while (rs.next()) {
                person=new HashMap<String, Object>();
                if(pageSize!=null)
                    person.put("recordId",pageSize*(pageNumber-1)+(++i) );
                else
                    person.put("recordId", ++i);
                person.put("id", rs.getString("tperson.id"));
                person.put("num", rs.getString("num"));
                person.put("name", rs.getString("tperson.name"));
                person.put("room", rs.getString("troom.name"));
                person.put("group", rs.getString("tgroup.name"));
                persons.add(person);
            }
            rs = dd.query("select count(*) from tperson inner join tgroup on tgroup_id=tgroup.id inner join troom on troom_id=troom.id "+selectSql);
            if(rs.next())
                total = rs.getInt(1);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        result.put("total", total);
        result.put("rows", persons);
        return result;
    }

    @RequestMapping("/getGroup")
    @ResponseBody
    private List<Map<String, String>> getGroup( String roomId)  {
        List<Map<String, String>> groups=new ArrayList<Map<String,String >>();
        Map<String, String> group;
        DbDao dd=new DbDao();
        ResultSet rs;
        try {
            rs = dd.query("select id, name from tgroup where troom_id = ?", roomId);
            while (rs.next()){
                group=new HashMap<String, String>();
                group.put("id", rs.getString("id"));
                group.put("name", rs.getString("name"));
                groups.add(group);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  groups;
    }

    @RequestMapping("/getRoom")
    @ResponseBody
    private List<Map<String, String>> getRoom()  {
        List<Map<String, String>> rooms=new ArrayList<Map<String,String >>();
        Map<String, String> room;
        DbDao dd=new DbDao();
        ResultSet rs;
        try {
            rs = dd.query("select id, name from troom ");
            while (rs.next()){
                room=new HashMap<String, String>();
                room.put("id", rs.getString("id"));
                room.put("name", rs.getString("name"));
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rooms;
    }

    @RequestMapping("/getPerson")
    @ResponseBody
    private Map<String, Object> getPerson( String id)  {
        Map<String, Object> result=new HashMap<String, Object>();
        DbDao dd=new DbDao();
        ResultSet rs;
        try {
            rs=dd.query("select tperson.id, tperson.name, num, troom.id, tgroup.id from tperson inner join tgroup on tgroup_id=tgroup.id inner join troom on troom_id=troom.id  where tperson.id=?", id);
            if(rs.next()){
                result.put("groupId", rs.getString("tgroup.id"));
                result.put("roomId", rs.getString("troom.id"));
                result.put("name", rs.getString("tperson.name"));
                result.put("num", rs.getString("num"));
                result.put("id", rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

    @RequestMapping("/editPerson")
    @ResponseBody
    private void editPerson(@RequestParam(value="id", required = false) String id, String num, String name, String groupId){
        DbDao dd = new DbDao();
        try {
            if(id!=null)
                dd.modify("update tperson set name=?, num=?, tgroup_id=? where id=? ", name, num, groupId, id);
            else
                dd.insert("insert into tperson (name, num, tgroup_id) values (?, ?, ?)", name, num, groupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delPerson")
    @ResponseBody
    private void delPerson( String id){
        DbDao dd = new DbDao();
        try {
            dd.modify("delete from tperson where id=? ", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/batchDelPerson")
    @ResponseBody
    private Map<String, String> batchDelPerson( String ids){
        DbDao dd = new DbDao();
        String status="success";
        Map<String, String> result=new HashMap<String, String>();
        try {
            for(String id: ids.split(","))
                dd.modify("delete from tperson where id=? ", id);
        } catch (Exception e) {
            e.printStackTrace();
            status="failed";
        }
        result.put("status", status);
        return  result;
    }

}


@Controller
class PersonViewController {
    @RequestMapping("/personalManage")
    private String customForm(Map<String, Object> map, HttpServletRequest request, RedirectAttributes attr)
    {
        map.put("curPage",1);

        int res= comm.Login.validCheck(request,2,map);
        if(res==0) {
            return "personalManage";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }
    }
}