package cn.iie.icm.action.api;

import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.pojo.ClientFormPojo;
import cn.iie.icm.pojo.FormPojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Controller
public class ClientFormRESTController {

    @RequestMapping(value="saveClientForm")
    public String form(Map<String, Object> map, HttpServletRequest request) {

        String formToken=request.getParameter("formToken");
        String Json=request.getParameter("Json");
        String Block=request.getParameter("block");
        String time=System.currentTimeMillis()+"";

        ClientFormPojo Cp=new ClientFormPojo();
        ClientFormDao Cd=new ClientFormDao();
        JSONObject jsonObj=new JSONObject();

        JSONObject loginObj=comm.Login.getLoginInfo(request);

        Cp.setFormtoken(formToken);
        Cp.setJson(Json);
        Cp.setName((String)loginObj.get("name"));
        Cp.setTime(time);
        Cp.setUserid((String)loginObj.get("account"));
        if(Block.equals("uncorrect"))
        {
            Cp.setIsChecked(1);
        }

        try {
            ClientFormPojo tmp=Cd.getFormResById(formToken,(String)loginObj.get("account"));
            if(tmp!=null)
                Cd.updateFormRes(Cp);
            else
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

    @RequestMapping(value="uncorrect/{page}/")
    public String uncorrect(Map<String, Object> map, @PathVariable("page") int page, HttpServletRequest request) {
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
            int resNum =fd.getUncheckedTotal(userName);
            int pagesNum = resNum / pageCnt + (resNum % pageCnt == 0 ? 0 : 1);

            List<FormPojo> forms=fd.getFormsResWithoutChecked(userName,page,pageCnt);


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


    @RequestMapping(value="all/{page}/")
    public String all(Map<String, Object> map, @PathVariable("page") int page, HttpServletRequest request) {
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
            int resNum =fd.getTotal();
            int pagesNum = resNum / pageCnt + (resNum % pageCnt == 0 ? 0 : 1);

            List<FormPojo> forms=fd.getFormsWithLimit(page,pageCnt);
//            List<FormPojo> forms=fd.getFormsWithLimit(page,pageCnt);


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

    @RequestMapping(value="export/{formToken}/")
    public void exportExcel(Map<String, Object> map, @PathVariable("formToken") String formToken, HttpServletRequest request, HttpServletResponse res) {

        formDao fd=new formDao();
        ClientFormDao cfd=new ClientFormDao();

        FormPojo fp= fd.getFormById(formToken);
        List<ClientFormPojo>  cfp=cfd.getFormResByFormToken(formToken);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date=df.format(new Date()).toString();

        String fileName = fp.getTitle()+date;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        comm.Excel.writeExcel(os,cfp,fp);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ServletOutputStream out=null;
        // 设置response参数，可以打开下载页面
        try {
            res.reset();
            res.setContentType("application/vnd.ms-excel;charset=utf-8");
            res.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            out = res.getOutputStream();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

//        return "api";
    }


    @RequestMapping(value = "delRes/")
    public String delForm(Map<String, Object> map, HttpServletRequest request)
    {
        String formToken=request.getParameter("formToken");
        String userId=request.getParameter("userId");
        JSONObject json=new JSONObject();
        if(comm.Login.validCheck(request,2)==0) {
            ClientFormDao cfd=new ClientFormDao();
            cfd.deleteFormRes(formToken,userId);
            json.put("res","OK");
        }
        else
        {
            json.put("res","Login Error");
        }

        map.put("json",json.toString());
        return "api";
    }

    @RequestMapping(value = "delRes/m")
    public String delFormMulti(Map<String, Object> map, HttpServletRequest request)
    {
        String data=request.getParameter("data");
        JSONObject json=new JSONObject();
        if(comm.Login.validCheck(request,2)==0) {
            try {
                ClientFormDao cfd = new ClientFormDao();
                JSONObject tokens = new JSONObject(data);
                String formToken = tokens.get("formToken").toString();
                for (String o : tokens.getJSONObject("res").keySet()) {
                    cfd.deleteFormRes(formToken, tokens.getJSONObject("res").get(o).toString());
                }

                json.put("res", "OK");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                json.put("res", "Error");
            }
        }
        else
        {
            json.put("res","Login Error");
        }

        map.put("json",json.toString());
        return "api";
    }



}
