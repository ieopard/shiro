package com.java.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @author pengluwei
 * @since 2013-6-18下午02:26:42
 * @version 1.0
 */
public interface Service<T,PK> {

	void save(T t);
	void update(T t)  ;
	void deleteEntity(T t)  ;
	void delete(PK id) ;
	void saveOrUpdate(T t) ;
	
	T get(PK id)  ;
	T load(PK id)  ;
	
	<B> B getAll(Class<? extends Serializable> c);
	/**
	 * 查询全部记录
	 * @return
	 */
	List<T> getAll();
	/**
	 * 返回全部记录数量
	 * @return
	 */
	Integer getAllCount();
	/**
	 * 查询全部并分页
	 * @param start
	 * @param end
	 * @return
	 */
	List<T> getAll(int start,int end);
	
}
