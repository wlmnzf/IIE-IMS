package cn.iie.icm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import cn.iie.icm.action.api.comm;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormManageController {

    @RequestMapping("/formManage")
    private String customForm(Map<String, Object> map,HttpServletRequest request,RedirectAttributes attr)
    {
        map.put("curPage",1);

//        int res=comm.Login.validCheck(request,2,map);
//        if(res==0) {
            return "formManage";
//        }
//        else
//        {
//          return comm.Login.errRedirect(attr,res);
//        }
    }
    @RequestMapping("/formManage/{page}/")
    private String customFormWithPage(Map<String, Object> map, @PathVariable("page")String page,HttpServletRequest request,RedirectAttributes attr ){
        map.put("curPage",page);
//        return "formManage";
//        int res=comm.Login.validCheck(request,2,map);
//        if(res==0) {
            return "formManage";
//        }
//        else
//        {
//            return comm.Login.errRedirect(attr,res);
//        }
    }



}
