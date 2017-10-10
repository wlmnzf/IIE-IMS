package cn.iie.icm.action;

import cn.iie.icm.Bean.AncJDBCTemplate;
import cn.iie.icm.Bean.AnnounceMent;
import cn.iie.icm.Bean.IndexAnc;
import cn.iie.icm.Bean.Pager;
import cn.iie.icm.action.api.comm;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AnnounceShowController {
    private List<AnnounceMent> datalist_client;
    private List<AnnounceMent> c_t_info;
    private List<AnnounceMent> c_t_act;
    private List<AnnounceMent> c_u_info;
    private List<AnnounceMent> c_u_act;
    private Pager p_c_t_info;
    private Pager p_c_t_act;
    private Pager p_c_u_info;
    private Pager p_c_u_act;

    @RequestMapping("/announceShow")
    private String show(HttpServletRequest request, Map<String, Object> map, RedirectAttributes attr) {

        int res= comm.Login.validCheck(request,1,map);
        if(res==0) {
            if(datalist_client == null){
                datalist_client = JDBCGetData();
            }
            c_t_info = new ArrayList<AnnounceMent>();
            c_t_act = new ArrayList<AnnounceMent>();
            c_u_info = new ArrayList<AnnounceMent>();
            c_u_act = new ArrayList<AnnounceMent>();
            for (int i = 0 ; i < datalist_client.size() ; i++){
                if(datalist_client.get(i).getLevel().equals("紧急")){
                    if(datalist_client.get(i).getType().equals("通知")){
                        c_u_info.add(datalist_client.get(i));
                    }else {
                        c_u_act.add(datalist_client.get(i));
                    }
                }

                if (datalist_client.get(i).getType().equals("通知")){
                    c_t_info.add(datalist_client.get(i));
                }else {
                    c_t_act.add(datalist_client.get(i));
                }

            }
            p_c_t_info = new Pager(c_t_info,1);
            p_c_t_act = new Pager(c_t_act,1);
            p_c_u_info = new Pager(c_u_info,1);
            p_c_u_act = new Pager(c_u_act,1);
            System.out.println("*********" + p_c_t_act.getTotalPage());
            request.setAttribute("p_c_t_info",p_c_t_info);
            request.setAttribute("p_c_t_act",p_c_t_act);
            request.setAttribute("p_c_u_info",p_c_u_info);
            request.setAttribute("p_c_u_act",p_c_u_act);
            request.setAttribute("anc_c_t_info",p_c_t_info.getDatalist());
            request.setAttribute("anc_c_t_act",p_c_t_act.getDatalist());
            request.setAttribute("anc_c_u_info",p_c_u_info.getDatalist());
            request.setAttribute("anc_c_u_act",p_c_u_act.getDatalist());
            return "announceShow";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }

    }



    private List<AnnounceMent> JDBCGetData(){
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        List<AnnounceMent> announceMents = ancJDBCTemplate.listAnnouncements();
        return  announceMents;

    }

    //分页处理
    @RequestMapping("/pagePro")
    @ResponseBody
    public List<AnnounceMent> pagePro(HttpServletRequest request){
        String aidstr = request.getParameter("aid");
        String index = request.getParameter("index");
        List<AnnounceMent> list = null;

        int aid = Integer.parseInt(aidstr);
        //index = new String(index.trim().getBytes("ISO-8859-1"),"UTF-8");
        int pN = Integer.parseInt(index);
        //System.out.println("%%%%%%%" + pN);


        //用于公告的查看权限设置
        /*Cookie[] cookies = request.getCookies();
        String usr = null;
        for (int i = 0;i < cookies.length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("login")){
                String jsonString = java.net.URLDecoder.decode(cookie.getValue());
                JSONObject jsonObject = new JSONObject(jsonString);
                usr = jsonObject.getString("account");
                break;
            }
        }*/


        switch (aid){
            case 1:
                p_c_t_info = new Pager(c_t_info,pN);
               // p_c_t_info.setCurrentPage(pN);
                //t_info_cp = pN;
                //request.setAttribute("t_info_cp",t_info_cp);
                list =  p_c_t_info.getDatalist();
                break;
            case 2:
                p_c_t_act = new Pager(c_t_act,pN);
                //t_act_cp = pN;
                //request.setAttribute("t_act_cp",t_act_cp);
                list = p_c_t_act.getDatalist();
                break;
            case 3:
                p_c_u_info = new Pager(c_u_info,pN);

                //u_info_cp = pN;
                //request.setAttribute("u_info_cp",u_info_cp);
                list = p_c_u_info.getDatalist();
                break;
            case 4:
                p_c_u_act = new Pager(c_u_act,pN);
                //u_act_cp = pN;
                //request.setAttribute("u_act_cp",u_act_cp);
                list = p_c_u_act.getDatalist();
                break;
        }
        //return list;
        list.get(0).timeFormat();

        return  list;
    }


    @RequestMapping("/indexShow1")
    @ResponseBody
    public List<AnnounceMent> indexShow1(HttpServletRequest request){
        System.out.println("come in");
        if(datalist_client == null){
            datalist_client = JDBCGetData();
        }

        c_t_info = new ArrayList<AnnounceMent>();
        c_t_act = new ArrayList<AnnounceMent>();
        c_u_info = new ArrayList<AnnounceMent>();
        c_u_act = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getLevel().equals("紧急")){
                if(datalist_client.get(i).getType().equals("通知")){
                    c_u_info.add(datalist_client.get(i));
                }else {
                    c_u_act.add(datalist_client.get(i));
                }
            }

            if (datalist_client.get(i).getType().equals("通知")){
                c_t_info.add(datalist_client.get(i));
            }else {
                c_t_act.add(datalist_client.get(i));
            }

        }
        p_c_t_info = new Pager(c_t_info,1);
        p_c_t_act = new Pager(c_t_act,1);
        p_c_u_info = new Pager(c_u_info,1);
        p_c_u_act = new Pager(c_u_act,1);


        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();

        if ((c_u_info.size() < 3)){
            for(int k = 0 ; k < c_u_info.size() ; k++){
                indexAncList.add(c_u_info.get(k));
            }
        }else {
            indexAncList.add(c_u_info.get(0));
            indexAncList.add(c_u_info.get(1));
            indexAncList.add(c_u_info.get(2));
        }

        return indexAncList;

    }

    @RequestMapping("/indexShow2")
    @ResponseBody
    public List<AnnounceMent> indexShow2(HttpServletRequest request){
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();

        if(datalist_client == null){
            datalist_client = JDBCGetData();
        }

        c_t_info = new ArrayList<AnnounceMent>();
        c_t_act = new ArrayList<AnnounceMent>();
        c_u_info = new ArrayList<AnnounceMent>();
        c_u_act = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getLevel().equals("紧急")){
                if(datalist_client.get(i).getType().equals("通知")){
                    c_u_info.add(datalist_client.get(i));
                }else {
                    c_u_act.add(datalist_client.get(i));
                }
            }

            if (datalist_client.get(i).getType().equals("通知")){
                c_t_info.add(datalist_client.get(i));
            }else {
                c_t_act.add(datalist_client.get(i));
            }

        }

        if ((c_u_act.size() < 3)){
            for(int t = 0 ; t < c_u_act.size() ; t++){
                indexAncList.add(c_u_act.get(t));
            }
        }else {
            indexAncList.add(c_u_act.get(0));
            indexAncList.add(c_u_act.get(1));
            indexAncList.add(c_u_act.get(2));
        }
        return indexAncList;
    }

    @RequestMapping("/indexShow3")
    @ResponseBody
    public List<AnnounceMent> indexShow3(HttpServletRequest request){
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();

        if(datalist_client == null){
            datalist_client = JDBCGetData();
        }

        c_t_info = new ArrayList<AnnounceMent>();
        c_t_act = new ArrayList<AnnounceMent>();
        c_u_info = new ArrayList<AnnounceMent>();
        c_u_act = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getLevel().equals("紧急")){
                if(datalist_client.get(i).getType().equals("通知")){
                    c_u_info.add(datalist_client.get(i));
                }else {
                    c_u_act.add(datalist_client.get(i));
                }
            }

            if (datalist_client.get(i).getType().equals("通知")){
                c_t_info.add(datalist_client.get(i));
            }else {
                c_t_act.add(datalist_client.get(i));
            }

        }

        if ((c_t_info.size() < 3)){
            for(int i = 0 ; i < c_t_info.size() ; i++){
                indexAncList.add(c_t_info.get(i));
            }
        }else {
            indexAncList.add(c_t_info.get(0));
            indexAncList.add(c_t_info.get(1));
            indexAncList.add(c_t_info.get(2));
        }
        return indexAncList;
    }

    @RequestMapping("/indexShow4")
    @ResponseBody
    public List<AnnounceMent> indexShow4(HttpServletRequest request){
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();
        if(datalist_client == null){
            datalist_client = JDBCGetData();
        }

        c_t_info = new ArrayList<AnnounceMent>();
        c_t_act = new ArrayList<AnnounceMent>();
        c_u_info = new ArrayList<AnnounceMent>();
        c_u_act = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getLevel().equals("紧急")){
                if(datalist_client.get(i).getType().equals("通知")){
                    c_u_info.add(datalist_client.get(i));
                }else {
                    c_u_act.add(datalist_client.get(i));
                }
            }

            if (datalist_client.get(i).getType().equals("通知")){
                c_t_info.add(datalist_client.get(i));
            }else {
                c_t_act.add(datalist_client.get(i));
            }

        }


        if ((c_t_act.size() < 3)){
            for(int j = 0 ; j < c_t_act.size() ; j++){
                indexAncList.add(c_t_act.get(j));
            }
        }else {
            indexAncList.add(c_t_act.get(0));
            indexAncList.add(c_t_act.get(1));
            indexAncList.add(c_t_act.get(2));
        }



        return indexAncList;

    }

    @RequestMapping("/showIndex")
    public String showIndexAnc(HttpServletRequest request,ModelMap model){
        String c_title = request.getParameter("c_title");
        try {
            c_title = new String(c_title.trim().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int index = 0;
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getTitle().equals(c_title)){
                index = i;
            }
        }
        AnnounceMent c_anc = datalist_client.get(index);
        model.addAttribute("c_anc",c_anc);
        return "showIndexAnc";
    }


}
