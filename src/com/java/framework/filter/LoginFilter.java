package com.java.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.java.framework.session.ThreadSessionManage;
import com.java.module.user.security.AuthUtils;

public class LoginFilter implements Filter{
	
	private static final String LOGIN = "login";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest  request, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String url  = req.getRequestURI();
		
		
		System.out.println( ThreadSessionManage.get()+"-==================url=========="+url);
		if(LOGIN.equals(acquireUrl(url)) && ThreadSessionManage.get() != null){
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			AuthUtils.logout();
		}
		
		arg2.doFilter(req, arg1);
	}
	
	public String acquireUrl(String url){
		
		int index = url.lastIndexOf("/");
		
		return url.substring(index+1);
		
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

	
}
