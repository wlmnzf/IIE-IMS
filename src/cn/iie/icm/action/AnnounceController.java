package cn.iie.icm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnnounceController {

    @RequestMapping("/announceMagement")
    private String toAnnounceMag(){
        return "announceMagement";
    }

    @RequestMapping("/announceEditor")
    private String toAnnounceEdit(){
        return "announceEditor" ;
    }
}
