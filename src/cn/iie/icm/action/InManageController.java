package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class InManageController {
    @RequestMapping("/inManage")
    private String inManage(Map<String, Object> map, HttpServletRequest request, RedirectAttributes attr)
    {
        map.put("curPage",1);

        int res= comm.Login.validCheck(request,1,attr,map);
        if(res==0) {
            return "inManage";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }
    }

}
