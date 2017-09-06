package cn.iie.icm.action;

import org.springframework.stereotype.Controller;
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
public class CustomFormContorller {

//    @RequestMapping("/customForm")
//    private ModelAndView customForm(HttpServletRequest request, HttpServletResponse response,
//                                    String targetUrl) throws IOException {
//        return new ModelAndView("customForm");
//    }

    @RequestMapping("/customForm")
    private String customForm(Map<String, Object> map)
    {
        return "customForm";
     }

    @RequestMapping("/preview")
    private ModelAndView preview(HttpServletRequest request, HttpServletResponse response,
                                 String targetUrl) throws IOException {
        return new ModelAndView("preview");
    }

}
