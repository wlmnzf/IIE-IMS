package cn.iie.icm.action;

import cn.iie.icm.Bean.AncJDBCTemplate;
import cn.iie.icm.Bean.AnnounceMent;
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



            initList(request);
            p_c_t_info = new Pager(c_t_info,1);
            p_c_t_act = new Pager(c_t_act,1);
            p_c_u_info = new Pager(c_u_info,1);
            p_c_u_act = new Pager(c_u_act,1);
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



    public List<AnnounceMent> JDBCGetData(){
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





        switch (aid){
            case 1:
                p_c_t_info = new Pager(c_t_info,pN);
                list =  p_c_t_info.getDatalist();
                break;
            case 2:
                p_c_t_act = new Pager(c_t_act,pN);
                list = p_c_t_act.getDatalist();
                break;
            case 3:
                p_c_u_info = new Pager(c_u_info,pN);
                list = p_c_u_info.getDatalist();
                break;
            case 4:
                p_c_u_act = new Pager(c_u_act,pN);
                list = p_c_u_act.getDatalist();
                break;
        }
        list.get(0).timeFormat();

        return  list;
    }


    @RequestMapping("/indexShow1")
    @ResponseBody
    public List<AnnounceMent> indexShow1(HttpServletRequest request){

        List<AnnounceMent> list = JDBCGetData();
        List<AnnounceMent> t_Info = new ArrayList<AnnounceMent>();
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < list.size() ; i++){
            if(list.get(i).getType().equals("通知")){
                t_Info.add(list.get(i));
            }
        }
        t_Info = tinyAncList(t_Info);
        if ((t_Info.size() < 3)){
            for(int k = 0 ; k < t_Info.size() ; k++){
                indexAncList.add(t_Info.get(k));
            }
        }else {
            indexAncList.add(t_Info.get(0));
            indexAncList.add(t_Info.get(1));
            indexAncList.add(t_Info.get(2));
        }
        return indexAncList;
    }


    @RequestMapping("/indexShow2")
    @ResponseBody
    public List<AnnounceMent> indexShow2(HttpServletRequest request){
        List<AnnounceMent> list = JDBCGetData();
        List<AnnounceMent> t_act = new ArrayList<AnnounceMent>();
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < list.size() ; i++){
            if(list.get(i).getType().equals("活动")){
                t_act.add(list.get(i));
            }
        }
        t_act = tinyAncList(t_act);
        if ((t_act.size() < 3)){
            for(int k = 0 ; k < t_act.size() ; k++){
                indexAncList.add(t_act.get(k));
            }
        }else {
            indexAncList.add(t_act.get(0));
            indexAncList.add(t_act.get(1));
            indexAncList.add(t_act.get(2));
        }
        return indexAncList;
    }


    @RequestMapping("/indexShow3")
    @ResponseBody
    public List<AnnounceMent> indexShow3(HttpServletRequest request){
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();
        List<AnnounceMent> list = JDBCGetData();
        List<AnnounceMent> u_Info = new ArrayList<AnnounceMent>();
        List<AnnounceMent> temp = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < list.size() ; i++){
            if (list.get(i).getType().equals("通知")){
                if (list.get(i).getLevel().equals("紧急")){
                    u_Info.add(list.get(i));
                }else {
                    temp.add(list.get(i));
                }
            }
        }
        u_Info.addAll(temp);
        u_Info = tinyAncList(u_Info);
        if ((u_Info.size() < 3)){
            for(int i = 0 ; i < u_Info.size() ; i++){
                indexAncList.add(u_Info.get(i));
            }
        }else {
            indexAncList.add(u_Info.get(0));
            indexAncList.add(u_Info.get(1));
            indexAncList.add(u_Info.get(2));
        }
        return indexAncList;
    }


    @RequestMapping("/indexShow4")
    @ResponseBody
    public List<AnnounceMent> indexShow4(HttpServletRequest request){
        List<AnnounceMent> indexAncList = new ArrayList<AnnounceMent>();

        List<AnnounceMent> list = JDBCGetData();
        List<AnnounceMent> u_act = new ArrayList<AnnounceMent>();
        List<AnnounceMent> temp = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < list.size() ; i++){
            if (list.get(i).getType().equals("活动")){
                if (list.get(i).getLevel().equals("紧急")){
                    u_act.add(list.get(i));
                }else {
                    temp.add(list.get(i));
                }
            }
        }
        u_act.addAll(temp);
        u_act = tinyAncList(u_act);
        if ((u_act.size() < 3)){
            for(int i = 0 ; i < u_act.size() ; i++){
                indexAncList.add(u_act.get(i));
            }
        }else {
            indexAncList.add(u_act.get(0));
            indexAncList.add(u_act.get(1));
            indexAncList.add(u_act.get(2));
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


    public void initList(HttpServletRequest request){
        int group_id = gainGroupID(request);
        List<AnnounceMent> temp_info = new ArrayList<AnnounceMent>();
        List<AnnounceMent> temp_act = new ArrayList<AnnounceMent>();
        if(datalist_client == null){
            datalist_client = JDBCGetData();
            //datalist_client = tinyAncList(datalist_client);
        }
        c_t_info = new ArrayList<AnnounceMent>();
        c_t_act = new ArrayList<AnnounceMent>();
        c_u_info = new ArrayList<AnnounceMent>();
        c_u_act = new ArrayList<AnnounceMent>();
        for (int i = 0 ; i < datalist_client.size() ; i++){
            if(datalist_client.get(i).getGroupid() == group_id){
                if(datalist_client.get(i).getType().equals("通知")){
                    c_t_info.add(datalist_client.get(i));
                }else {
                    c_t_act.add(datalist_client.get(i));
                }
            }
        }
        for (int j = 0 ; j < datalist_client.size() ; j++){
            if(datalist_client.get(j).getGroupid() == group_id){
                if(datalist_client.get(j).getLevel().equals("紧急")){
                    if(datalist_client.get(j).getType().equals("通知")){
                        c_u_info.add(datalist_client.get(j));
                    }else {
                        c_u_act.add(datalist_client.get(j));
                    }
                }else {
                    if(datalist_client.get(j).getType().equals("通知")){
                        temp_info.add(datalist_client.get(j));
                    }else {
                        temp_act.add(datalist_client.get(j));
                    }
                }
            }
        }
        c_u_info.addAll(temp_info);
        c_u_act.addAll(temp_act);

        c_t_info = tinyAncList(c_t_info);
        c_t_act = tinyAncList(c_u_act);
        c_u_info = tinyAncList(c_u_info);
        c_u_act = tinyAncList(c_u_act);

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
                datalist.add(announceMents.get(i));
            }else {
                temp.add(announceMents.get(i));
            }
        }
        datalist.addAll(temp);
        return datalist;
    }

    //此函数用于获取用户分组
    public int gainGroupID(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        ApplicationContext context = new ClassPathXmlApplicationContext("anc-beans.xml");
        AncJDBCTemplate ancJDBCTemplate = (AncJDBCTemplate) context.getBean("ancJDBCTemplate");
        String usr = null;
        for (int i = 0;i < cookies.length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("login")){
                String jsonString = java.net.URLDecoder.decode(cookie.getValue());
                System.out.print("##############"+jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                usr = jsonObject.getString("name");
                break;
            }
        }

        int group_id = ancJDBCTemplate.groupAccess(usr);
        System.out.println("**********" + group_id);

        return group_id;
    }


}
