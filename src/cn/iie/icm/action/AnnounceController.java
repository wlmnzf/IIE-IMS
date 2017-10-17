package cn.iie.icm.action;

import cn.iie.icm.Bean.AncJDBCTemplate;
import cn.iie.icm.Bean.AnnounceMent;
import cn.iie.icm.Bean.Pager;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class AnnounceController {

    private List<AnnounceMent> datalist;

    //展示公告，管理公告
    @RequestMapping("/announceManagement")
    private String toAnnounceMag(HttpServletRequest request, ModelMap model){
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        List<AnnounceMent> announceMents = ancJDBCTemplate.listAnnouncements();
        datalist = tinyAncList(announceMents);
        int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);
        return "announceManagement";
    }

    @RequestMapping("/announceEditor")
    private String toAnnounceEdit(){
        return "announceEditor" ;
    }

    /**
     * 将公告录入到tanc_manger表中
     * @param request
     */
    @RequestMapping("/addAnc")
    @ResponseBody
    public void addAnc(HttpServletRequest request){

        System.out.println("come in hhhh");
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String level = request.getParameter("checkbox");
        String type = request.getParameter("checkbox1");
        String groupidString = request.getParameter("groupid");
        Date date = new Date();
        //获取公告发布者
        Cookie[] cookies = request.getCookies();
        String announcer = null;
        int status = 0;
        for (int i = 0;i < cookies.length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("login")){
                String jsonString = java.net.URLDecoder.decode(cookie.getValue());
                JSONObject jsonObject = new JSONObject(jsonString);
                announcer = jsonObject.getString("account");
                break;
            }
        }
        //生成公告发布的时间
        Timestamp timestamp  = new Timestamp(date.getTime());
        int groupid = Integer.parseInt(groupidString);
        ancJDBCTemplate.create(title,text,groupid,level,timestamp,announcer,type);

        //return status;
       // return "editSuccess";
    }



    //分页展示公告内容
    @RequestMapping("/pageManageMent")
    public String pageManagement(HttpServletRequest request,ModelMap model){

        String pagestr = (String)request.getParameter("currentPage");
        int pageNum = Integer.parseInt(pagestr);
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);
        return "announceManagement";

    }

    //展示公告详细内容
    @RequestMapping("/showAnc")
    public String showAnc(HttpServletRequest request,ModelMap model){
        String title = request.getParameter("title");
        try {
            title = new String(title.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int index = 0;

        for(int i = 0;i < datalist.size();i++){
            if (title.equals(datalist.get(i).getTitle())){
                index = i;
                break;
            }

        }
        AnnounceMent anc = datalist.get(index);
        model.addAttribute("anc",anc);

        return "showAnc";
    }

    @RequestMapping("/deleteAnc")
    public String deleteAnc(HttpServletRequest request, ModelMap model){
        String title = request.getParameter("title");
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        try {
            title = new String(title.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ancJDBCTemplate.deleteByTitle(title);
        //datalist = ancJDBCTemplate.listAnnouncements();
        datalist = tinyAncList(ancJDBCTemplate.listAnnouncements());
        /*int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);*/
        String pagestr = (String)request.getParameter("currentPage");

        int pageNum = Integer.parseInt(pagestr);
        System.out.println("/////////" + pageNum);
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);

        return "announceManagement";

    }

    //置顶操作
    @RequestMapping("/upperShow")
    public String upperShow(HttpServletRequest request,ModelMap model){
        String title = request.getParameter("title");
        int index = 0;
        try {
            title = new String (title.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < datalist.size();i++){
            if (datalist.get(i).getTitle().equals(title)){
                index = i;
                break;
            }
        }

        //设置置顶信息为true
       // datalist.get(index).setStickly(1);
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        ancJDBCTemplate.updateStickly(1,title);
        datalist = tinyAncList(ancJDBCTemplate.listAnnouncements());
        int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);

        return "announceManagement";

    }



  /*  //客户端展示公告
    @RequestMapping("/")
    public String ancShowClient(HttpServletRequest request , ModelMap model){
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        List<AnnounceMent> announceMents = ancJDBCTemplate.listAnnouncements();
        datalist_client = announceMents;
       // System.out.println("***********&&&&&&" + datalist_client.size());
        *//*int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage_c_time");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist_c_time,pageNum);
        model.addAttribute("announceMents_c_time",pager.getDatalist());
        model.addAttribute("page_c_time",pager);*//*
        //Object text = request.getParameter("event");
        model.addAttribute("datalist_client",datalist_client);
        return "announceShow";

    }*/


    //取消置顶
    @RequestMapping("/cancelStickly")
    public String cancelStickly(HttpServletRequest request , ModelMap model){
        String title = request.getParameter("title");
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        try {
            title = new String(title.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ancJDBCTemplate.updateStickly(0,title);
        List<AnnounceMent> announceMents = ancJDBCTemplate.listAnnouncements();
        datalist = tinyAncList(announceMents);
        String pagestr = (String)request.getParameter("currentPage");
        int pageNum = Integer.parseInt(pagestr);
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);

        return "announceManagement";
    }

    //此函数用于整理置顶公告的排列顺序
    public List<AnnounceMent> tinyAncList(List<AnnounceMent> announceMents){
        List<AnnounceMent> temp = new ArrayList<AnnounceMent>();
        List<AnnounceMent> datalist = new ArrayList<AnnounceMent>();
        if(announceMents == null){
            return  null;
        }
        for (int i = 0 ; i < announceMents.size() ; i++){
            if(announceMents.get(i).getStickly() == 1){
                System.out.println("i = " + i + "title = " + announceMents.get(i).getTitle() + " stickly = " + announceMents.get(i).getStickly());
                datalist.add(announceMents.get(i));
            }else {
                temp.add(announceMents.get(i));
            }
        }
        datalist.addAll(temp);
        return datalist;
    }

}
