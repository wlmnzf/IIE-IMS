package cn.iie.icm.action;

import cn.iie.icm.dao.ClientFormDao;
import cn.iie.icm.dao.formDao;
import cn.iie.icm.pojo.ClientFormPojo;
import cn.iie.icm.pojo.FormPojo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class ClientVoteRes {
    @RequestMapping("/clientVoteRes/{formToken}/")
    private String show(Map<String, Object> map,HttpServletRequest request, ModelMap model, @PathVariable("formToken") String formToken) {

        formDao fd=new formDao();
        FormPojo fp=fd.getFormById(formToken);

        if(fp!=null)
        {
             String Title=fp.getJson();
             JSONArray jb=new JSONArray(Title);
             Map<Integer,String> mapNum=new HashMap<Integer, String>();
             for(int i=0;i<jb.length();i++)
             {
                 JSONObject item=jb.getJSONObject(i);
                 String type=item.get("type").toString();
                 if(type.equals("t3")||type.equals("t4"))
                 {
                    int opLen= type.equals("t3")?item.getJSONObject("data").getJSONObject("1").length():item.getJSONObject("data").length();
//                     int opLen=item.getJSONObject("data").getJSONObject("1").length()  ;
                     boolean flag=false;
                     String opText="";
                     for(int j=0;j<opLen;j++)
                     {
                         if(flag)
                         {
                             opText+="|";
                         }
                         else
                         {
                             flag=true;
                         }
                         opText+="0";
                     }
                     mapNum.put(i,opText);
                 }
//                 else if(type.equals("t4"))
//                 {
//                     mapNum.put(i,"0");
//                 }
             }

             ClientFormDao cfd=new ClientFormDao();
             List<ClientFormPojo> cfp=cfd.getFormResByFormToken(formToken);
//             int t3[]=new int[15];
//             int t4[]=new int[15];
//             Arrays.fill(t3,0);
//             Arrays.fill(t4,0);
             for(int i=0;i<cfp.size();i++)
             {
                 ClientFormPojo c=cfp.get(i);
                 String json=c.getJson();
                 JSONObject res=new JSONObject(json);

                 Iterator iter=mapNum.entrySet().iterator();
//                 while(iter.hasNext())
                 for(int key: mapNum.keySet())
                 {
//                     Object tmp= ;)

                    // Map.Entry entry=(Map.Entry)iter.next();
                  //   String key=(entry.getKey().toString());
                     JSONObject option=res.getJSONObject(String.valueOf(key));
                     if(option.get("type").equals("t3")||option.get("type").equals("t4"))
                     {
                          String resIndex=option.getJSONObject("data").get("index").toString();
                          String []resI=resIndex.split("\\|");

                          String totalCnt=mapNum.get(key);//entry.getValue().toString();
                          String []totalC=totalCnt.split("\\|");

                          for(int k=0;k<totalC.length;k++)
                          {
//                              boolean flag=false;
                              for(int l=0;l<resI.length;l++)
                              {
                                  if(k==Integer.parseInt(resI[l]))
                                  {
                                      totalC[k]=String.valueOf(Integer.parseInt(totalC[k])+1);
                                  }
                              }

                          }
                         boolean flag=false;
                         String opText="";
                         for(int j=0;j<totalC.length;j++)
                         {
                             if(flag)
                             {
                                 opText+="|";
                             }
                             else
                             {
                                 flag=true;
                             }
                             opText+=totalC[j];
                         }
                         mapNum.replace(key,opText);
                     }
//                     else if(option.get("type").equals("t4"))
//                     {
//                         String resIndex=option.getJSONObject("data").get("index").toString();
//
//                     }
                 }
//                 mapNum.replace();
             }


             JSONObject jsonText=new JSONObject();
             jsonText.put("form",fp.getJson());
             jsonText.put("res",mapNum);
             jsonText.put("title",fp.getTitle());
             jsonText.put("total",cfp.size());
             map.put("data",jsonText.toString());

        }

        return "clientVoteRes";
    }
}
