package iss;

import iss.MultipleSecurityConfiguration.ApiWebSecurityConfiguration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SessionInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{MultipleSecurityConfiguration.ApiWebSecurityConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 @Override
	    protected Filter[] getServletFilters() {
		return new Filter[] { new HiddenHttpMethodFilter() };
	    }
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}
	
	public class SessionListener implements HttpSessionListener {
		 
	    @Override
	    public void sessionCreated(HttpSessionEvent event) {
	        System.out.println("==== Session is created ====");
	        event.getSession().setMaxInactiveInterval(15);
	    }
	 
	    @Override
	    public void sessionDestroyed(HttpSessionEvent event) {
	        System.out.println("==== Session is destroyed ====");
	    }
	}

}
