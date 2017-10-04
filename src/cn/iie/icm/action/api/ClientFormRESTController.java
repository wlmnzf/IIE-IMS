package cn.iie.icm.action.api;

import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.pojo.ClientFormPojo;
import cn.iie.icm.pojo.FormPojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @RequestMapping(value="inputAll")
    public String inputAll(Map<String, Object> map, HttpServletRequest request) {
        if(comm.Login.validCheck(request,1)==0)
        {
            String page=request.getParameter("page");
//用户名什么的从Cookie中直接读取
        }
        else
        {
            map.put("json","认证出错");
        }

        return "api";
    }

    @RequestMapping(value="uninput/{page}/")
    public String uninput(Map<String, Object> map, @PathVariable("page") int page, HttpServletRequest request) {
        if(comm.Login.validCheck(request,1)==0)
        {
//            String page=request.getParameter("page");
            //用户名什么的从Cookie中直接读取
            Cookie login= comm.getCookieByName(request,"login");
            String jsonText = login.getValue();
            try {
                jsonText = java.net.URLDecoder.decode(jsonText, "utf-8");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            JSONObject loginObj = new JSONObject(jsonText);
            String userName = (String) loginObj.get("account");
            String userToken = (String) loginObj.get("token");

            JSONObject  jsonObj = new JSONObject();
            JSONObject  pagesObj = new JSONObject();
            formDao fd=new formDao();


            int pageCnt = 10;
            int resNum =fd.getEmptyTotal(userName);
            int pagesNum = resNum / pageCnt + (resNum % pageCnt == 0 ? 0 : 1);

            List<FormPojo> forms=fd.getFormsEmpty(userName,page,pageCnt);


            pagesObj.put("total",resNum);
            pagesObj.put("num",pagesNum);
            pagesObj.put("cur",page);

            jsonObj.put("info",forms);
            jsonObj.put("page",pagesObj);
            jsonObj.put("res","OK");

            map.put("json", jsonObj.toString());

        }
        else
        {
            map.put("json","认证出错");
        }

        return "api";
    }





}
