package cn.iie.icm.action.api;
import cn.iie.icm.dao.PersonDao;
import cn.iie.icm.pojo.PersonPojo;
import org.json.JSONObject;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public  class comm {
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String MD5_32(String SourceString) throws Exception {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(SourceString.getBytes());
        byte messageDigest[] = digest.digest();
        return toHexString(messageDigest);
    }

    public static String MD5_16(String SourceString) throws Exception {
        return MD5_32(SourceString).substring(8, 24);
    }

    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    /*
    0:OK
    1:Login failed
    2:Auth failed
     */
    public static class Login
    {
        public  static String errRedirect(RedirectAttributes attr,int res)
        {
            switch(res)
            {
                case 0:
                case 1:return setErrInfo(attr,"登录已失效，请重新登陆！");
                case 2:return  setErrInfo(attr,"对不起，您没有权限访问此页面");
                case 3:return  setErrInfo(attr,"退出成功!");
            }
            return "redirect:/index";
        }
//        public  static String Check(HttpServletRequest request,int authType,RedirectAttributes attr,Map<String)
//        {
//            int res=validCheck(request,authType,attr,null);
//            switch(res)
//            {
//                case 0:
//                case 1:return setErrInfo(attr,"登录已失效，请重新登陆！");
//                case 2:return  setErrInfo(attr,"对不起，您没有权限访问此页面");
//            }
//            return ;
//        }

        public  static int validCheck(HttpServletRequest request,int authType)
        {
            return  validCheck( request,authType,null);
        }

        public  static int validCheck(HttpServletRequest request,int authType,Map<String, Object>  map)
        {
            Cookie login= comm.getCookieByName(request,"login");

            if(login!=null)
            {
                try {
                    PersonDao pd = new PersonDao();
                    PersonPojo pp = new PersonPojo();

                    String jsonText = login.getValue();
                    jsonText = java.net.URLDecoder.decode(jsonText,"utf-8");
                    JSONObject jsonObj = new JSONObject(jsonText);
                    String userName = (String) jsonObj.get("account");
                    String userToken = (String) jsonObj.get("token");

                    pp = pd.getPerson(userName);

                    if(!authCheck(authType,pp))
                    {
                            return 2;
//                        setErrInfo(attr,"对不起，您没有权限访问此页面");
                    }

                    if (pp.getToken().equals(userToken)) {
                        if(map!=null)
                            setLoginInfo(map,pp);
                        return 0;
                    }
                }
                catch(Exception e)
                {
                    return 1;
                }
            }
            return 1;
        }

        public  static boolean authCheck(int authType,PersonPojo pp)
        {
            if(authType<=pp.getAuth())
            {
                return true;
            }
            return false;
        }

        public static void  setLoginInfo(Map<String, Object>  map,PersonPojo pp)
        {
            map.put("_USER_NAME",pp.getNum());
            map.put("_TOKEN",pp.getToken());
            map.put("_TYPE",pp.getAuth());
            map.put("_TYPE_TEXT",pp.getAuth()==1?"用户":"管理员");
            map.put("_HEAD_URL",pp.getHeadUrl());
        }

        public static  String setErrInfo(RedirectAttributes attr,String info)
        {
            attr.addFlashAttribute("info", info);
            return "redirect:/index";
        }
    }




}
