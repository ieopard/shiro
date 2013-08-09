package com.java.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.util.WebUtils;

import com.java.framework.session.ThreadSessionManage;
import com.java.module.entity.User;
import com.java.module.user.security.AuthUtils;

public class ReaderSessionFilter implements Filter{
	
	
	

	public ReaderSessionFilter() {
		super();
		System.out.println("--------------filter------------------");
	}
	
	

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		User user = (User) ThreadSessionManage.get();
		
		if(user == null && AuthUtils.getUser() != null){
			//ThreadSessionManage.set(AuthUtils.getUser());
		}
		
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		System.out.println(req.getRequestURL());
		//matchUrl(req.getRequestURL().toString());
		arg2.doFilter(req, arg1);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		filterConfig.getFilterName();
//		filterConfig.getInitParameter(arg0)
//		filterConfig.getInitParameterNames();
//		System.out.println("------------------------ReaderSessionFilter-----------------------------");
//		System.out.println(filterConfig.getServletContext());
	}
	public void matchUrl(String url){
		System.out.println(url.substring(url.lastIndexOf("/")));
		if(url.substring(url.lastIndexOf("/")+1).equals("login")){
			logoutSubject();
		}
		
	}

	private void logoutSubject() {
		Subject s = (Subject) ThreadContext.getSubject();
		if(s != null){
			SecurityUtils.getSubject();
		}
	}
}
