
package com.java.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.java.framework.dao.Dao;


/**
 * @author pengluwei
 * @since 2013-6-18下午02:26:22
 * @version 1.0
 */
public abstract class ServiceBaseSupport<T extends Serializable,PK extends Serializable> implements Service<T, PK>{
	
	protected Dao<T,PK> daoBaseSupport;
	
	public abstract void setDaoBaseSupport(Dao<T,PK> daoBaseSupport);

	public void deleteEntity(T t)  {
		daoBaseSupport.deleteEntity(t);
	}
	
	public void delete(PK id)  {
		daoBaseSupport.delete(id);
	}

	public T get(PK id)  {
		return daoBaseSupport.get(id);
	}

	public List<T> getAll() {
		return daoBaseSupport.getAll();
	}
	public <B> B getAll(Class<? extends Serializable> c){
		return daoBaseSupport.getAll(c);
	}

	public List<T> getAll(int start, int end) {
		return daoBaseSupport.getAll(start, end);
	}
	public Integer getAllCount() {
		return daoBaseSupport.getAllCount();
	}

	public T load(PK id)  {
		return daoBaseSupport.load(id);
	}

	public void save(T t){
		daoBaseSupport.save(t);
	}

	public void saveOrUpdate(T t)  {
		daoBaseSupport.saveOrUpdate(t);
	}

	public void update(T t)  {
		daoBaseSupport.update(t);
	}
}
