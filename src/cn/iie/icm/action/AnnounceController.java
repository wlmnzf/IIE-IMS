package cn.iie.icm.action;

import cn.iie.icm.Bean.AncJDBCTemplate;
import cn.iie.icm.Bean.AnnounceMent;
import cn.iie.icm.Bean.Pager;
import org.apache.xpath.operations.Mod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class AnnounceController {

    private List<AnnounceMent> datalist;

    //展示公告，管理公告
    @RequestMapping("/announceMagement")
    private String toAnnounceMag(HttpServletRequest request, ModelMap model){
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        List<AnnounceMent> announceMents = ancJDBCTemplate.listAnnouncements();
        datalist = announceMents;
        int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(announceMents,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);
        return "announceMagement";
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
    public String addAnc(HttpServletRequest request){
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String level = request.getParameter("checkbox");
        try {
            level = new String(level.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String groupidString = request.getParameter("groupid");
        Date date = new Date();
        //获取公告发布者
        String author = "admin";
        //生成公告发布的时间
        Timestamp timestamp  = new Timestamp(date.getTime());
        //int level = Integer.parseInt(levelString);
        int groupid = Integer.parseInt(groupidString);
        ancJDBCTemplate.create(title,text,groupid,level,timestamp,author);
        return "editSuccess";
    }

    //分页展示公告内容
    @RequestMapping("/pageManageMent")
    public String pageManagement(HttpServletRequest request,ModelMap model){

        String pagestr = (String)request.getParameter("currentPage");
        int pageNum = Integer.parseInt(pagestr);
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);
        return "announceMagement";

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
        ancJDBCTemplate.deleteByTitle(title);
        datalist = ancJDBCTemplate.listAnnouncements();
        int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);

        return "announceMagement";

    }

    //置顶操作
    @RequestMapping("/upperShow")
    public String upperShow(HttpServletRequest request,ModelMap model){
        String title = request.getParameter("title");
        int index = 0;
        for(int i = 0; i < datalist.size();i++){
            if (datalist.get(i).getTitle().equals(title)){
                index = i;
                break;
            }
        }

        Collections.swap(datalist,0,index);
        for (int j = 1;j < index - 1;j++){
            Collections.swap(datalist,j,index);
        }

        int pageNum = 1;
        String pagestr = (String)request.getParameter("currentPage");
        if( pagestr!= null){
            pageNum = Integer.parseInt(pagestr);
        }
        Pager pager = new Pager(datalist,pageNum);
        model.addAttribute("announceMents",pager.getDatalist());
        model.addAttribute("page",pager);

        return "announceMagement";

    }
}
