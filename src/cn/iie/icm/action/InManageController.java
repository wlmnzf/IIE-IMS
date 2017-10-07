package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.TypePojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class InManageController {
    @RequestMapping("/inManage")
    private String inManage(Map<String, Object> map, HttpServletRequest request, RedirectAttributes attr)
    {
        map.put("curPage",1);

        int res= comm.Login.validCheck(request,1,map);
        if(res==0) {
            typeDao td=new typeDao();
            List<TypePojo> types=td.getAllType();

            map.put("types",new JSONObject().put("types",types).toString());


            return "inManage";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }
    }

}
