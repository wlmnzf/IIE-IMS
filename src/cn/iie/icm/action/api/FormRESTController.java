package cn.iie.icm.action.api;

import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.ClientFormPojo;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.TypePojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Object;
import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;


@Controller
public class FormRESTController {

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

    @RequestMapping(value="saveForm")
    public String saveForm(Map<String, Object> map, HttpServletRequest request){

        String formToken=request.getParameter("formToken");
        String json=request.getParameter("json");
        String type=request.getParameter("type");
        String title=request.getParameter("title");
        String deadline=request.getParameter("deadline");
        JSONObject  jsonObj = new JSONObject();


        JSONObject loginObj =comm.Login.getLoginInfo(request);
        String userName = (String) loginObj.get("account");
        String userToken = (String) loginObj.get("token");

        FormPojo fp=new FormPojo();
        fp.setFormToken(formToken);
        fp.setUserId(userName);
        fp.setJson(json);
        fp.setTime(System.currentTimeMillis()+"");
        fp.setType( Integer.parseInt(type));//其实应该检验一下
        fp.setTitle(title);
        fp.setDeadline(deadline);

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


    @RequestMapping(value="getJson")
    public String getJson(Map<String, Object> map, HttpServletRequest request)
    {
        String formToken=request.getParameter("formToken");
        String userId=comm.Login.getLoginInfo(request).get("account").toString();
        FormPojo fp=new FormPojo();
        ClientFormPojo cfp=new ClientFormPojo();
        formDao fd=new formDao();
        ClientFormDao cfd=new ClientFormDao();

        JSONObject  jsonObj = new JSONObject();

        try {
            cfp=cfd.getFormResById(formToken,userId);
            fp = fd.getFormById(formToken);
            jsonObj.put("needCheck",(fp==null)?"":fp.getNeedCheck());
            jsonObj.put("checkOption",(fp==null||fp.getCheckOption()==null)?"":fp.getCheckOption());
            jsonObj.put("result",cfp==null?"":cfp.getJson());
            jsonObj.put("json",fp==null?"":fp.getJson());
            jsonObj.put("isChecked",cfp==null?"":cfp.getIsChecked());
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



    @RequestMapping(value="Result/{formToken}/{page}/")
    public String formResult(Map<String, Object> map, @PathVariable("formToken") String formToken, @PathVariable("page") int page,  HttpServletRequest request)
    {
        List<ClientFormPojo> fp=new ArrayList<ClientFormPojo>();
        ClientFormDao cfd=new ClientFormDao();
        JSONObject  jsonObj = new JSONObject();
        JSONObject  pagesObj = new JSONObject();

        int pageCnt = 10;
        int resNum =cfd.getTotal();
        int pagesNum = resNum / pageCnt + (resNum % pageCnt == 0 ? 0 : 1);

        fp=cfd.getFormsResWithLimit(page,pageCnt,formToken);


        pagesObj.put("total",resNum);
        pagesObj.put("num",pagesNum);
        pagesObj.put("cur",page);

        jsonObj.put("info",fp);
        jsonObj.put("page",pagesObj);
        jsonObj.put("res","OK");

        map.put("json", jsonObj.toString());
        return "api";
    }

    @RequestMapping(value="needCheck")
    public String formTitle(Map<String, Object> map, HttpServletRequest request)
    {
        String formToken=request.getParameter("formToken");
        String flag=request.getParameter("flag");
        String json=request.getParameter("json");
        JSONObject  jsonObj = new JSONObject();

        formDao fd=new formDao();
        ClientFormDao cfd=new ClientFormDao();
        try {
            fd.updateCheck(formToken, flag.equals("1") ? 1 : 0, json);
            //此处还需要清除所有结果的验证标志，进行重新审核
            cfd.clearAllChecked(formToken);
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



    }
