package cn.iie.icm.server;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.esen.util.StrFunc;


public class FilterLoginCheck implements Filter{
	/**
	 * 可以不需要登录就访问的action	
	 */
	public String[] freeAccessList;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
			chain.doFilter(req, res);
			return;
	}

	public void init(FilterConfig fc) throws ServletException {
		HashSet<String> accesses = new HashSet<String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> names = fc.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if (name.endsWith("FreeAccess")) {
				String initParameter = fc.getInitParameter(name);
				if (!StrFunc.isNull(initParameter)) {
					String[] list = initParameter.split(",");
					for (String uri : list) {
						if (!StrFunc.isNull(uri)) {
							accesses.add(uri);
						}
					}
				}
			}
		}
		this.freeAccessList = new String[accesses.size()];
		accesses.toArray(this.freeAccessList);
	}

}
