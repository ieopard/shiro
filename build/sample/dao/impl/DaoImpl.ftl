/**
 * 
 */
package com.java.module.${packageName}.dao.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.java.framework.dao.DaoBaseSupport;
import com.java.module.entity.${name};
import com.java.module.${packageName}.dao.${name}Dao;



@Repository
public class ${name}DaoImpl extends DaoBaseSupport<${name},Long> implements ${name}Dao{

	
	private static final Logger log = LoggerFactory.getLogger(${name}DaoImpl.class);
	
}
