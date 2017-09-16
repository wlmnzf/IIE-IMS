package cn.iie.icm.action.api;

import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.TypePojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.lang.Object;
import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;


@Controller
public class FormRESTContorller {

    @RequestMapping(value="Form/{page}/")
    public String form(Map<String, Object> map,@PathVariable("page") int page, HttpServletRequest request){
        String userid=request.getParameter("UserId");
        String usertoken=request.getParameter("UserToken");
        JSONObject  jsonObj = new JSONObject();
        JSONObject  pagesObj = new JSONObject();

        formDao fd=new formDao();
        typeDao td=new typeDao();

        //需要当前页码，然后limit，然后total的数字，不要获取全面页面，傻逼

    try {
        int pageCnt = 10;
        int formNum = fd.getTotal();
        int pagesNum = formNum / pageCnt + (formNum % pageCnt == 0 ? 0 : 1);

        List<FormPojo> forms = fd.getFormsWithLimit(page, pageCnt);
        List<TypePojo> types=td.getAllType();

        pagesObj.put("total",formNum);
        pagesObj.put("num",pagesNum);
        pagesObj.put("cur",page);

        jsonObj.put("info",forms);
        jsonObj.put("page",pagesObj);
        jsonObj.put("type",types);
        jsonObj.put("res","OK");
    }
    catch (Exception e)
    {
        jsonObj.put("res",e);
    }

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
        String type=request.getParameter("type");
        String title=request.getParameter("title");
        JSONObject  jsonObj = new JSONObject();

        FormPojo fp=new FormPojo();
        fp.setFormToken(formToken);
        fp.setUserId(userid);
        fp.setJson(json);
        fp.setTime(System.currentTimeMillis()+"");
        fp.setType( Integer.parseInt(type));//其实应该检验一下
        fp.setTitle(title);

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


    @RequestMapping(value="getJson/{userid}/{usertoken}/")
    public String getJson(Map<String, Object> map, @PathVariable("userid") String userid, @PathVariable("usertoken") String usertoken,  HttpServletRequest request)
    {
        String formToken=request.getParameter("formToken");
        FormPojo fp=new FormPojo();
        formDao fd=new formDao();
        JSONObject  jsonObj = new JSONObject();

        try {
            fp = fd.getFormById(formToken);
            jsonObj.put("json",fp.getJson());
            jsonObj.put("res","OK");
        }
        catch(Exception e)
        {
            jsonObj.put("res",e);
        }
        finally
        {
            map.put("json", jsonObj.toString());
        }
        return "api";
    }



//    @RequestMapping(value="formResult/{formid}/{page}/")
//    public String formResult(Map<String, Object> map, @PathVariable("formid") String formid, @PathVariable("page") String page,  HttpServletRequest request)
//    {
//        FormPojo fp=new FormPojo();
//        formDao fd=new formDao();
//        JSONObject  jsonObj = new JSONObject();
//
//        return "api";
//    }

//    @RequestMapping(value="formTitle/{formtoken}/{page}/")
//    public String formTitle(Map<String, Object> map, @PathVariable("formtoken") String formToken, @PathVariable("page") String page,  HttpServletRequest request)
//    {
//        String userid=request.getParameter("UserId");
//        String usertoken=request.getParameter("UserToken");
//        FormPojo fp=new FormPojo();
//        TypePojo tp=new TypePojo();
//        typeDao td=new typeDao();
//        formDao fd=new formDao();
//
//        JSONObject  jsonObj = new JSONObject();
//
//        try {
//            fp = fd.getFormById(formToken);
//            jsonObj.put("title",fp.getTitle());
//            jsonObj.put("json",fp.getJson());
//            jsonObj.put("type",fp.getType());
//            jsonObj.put("types",td.getAllType());
//            jsonObj.put("res","OK");
//        }
//        catch(Exception e)
//        {
//            jsonObj.put("res",e);
//        }
//        finally
//        {
//            map.put("json", jsonObj.toString());
//        }
//
//        return "api";
//    }



    }
