
package com.java.module.user.service;

import java.util.*;
import com.java.framework.service.Service;
import com.java.module.entity.Privilege;
import com.java.module.entity.Role;
import com.java.module.entity.User;
import com.java.module.entity.UserRole;

public interface UserService extends Service<User,Long>{
	
	
	public void createRole(List<Privilege> privileges,Role role);
	
	public void createUserPrivilege(User user,Role role);
	
	public List<UserRole> getList(long id);
	
	
	public <C> C getAuthority(long id);
	
	void deletes(long id);
	
	User userAuthentication(String username);
	
	List<Role> getUserRole(String username);
	
	
	public void createManyRole();
	public <B> B getManyRole(Long id);
	
}
