package cn.iie.icm.server;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.iie.icm.action.api.comm;
import com.esen.util.StrFunc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class FilterLoginCheck implements Filter{
	/**
	 * 可以不需要登录就访问的action	
	 */
	public String[] freeAccessList;
	public String[] manageList={"announceEditor","announceManagement","ClientIn","clientVote","clientVoteRes","customForm","formManage","formResult","group","personal"};
	public String[] clientList={"announceShow","inManage","showAnc"};
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest reqT, ServletResponse resT,
						 FilterChain chain) throws IOException, ServletException {
		// 获得用户请求的URI
		HttpServletRequest req = (HttpServletRequest)reqT;
		HttpServletResponse res =(HttpServletResponse) resT;
		String path = req.getRequestURI();
		HttpSession session = req.getSession();
//		String password = (String) session.getAttribute("password");

		if(path.indexOf("/index") > -1||path.indexOf(".js") > -1||path.indexOf(".css") > -1||path.indexOf(".png") > -1||path.indexOf("/jpg") > -1||path.indexOf("/Login") > -1) {
			chain.doFilter(req, res);
			return;
		}
		else if(path.indexOf("/exit") > -1)
		{
			Cookie exit= new Cookie("login",null);
			exit.setMaxAge(0);
			exit.setHttpOnly(true);
			exit.setPath("/");
			res.addCookie(exit);
			req.setAttribute("info","退出成功！");
			req.getRequestDispatcher("/index").forward(req, res);
			chain.doFilter(req, res);
			return;
		}
		else {
			boolean flag=false;
			int result=-1;
//			for(int i = 0; i < clientList.length; i++){
//				System.out.print(clientList[i]);
//				if(path.indexOf(clientList[i]) > -1)
//				{
//					result= comm.Login.validCheck(req,1,session);
//					flag=true;
//				}
//			}

//			if(!flag)
//			{
				for(int i = 0; i < manageList.length; i++){
					System.out.print(manageList[i]);
					if(path.indexOf(manageList[i]) > -1)
					{
						result= comm.Login.validCheck(req,2,session);
						flag=true;
					}
				}
//			}

			String info="";
			if(!flag)
			{
				result= comm.Login.validCheck(req,1,session);
			}

			if(result==0) {
//				return "formManage";

//				session.setAttribute("basePath", ); // 每个页面，都设置一下基础路径
				chain.doFilter(req, res);
			}
     		else {
				switch(result)
				{
					case 0:
					case 1:info="登录已失效，请重新登陆！";break;
					case 2:info="对不起，您没有权限访问此页面";break;
					case 3:info="退出成功!";
				}
				req.setAttribute("info",info);
				req.getRequestDispatcher("/index").forward(req, res);
			}
		}

			chain.doFilter(req, res);
			return;
	}

	public void init(FilterConfig fc) throws ServletException {
//		HashSet<String> accesses = new HashSet<String>();
//		@SuppressWarnings("unchecked")
//		Enumeration<String> names = fc.getInitParameterNames();
//		while (names.hasMoreElements()) {
//			String name = names.nextElement();
//			if (name.endsWith("FreeAccess")) {
//				String initParameter = fc.getInitParameter(name);
//				if (!StrFunc.isNull(initParameter)) {
//					String[] list = initParameter.split(",");
//					for (String uri : list) {
//						if (!StrFunc.isNull(uri)) {
//							accesses.add(uri);
//						}
//					}
//				}
//			}
//		}
//		this.freeAccessList = new String[accesses.size()];
//		accesses.toArray(this.freeAccessList);
	}

}
