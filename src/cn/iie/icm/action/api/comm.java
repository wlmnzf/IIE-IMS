package cn.iie.icm.action.api;
import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.dao.PersonDao;
import cn.iie.icm.pojo.ClientFormPojo;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.PersonPojo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import java.io.*;
import jxl.*;
import jxl.write.*;
import jxl.write.biff.LabelRecord;
import jxl.write.biff.RowsExceededException;
import jxl.format.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.Color;
import jxl.write.Number;
import jxl.write.Boolean;

public  class comm {
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String MD5_32(String SourceString) throws Exception {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(SourceString.getBytes());
        byte messageDigest[] = digest.digest();
        return toHexString(messageDigest);
    }

    public static String MD5_16(String SourceString) throws Exception {
        return MD5_32(SourceString).substring(8, 24);
    }

    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    public static class  Excel
    {
        /**
         * 输出Excel
         *
         * @param os
         */
        public static WritableWorkbook writeExcel(OutputStream os, List<ClientFormPojo> lists, FormPojo fp)
        {
            WritableWorkbook wwb=null;
            try
            {
                /**
                 * 只能通过API提供的工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，
                 * 因为类WritableWorkbook的构造函数为protected类型
                 * method(1)直接从目标文件中读取WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));
                 * method(2)如下实例所示 将WritableWorkbook直接写入到输出流

                 */
                wwb = Workbook.createWorkbook(os);
                //创建Excel工作表 指定名称和位置
                WritableSheet ws = wwb.createSheet("Sheet 1",0);

                //**************往工作表中添加数据*****************

                //1.添加Label对象

                List<String> titles=new ArrayList<String>();
                titles.add( "姓名");
                titles.add("学号");
                titles.add("时间");

                JSONArray titleObj=new JSONArray(fp.getJson());
                for(int o=0;o<titleObj.length();o++)
                {
                    if(titleObj.getJSONObject(o).get("type").equals("t1"))
                    {
                        titles.add(titleObj.getJSONObject(o).getJSONObject("data").get("label").toString());
                    }
                    else if (titleObj.getJSONObject(o).get("type").equals("t2"))
                    {
                        titles.add(titleObj.getJSONObject(o).getJSONObject("data").get("label").toString());
                    }
                    else if (titleObj.getJSONObject(o).get("type").equals("t3"))
                    {
                        titles.add(titleObj.getJSONObject(o).getJSONObject("data").get("0").toString());
                    }
                    else if (titleObj.getJSONObject(o).get("type").equals("t4"))
                    {
                        titles.add(titleObj.getJSONObject(o).getJSONObject("data").get("0").toString());
                    }

                }

                for(int i=0;i<titles.size();i++)
                {
                    Label label = new Label(i,0,titles.get(i));
                    ws.addCell(label);
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for(int i=0;i<lists.size();i++)
                {
                    Label label = new Label(0,i+1,lists.get(i).getName());
                    ws.addCell(label);

                    Label label1 = new Label(1,i+1,lists.get(i).getUserid());
                    ws.addCell(label1);

                    long lt = new Long(lists.get(i).getTime());
                    Date date = new Date(lt);
                    Label label2 = new Label(2,i+1,simpleDateFormat.format(date));
                    ws.addCell(label2);

                    JSONObject contentObj=new JSONObject(lists.get(i).getJson());
                    int index=3;
                    for(String o:contentObj.keySet())
                    {
                        Label label3 =null;

                        if(contentObj.getJSONObject(o).get("type").equals("t1"))
                        {
                            label3= new Label(index,i+1,contentObj.getJSONObject(o).get("data").toString());
                        }
                        else if (contentObj.getJSONObject(o).get("type").equals("t2"))
                        {
                            label3= new Label(index,i+1,contentObj.getJSONObject(o).get("data").toString());
                        }
                        else if (contentObj.getJSONObject(o).get("type").equals("t3"))
                        {
                            label3= new Label(index,i+1,contentObj.getJSONObject(o).getJSONObject("data").get("content").toString());
                        }
                        else if (contentObj.getJSONObject(o).get("type").equals("t4"))
                        {
                            label3= new Label(index,i+1,contentObj.getJSONObject(o).getJSONObject("data").get("content").toString());
                        }

                        ws.addCell(label3);
                        index++;
                    }

                }



                //添加带有字型Formatting对象
//                WritableFont wf = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,true);
//                WritableCellFormat wcf = new WritableCellFormat(wf);
//                Label labelcf = new Label(1,0,"this is a label test",wcf);
//                ws.addCell(labelcf);

                //添加带有字体颜色的Formatting对象
//                WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,
//                        UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);
//                WritableCellFormat wcfFC = new WritableCellFormat(wfc);
//                Label labelCF = new Label(1,0,"This is a Label Cell",wcfFC);
//                ws.addCell(labelCF);

                //2.添加Number对象
//                Number labelN = new Number(0,1,3.1415926);
//                ws.addCell(labelN);

                //添加带有formatting的Number对象
//                NumberFormat nf = new NumberFormat("#.##");
//                WritableCellFormat wcfN = new WritableCellFormat(nf);
//                Number labelNF = new jxl.write.Number(1,1,3.1415926,wcfN);
//                ws.addCell(labelNF);

//                //3.添加Boolean对象
//                Boolean labelB = new jxl.write.Boolean(0,2,false);
//                ws.addCell(labelB);

//                //4.添加DateTime对象
//                jxl.write.DateTime labelDT = new jxl.write.DateTime(0,3,new java.util.Date());
//                ws.addCell(labelDT);

                //添加带有formatting的DateFormat对象
//                DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
//                WritableCellFormat wcfDF = new WritableCellFormat(df);
//                DateTime labelDTF = new DateTime(1,3,new java.util.Date(),wcfDF);
//                ws.addCell(labelDTF);


//                //添加图片对象,jxl只支持png格式图片
//                File image = new File("d://2.png");
//                WritableImage wimage = new WritableImage(0,1,2,2,image);
//                ws.addImage(wimage);
                //写入工作表
                wwb.write();
                wwb.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally {
                return wwb;
            }

        }







    }

//}

    /*
    0:OK
    1:Login failed
    2:Auth failed
     */
    public static class Login {
        public static String errRedirect(RedirectAttributes attr, int res) {
            switch (res) {
                case 0:
                case 1:
                    return setErrInfo(attr, "登录已失效，请重新登陆！");
                case 2:
                    return setErrInfo(attr, "对不起，您没有权限访问此页面");
                case 3:
                    return setErrInfo(attr, "退出成功!");
            }
            return "redirect:/index";
        }
//        public  static String Check(HttpServletRequest request,int authType,RedirectAttributes attr,Map<String)
//        {
//            int res=validCheck(request,authType,attr,null);
//            switch(res)
//            {
//                case 0:
//                case 1:return setErrInfo(attr,"登录已失效，请重新登陆！");
//                case 2:return  setErrInfo(attr,"对不起，您没有权限访问此页面");
//            }
//            return ;
//        }

        public static int validCheck(HttpServletRequest request, int authType) {
            return validCheck(request, authType, (HttpSession) null);
        }

        public static int validCheck(HttpServletRequest request, int authType, HttpSession session) {
            JSONObject jsonObj = getLoginInfo(request);
            PersonDao pd = new PersonDao();
            PersonPojo pp = new PersonPojo();


            if (jsonObj != null) {
                try {
                    String userName = (String) jsonObj.get("account");
                    String userToken = (String) jsonObj.get("token");

                    pp = pd.getPerson(userName);

                    if (!authCheck(authType, pp)) {
                        return 2;
                    }

                    if (pp.getToken().equals(userToken)) {
                        if (session != null)
                            setLoginInfo(session, pp);
                        return 0;
                    }
                } catch (Exception e) {
                    return 1;
                }
            }
            return 1;
        }

        public static int validCheck(HttpServletRequest request, int authType, Map<String, Object> map) {
            Cookie login = comm.getCookieByName(request, "login");

            if (login != null) {
                try {
                    PersonDao pd = new PersonDao();
                    PersonPojo pp = new PersonPojo();

                    String jsonText = login.getValue();
                    jsonText = java.net.URLDecoder.decode(jsonText, "utf-8");
                    JSONObject jsonObj = new JSONObject(jsonText);
                    String userName = (String) jsonObj.get("account");
                    String userToken = (String) jsonObj.get("token");

                    pp = pd.getPerson(userName);

                    if (!authCheck(authType, pp)) {
                        return 2;
//                        setErrInfo(attr,"对不起，您没有权限访问此页面");
                    }

                    if (pp.getToken().equals(userToken)) {
                        if (map != null)
                            setLoginInfo(map, pp);
                        return 0;
                    }
                } catch (Exception e) {
                    return 1;
                }
            }
            return 1;
        }

        public static boolean authCheck(int authType, PersonPojo pp) {
            if (authType <= pp.getAuth()) {
                return true;
            }
            return false;
        }

        public static void setLoginInfo(HttpSession session, PersonPojo pp)
        {
            session.setAttribute("_USER_NAME",pp.getNum());
            session.setAttribute("_TOKEN",pp.getToken());
            session.setAttribute("_TYPE",pp.getAuth());
            session.setAttribute("_TYPE_TEXT",pp.getAuth()==1?"用户":"管理员");
            session.setAttribute("_HEAD_URL",pp.getHeadUrl());
            session.setAttribute("_NAME",pp.getName());
        }

        public static void  setLoginInfo(Map<String, Object>  map,PersonPojo pp)
        {
            map.put("_USER_NAME",pp.getNum());
            map.put("_TOKEN",pp.getToken());
            map.put("_TYPE",pp.getAuth());
            map.put("_TYPE_TEXT",pp.getAuth()==1?"用户":"管理员");
            map.put("_HEAD_URL",pp.getHeadUrl());
            map.put("_NAME",pp.getName());
        }

        public static  String setErrInfo(RedirectAttributes attr,String info)
        {
            attr.addFlashAttribute("info", info);
            return "redirect:/index";
        }

        public static JSONObject getLoginInfo(HttpServletRequest request)
        {
                 Cookie login= comm.getCookieByName(request,"login");

                 if(login==null)
                     return null;

                    String jsonText = login.getValue();
                    try {
                        jsonText = java.net.URLDecoder.decode(jsonText,"utf-8");
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        return null;
                    }

                    JSONObject jsonObj = new JSONObject(jsonText);
                   return jsonObj;
        }

    }




}
