
package com.java.module.user.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.framework.annotation.Genre;
import com.java.framework.dao.Dao;
import com.java.framework.service.ServiceBaseSupport;
import com.java.module.entity.Privilege;
import com.java.module.entity.PrivilegeRole;
import com.java.module.entity.Role;
import com.java.module.entity.User;
import com.java.module.entity.UserRole;
import com.java.module.user.dao.UserDao;
import com.java.module.user.service.UserService;


@Service
public class UserServiceImpl extends ServiceBaseSupport<User,Long> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Resource
    private UserDao userDao;
    
    
	
	@Autowired
    @Genre("userDaoImpl")
	@Override 
	public void setDaoBaseSupport(Dao<User, Long> daoBaseSupport) {
		this.daoBaseSupport = daoBaseSupport;
    }

	@Override
	//创建用户权限
	public void createUserPrivilege(User user, Role role) {
		
		//userDao.save(user);
		//roleDao.save(role);
		
		userDao.save(new UserRole(user, role));
		
	}
	
	@Override
	//创建角色
	public void createRole(List<Privilege> privileges, Role role) {
		
		int count = 0;
		userDao.save(role);
		for(Privilege privilege : privileges){
			
			if(count++ == 50) userDao.flush();
			
			userDao.save(new PrivilegeRole(privilege, role));
		}
	}

	@Override
	//获得用户角色
	public List<UserRole> getList(long id) {
		
		final String hql = "select o from UserRole o where o.user.id = :userid";
		
		Map<String,Long> paramMap = new HashMap<String,Long>();
		
		paramMap.put("userid", id);
		
		return userDao.getByConditionObject(hql,null,paramMap);
	}

	@Override
	//获得权限
	public <C> C getAuthority(long id) {
		
		final String hql = "select p from UserRole o,PrivilegeRole pr,Privilege p where o.user.id = :userid and o.role.id = pr.role.id and pr.privilege.id = p.id";
		
		Map<String,Long> paramMap = new HashMap<String,Long>();
		
		paramMap.put("userid", id);
		
		List<Privilege> list = userDao.getByConditionObject(hql,null,paramMap);
		
		Set<Privilege> set = new HashSet<Privilege>();
		
		for(Privilege privilege : list){
			
			set.add(privilege);
			
		}
		return (C) set;
	}

	@Override
	//删除角色
	public void deletes(long id) {
		
		final String hql = "select o from PrivilegeRole o where o.role.id = ?";
		
		final String userHql = "select o from UserRole o where o.role.id = ?";
		
		List<PrivilegeRole> privilegeRoles = userDao.getByConditionObject(hql,null,new Object[]{id});
		
		List<UserRole> userRoles = userDao.getByConditionObject(userHql,null,new Object[]{id});
		
		userDao.deleteList(PrivilegeRole.class,privilegeRoles);
		
		userDao.deleteList(UserRole.class,userRoles);
		//
		userDao.deleteEntity(userDao.get(Role.class,id));
	}

	@Override
	//用户认证
	public User userAuthentication(String username) {
		
		final String hql = "select o from User o where o.name = ?";  
		
		List<User> list = userDao.getByCondition(hql, new Object[]{username});
		
		return list != null ? list.get(0) : null;
	}

	@Override
	//获得当前用户的角色
	public List<Role> getUserRole(String username) {
		
		final String hql = "select r from User u,Role r,UserRole ur where ur.user.id = u.id and r.id = ur.role.id and u.name = ?";
		
		return userDao.getByConditionObject(hql, null, new Object[]{username});
	}

	@Override
	public void createManyRole() {
		
//		Role role = new Role("rs");
//		User user = new User();
//		UserRole ur = new UserRole(user,role);
		
		
		
	}

	@Override
	public <B> B getManyRole(Long id) {
		
		
		final String hql = "select o from User o where o.id = ?";
		return userDao.getByConditionObject(hql, null, new Object[]{id});
	}
	
}
