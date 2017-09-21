package cn.iie.icm.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientVote {
    @RequestMapping("/clientVote")
    private String show(HttpServletRequest request, ModelMap model) {

        return "clientVote";
    }
}
