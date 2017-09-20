package cn.iie.icm.action.api;

import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginRESTContorller {

    @RequestMapping(value="Login/check")
    public String form(Map<String, Object> map, HttpServletRequest request) {
        String userid = request.getParameter("UserId");
        String usertoken = request.getParameter("UserToken");

        JSONObject jsonObj = new JSONObject();
        JSONObject pagesObj = new JSONObject();

        formDao fd = new formDao();
        typeDao td = new typeDao();
        return "api";
    }

}
