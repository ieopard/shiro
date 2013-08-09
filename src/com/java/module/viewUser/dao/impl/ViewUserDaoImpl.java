/**
 * 
 */
package com.java.module.viewUser.dao.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.java.framework.dao.DaoBaseSupport;
import com.java.module.entity.ViewUser;
import com.java.module.viewUser.dao.ViewUserDao;



@Repository
public class ViewUserDaoImpl extends DaoBaseSupport<ViewUser,Long> implements ViewUserDao{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewUserDaoImpl.class);
	
}
