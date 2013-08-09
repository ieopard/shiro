package com.java.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SearchApplicationContentFilter implements Filter{
	
	
	private final Object object = new Object(); 
	
	private WebApplicationContext webApplicationContext;
	
	private String contextAttribute;

	private FilterConfig filterConfig;
	
	private ServletContext servletContext;
	
	public void setContextAttribute(String contextAttribute) {
		this.contextAttribute = contextAttribute;
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("-----------------wac=====================");
		
		//System.out.println(webApplicationContext.getBean("shiroFilter"));
		
//		AbstractShiroFilter ssf = (AbstractShiroFilter) webApplicationContext.getBean("shiroFilter");
		
//		System.out.println(ssf.getFilterChainResolver());
		
		System.out.println(req.getRequestURI());
		
		arg2.doFilter(request, arg1);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
		
		synchronized (object) {
		
			WebApplicationContext wac = findWebApplicationContext();
			

			System.out.println("-----------------wac~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.out.println(wac.getBean("shiroFilter"));
			this.webApplicationContext = wac;
		}
		
	}

	private WebApplicationContext findWebApplicationContext() {
		
		if(webApplicationContext != null)
			return webApplicationContext;
		
		String attrName = getContextAttribute();
		
		if(attrName != null)
			return WebApplicationContextUtils.getWebApplicationContext(getServletContext(), attrName);
		else
			return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
	}

	private ServletContext getServletContext() {
		return (this.filterConfig != null ? this.filterConfig.getServletContext() : this.servletContext);
	}

	private String getContextAttribute() {
		return contextAttribute;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
