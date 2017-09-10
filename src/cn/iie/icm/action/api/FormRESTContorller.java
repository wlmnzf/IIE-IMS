package cn.iie.icm.action.api;

import cn.iie.icm.dao.formDao;
import cn.iie.icm.pojo.FormPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.lang.Object;
import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;


@Controller
public class FormRESTContorller {

    @RequestMapping(value="Form/{userid}/{usertoken}/{page}/")
    public String form(Map<String, Object> map,@PathVariable("userid") String userid,@PathVariable("usertoken") String usertoken,
                                     @PathVariable("page") String page){

        JSONObject  jsonObj = new JSONObject();
        jsonObj.put("info",userid);
        jsonObj.put("usertoken",usertoken);
        jsonObj.put("page",page);

         map.put("json", jsonObj.toString());
         return "api";
        //具体代码略
    }

    @RequestMapping(value="formToken/{userid}/{usertoken}/")
    public String formToken(Map<String, Object> map,@PathVariable("userid") String userid,@PathVariable("usertoken") String usertoken){

        //比对userid和token是否合法

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Random r=new java.util.Random();
        JSONObject  jsonObj = new JSONObject();

        String sh=df.format(new Date()).toString();
        sh+=r.nextInt(10);

        jsonObj.put("formToken",sh);



//        finally {
            map.put("json", jsonObj.toString());
            return "api";
//        }

    }

    @RequestMapping(value="saveForm/{userid}/{usertoken}/")
    public String saveForm(Map<String, Object> map, @PathVariable("userid") String userid, @PathVariable("usertoken") String usertoken,  HttpServletRequest request){

        String formToken=request.getParameter("formToken");
        String json=request.getParameter("json");
        JSONObject  jsonObj = new JSONObject();

        FormPojo fp=new FormPojo();
        fp.setFormToken(formToken);
        fp.setUserId(userid);
        fp.setJson(json);

        formDao fd=new formDao();
        try {
            fd.addForm(fp);
            jsonObj.put("res","OK");
        }
        catch(Exception e)
        {
            jsonObj.put("res",e);
        }
        finally {
            map.put("json", jsonObj.toString());
            return "api";
        }

    }

}
