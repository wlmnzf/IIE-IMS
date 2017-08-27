package cn.iie.icm.server;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * 加载spring后的事件对象
 * @author wang
 *
 */
@Component
@Lazy(false)
public class PlatformContextAware extends WebApplicationObjectSupport {
	
	private static final Logger log = Logger.getLogger(PlatformContextAware.class);
	private static ApplicationContext webAppContext;
	private static ServletContext webServletContext;
	
	public PlatformContextAware(){
		super();
	}
	
	public void init() {
		log.info("init spring ApplicationContextAware: {}");
	}
	
	public void destroy() {
		webAppContext = null;
		webServletContext = null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.support.WebApplicationObjectSupport#isContextRequired()
	 */
	protected boolean isContextRequired() {
		return super.isContextRequired();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.support.WebApplicationObjectSupport#initApplicationContext(org.springframework.context.ApplicationContext)
	 */
	protected void initApplicationContext(ApplicationContext context) {
		super.initApplicationContext(context);
		webAppContext = context;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.support.WebApplicationObjectSupport#initServletContext(javax.servlet.ServletContext)
	 */
	@Override
	protected void initServletContext(ServletContext servletContext) {
		super.initServletContext(servletContext);
		webServletContext = servletContext;
	}
	
	public static ApplicationContext getWebAppContext() {
		return webAppContext;
	}
	
	
	public static ServletContext getWebServletContext() {
		return webServletContext;
	}


}
