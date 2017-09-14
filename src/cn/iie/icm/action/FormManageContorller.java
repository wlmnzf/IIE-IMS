package cn.iie.icm.action;

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
import java.util.Map;

@Controller
public class FormManageContorller {

    @RequestMapping("/formManage")
    private String customForm(Map<String, Object> map)
    {
        map.put("curPage",1);
        return "formManage";
    }
    @RequestMapping("/formManage/{page}/")
    private String customFormWithPage(Map<String, Object> map, @PathVariable("page")String page ){
        map.put("curPage",page);
        return "formManage";
    }


}
