
package com.java.module.${packageName}.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.java.framework.dao.Dao;
import com.java.framework.service.ServiceBaseSupport;
import com.java.module.entity.${name};
import com.java.module.${packageName}.dao.${name}Dao;
import com.java.module.${packageName}.service.${name}Service;


@Service
public class ${name}ServiceImpl extends ServiceBaseSupport<${name},Long> implements ${name}Service {

    private static final Logger log = LoggerFactory.getLogger(${name}ServiceImpl.class);
    
    @Resource
    private ${name}Dao ${packageName}Dao;
	
    @Autowired
    @Qualifier("${packageName}DaoImpl")
	@Override 
	public void setDaoBaseSupport(Dao<${name}, Long> daoBaseSupport) {
		this.daoBaseSupport = daoBaseSupport;
    }
    
    
    
}
