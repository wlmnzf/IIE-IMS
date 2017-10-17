package cn.iie.icm.action.api;

import cn.iie.icm.dao.PersonDao;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.PersonPojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginRESTController {

    @RequestMapping(value="Login")
    public String Login(Map<String, Object> map, HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr) {
        String userid = request.getParameter("login_name");
        String password = request.getParameter("password");

        PersonPojo pp=new PersonPojo();
        PersonDao pd=new PersonDao();

        JSONObject jsonObj = new JSONObject();

        try {
            pp = pd.getPerson(userid);
            password=comm.MD5_32(password).toLowerCase();
            if(pp==null)
            {
                request.setAttribute("info","账号不存在");
                request.getRequestDispatcher("/index").forward(request, response);
            }

            if (password.equals(pp.getPassword())) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String token = comm.MD5_32(userid + password + df.format(new Date()).toString());
                pd.updateToken(userid, token);
                jsonObj.put("account",userid);
                jsonObj.put("res", "OK");
                jsonObj.put("type", pp.getAuth());
                jsonObj.put("token", token);
                jsonObj.put("name",pp.getName());



                Cookie   cookie = new Cookie("login",java.net.URLEncoder.encode(jsonObj.toString(),"utf-8"));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(604800);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            else
            {
//                jsonObj.put("res", "Password error");
//                attr.addFlashAttribute("info", "账号密码错误！");
//                return "redirect:/index";
//               return comm.Login.setErrInfo(attr,"账号密码错误！");
                request.setAttribute("info","密码认证错误");
                request.getRequestDispatcher("/index").forward(request, response);
            }
        }
        catch(Exception e){
            jsonObj.put("res", "SQL error"+e.getMessage());
        }

//        map.put("json",jsonObj.toString());

        if(pp.getAuth()==1)
        {
            return "redirect:/announceShow";
        }
        else if(pp.getAuth()==2)
        {
            return "redirect:/announceManagement";
        }
        else
        {
            request.setAttribute("info","未知错误");
            try {
                request.getRequestDispatcher("/index").forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return "api";
    }

    @RequestMapping(value="Login/check")
    public String checkLogin(Map<String, Object> map, HttpServletRequest request) {
        String userid = request.getParameter("UserId");
        String usertoken = request.getParameter("UserToken");

        JSONObject jsonObj = new JSONObject();
        JSONObject pagesObj = new JSONObject();

        formDao fd = new formDao();
        typeDao td = new typeDao();
        return "api";
    }





}
