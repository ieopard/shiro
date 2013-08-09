package com.java.module.user.security;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.java.module.entity.Privilege;
import com.java.module.entity.Role;
import com.java.module.entity.User;
import com.java.module.user.service.UserService;


@Component("securityRealm")
public class SecurityRealm extends AuthorizingRealm{

	
	@Resource
	private UserService userService;
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		User user = userService.userAuthentication(token.getUsername());
		
		if(user == null){
			return null;
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.newUser(),user.getPassword(),getName());
		
		return info;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		User user = (User) principalCollection.getPrimaryPrincipal();
		
		
		//要将role也加入到info中
		Set<Privilege> sets = userService.getAuthority(user.getId());
		
		List<Role> roles = userService.getUserRole(user.getName());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		for(Privilege p : sets){
			info.addStringPermission(p.privilegeToString());
		}
		for(Role role : roles){
			info.addRole(role.getName());
		}
		return info;
	}
	
	public List<String> conver(List<Role> roles){
		
		for(Role role : roles){
			
		}
		return null;
	}
	
}
