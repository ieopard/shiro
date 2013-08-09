package com.java.module.user.security;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.java.module.entity.User;




public class AuthUtils {
	
	
	private final static String GUEST = "guest";
	
	//获得当前subject
	public static Subject getCurrentSubject(){
		
		return SecurityUtils.getSubject();
		
	}
	
	//登陆认证
	public static void login(String username,String password){
		
		
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		
		getCurrentSubject().login(token);
	}
	
	//登出
	public static void logout(){
		
		getCurrentSubject().logout();
	}
	
	//获取当前user对象
	public static User getUser(){
		
		Subject subject = getCurrentSubject();
		
		if(subject.isAuthenticated()){
			
			return (User) subject.getPrincipal();
		}
		
		return null;
	}
	
	//创建session，不依赖于web容器
	public static Session getSession(){
		
		return getCurrentSubject().getSession();
	}
	
	//验证角色
	public static boolean hasRole(String role){
		
		return getCurrentSubject().hasRole(role);
		
	}
	//验证所有角色
	public static boolean hasRoleAll(Collection<String> roles){
		
		return getCurrentSubject().hasAllRoles(roles);
	}
	
	//认证subject
	public static boolean isAuthenticated(){
		
		return getCurrentSubject().isAuthenticated();
	}
	
	//是否允许
	/**
	 * shiro的wildcard格式的检查权限
	 */
	public static boolean isPermitted(String permitted){
		
		return getCurrentSubject().isPermitted(permitted);
	}
	
	public static boolean[] isPermitted(String... permitteds){
		
		return getCurrentSubject().isPermitted(permitteds);
	}
	
	public static boolean isPermittedAll(String... permitteds){
		
		return getCurrentSubject().isPermittedAll(permitteds);
	}
	
	public static boolean isPermittedAll(Collection<String> c){
		
		return getCurrentSubject().isPermittedAll(c.toArray(new String[]{}));
	}
	
	//获得用户名
	public static String getUsername(){
		
		return getUser().getName() != null ? getUser().getName() : GUEST;  
	}
	
}










