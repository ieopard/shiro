/**
 * 
 */
package com.java.module.user.dao.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.java.framework.dao.DaoBaseSupport;
import com.java.module.entity.User;
import com.java.module.user.dao.UserDao;



@Repository
public class UserDaoImpl extends DaoBaseSupport<User,Long> implements UserDao{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
}
