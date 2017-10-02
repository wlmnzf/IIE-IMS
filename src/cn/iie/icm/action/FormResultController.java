package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.dao.typeDao;
import cn.iie.icm.pojo.FormPojo;
import cn.iie.icm.pojo.TypePojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class FormResultController {

    @RequestMapping("/formResult/{formtoken}/")
    private String formResult(Map<String, Object> map, @PathVariable("formtoken") String formToken,HttpServletRequest request,RedirectAttributes attr)
    {
        map.put("curPage",1);

        int res= comm.Login.validCheck(request,2,attr,map);
        if(res==0) {
            String userid=request.getParameter("UserId");
            String usertoken=request.getParameter("UserToken");
            Result(map,formToken);
            return "formResult";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }

    }

    @RequestMapping("/formResult/{formtoken}/{page}/")
    private String formResultPage(Map<String, Object> map, @PathVariable("formtoken") String formToken, @PathVariable("page") String page,  HttpServletRequest request,RedirectAttributes attr)
    {
        map.put("curPage",page);

        int res=comm.Login.validCheck(request,2,attr,map);
        if(res==0) {
            String userid=request.getParameter("UserId");
            String usertoken=request.getParameter("UserToken");
            Result(map,formToken);
            return "formResult";
        }
        else
        {
            return comm.Login.errRedirect(attr,res);
        }

    }


    public void  Result(Map<String, Object> map,String formToken)
    {
        FormPojo fp=new FormPojo();
        TypePojo tp=new TypePojo();
        typeDao td=new typeDao();
        formDao fd=new formDao();

        fp = fd.getFormById(formToken);
        List<TypePojo> types=td.getAllType();

        map.put("title",fp.getTitle());
        map.put("formToken",formToken);
        map.put("json",fp.getJson());
        map.put("type",fp.getType());
        map.put("types",new JSONObject().put("types",types).toString());
    }

}
