
package com.java.module.viewUser.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.java.framework.dao.Dao;
import com.java.framework.service.ServiceBaseSupport;
import com.java.module.entity.ViewUser;
import com.java.module.viewUser.dao.ViewUserDao;
import com.java.module.viewUser.service.ViewUserService;


@Service
public class ViewUserServiceImpl extends ServiceBaseSupport<ViewUser,Long> implements ViewUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewUserServiceImpl.class);
    
    @Autowired
    private ViewUserDao viewUserDao;
	
    @Autowired
    @Qualifier("viewUserDaoImpl")
	@Override 
	public void setDaoBaseSupport(Dao<ViewUser, Long> daoBaseSupport) {
		this.daoBaseSupport = daoBaseSupport;
    }
    
}
