package cn.iie.icm.action;

import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.TypePojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;


@Controller
public class FormResult {

    @RequestMapping("/formResult/{formtoken}/{page}/")
    private String formResult(Map<String, Object> map, @PathVariable("formtoken") String formToken, @PathVariable("page") String page,  HttpServletRequest request)
    {
        map.put("curPage",1);

        String userid=request.getParameter("UserId");
        String usertoken=request.getParameter("UserToken");

        FormPojo fp=new FormPojo();
        TypePojo tp=new TypePojo();
        typeDao td=new typeDao();
        formDao fd=new formDao();

        fp = fd.getFormById(formToken);
        List<TypePojo> types=td.getAllType();

        map.put("title",fp.getTitle());
        map.put("json",fp.getJson());
        map.put("type",fp.getType());
        map.put("types",new JSONObject().put("types",types).toString());





        return "formResult";
    }
}