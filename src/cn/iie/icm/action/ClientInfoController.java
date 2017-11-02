package cn.iie.icm.action;

import cn.iie.icm.action.api.comm;
import cn.iie.icm.dao.PersonDao;
import cn.iie.icm.pojo.PersonPojo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;

@Controller
public class ClientInfoController {
    @RequestMapping("/clientInfo")
    private String show(Map<String, Object> map, HttpServletRequest request, ModelMap model) {

        JSONObject info = comm.Login.getLoginInfo(request);
        PersonPojo pp=new PersonPojo();
        PersonDao pd=new PersonDao();
        pp=pd.getPerson(info.get("account").toString());
        if(pp!=null)
        {
            map.put("stuName",pp.getName());
            map.put("stuNumber",pp.getNum());
            map.put("stuHead", pp.getHeadUrl());

        }

        return "clientInfo";
    }

    @RequestMapping("/HeadUpload")
    private String headUpload(Map<String, Object> map,HttpServletRequest request, @RequestParam("stuHead") MultipartFile img) {

//        JSONObject info = comm.Login.getLoginInfo(request);
//        PersonPojo pp=new PersonPojo();
//        PersonDao pd=new PersonDao();
//        pp=pd.getPerson(info.get("account").toString());



        String oldFileName=img.getOriginalFilename();
//        String savePath=getClass().getClassLoader().getResource("").getPath();//"\\headurl";
        String savePath=request.getSession().getServletContext().getRealPath("")+"graph\\headurl";//"\\headurl";
        File pathDir = new File(savePath);
        if(!pathDir.exists())
            pathDir.mkdirs();

        File newfile=null;
//        newfile.mkdir();
        String newFileName="";
        JSONObject json=new JSONObject();
        if(img!=null&&oldFileName!=null&&oldFileName.length()>0)
        {
            newFileName= UUID.randomUUID()+oldFileName.substring(oldFileName.lastIndexOf('.'));
            newfile=new File(savePath+"\\"+newFileName);


            try {
               // newfile.mkdir();
                img.transferTo(newfile);
                json.put("res","OK");
                json.put("file",newFileName);
            }
            catch(Exception err)
            {
                err.printStackTrace();
                json.put("res","Error");
            }

        }


        map.put("json",json.toString());
        return "api";
    }

}
