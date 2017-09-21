package cn.iie.icm.action.api;

import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.pojo.ClientFormPojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ClientFormRESTController {

    @RequestMapping(value="saveClientForm")
    public String form(Map<String, Object> map, HttpServletRequest request) {
        String userid=request.getParameter("UserId");
        String usertoken=request.getParameter("UserToken");
        String name=request.getParameter("name");
        String formToken=request.getParameter("formToken");
        String Json=request.getParameter("Json");
        String time=System.currentTimeMillis()+"";

        ClientFormPojo Cp=new ClientFormPojo();
        ClientFormDao Cd=new ClientFormDao();
        JSONObject jsonObj=new JSONObject();

        Cp.setFormtoken(formToken);
        Cp.setJson(Json);
        Cp.setName(name);
        Cp.setTime(time);
        Cp.setUserid(userid);

        try {
            Cd.saveFormRes(Cp);
            jsonObj.put("res","OK");
        }
        catch (Exception e)
        {
            jsonObj.put("res",e);
        }
        finally
        {
            map.put("json",jsonObj.toString());
        }

        return "api";
    }
}
