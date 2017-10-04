package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AnnounceShowController {
    @RequestMapping("/announceShow")
    private String show(HttpServletRequest request, Map<String, Object> map, RedirectAttributes attr) {

        int res= comm.Login.validCheck(request,1,map);
        if(res==0) {
            return "announceShow";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }

    }

}
