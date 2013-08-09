/**
 * 
 */
package com.java.framework.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.java.framework.page.Page;
import com.java.module.entity.User;


public interface Dao<T,PK> {
	
	
	void save(Object obj);
	void update(Object obj)  ;
	void deleteEntity(Object obj)  ;
	
	void saveOrUpdate(Object obj) ;
	void deleteList(Class<?> c,Collection<? extends Serializable> objs);
	public <B> B get(Class<?> c,Serializable id);
	void delete(PK id) ;
	
	public void flush();
	
	public void clear();
	
	T get(PK id)  ;
	T load(PK id)  ;
	
	/**
	 * 查询全部记录
	 * @return
	 */
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
	/**
	 * 查询全部并排序
	 * @param orderBy
	 * @param start
	 * @param end
	 * @return
	 */
	List<T> getAll(String orderBy,int start,int end);	
	/**
	 * 查询全部 分页 并排序  分组
	 * @param orderBy
	 * @param groupBy
	 * @param start
	 * @param end
	 * @return
	 */
	List<T> getAll(String orderBy,String groupBy,int start,int end);	
	
	
	/**
	 * 条件查询
	 * @param hql
	 * @param paramMap key : value
	 * @return
	 */
	public List<T> getByCondition(String hql,Map<String,Object> paramMap);
	
	/**
	 * 条件查询数量
	 * @param hql
	 * @param paramMap key : value
	 * @return
	 */
	Integer getByConditionCount(String hql,Map<String,Object> paramMap);
	
	/**
	 * 条件查询并分页
	 * @param hql
	 * @param start
	 * @param end
	 * @param paramMap
	 * @return
	 */
	public List<T> getByCondition(String hql,int start,int end,Map<String,Object> paramMap);
	
	/**
	 * 条件查询
	 * @param hql 
	 * @param parameters 参数可以为空
	 * @return
	 */
	List<T> getByCondition(String hql,Object... parameters);
	
	/**
	 * 多表条件查询
	 * @param hql 
	 * @param parameters 参数可以为空
	 * @return List<Object[]>
	 * 
	 */
	<B> B getByConditionResultObject(String hql,int start,int end,Object... parameters);

	/**
	 * 条件查询数量
	 * @param hql
	 * @param parameters
	 * @return
	 */
	
	Integer getByConditionCount(String hql,Object... parameters);
	/**
	 * 条件查询并分页
	 * @param hql
	 * @param start
	 * @param end
	 * @param paramenters
	 * @return
	 */
	List<T> getByCondition(String hql,int start,int end,Object... parameters);
	
	/**
	 * 条件查询并分页
	 * @param hql
	 * @param page
	 * @param parameters
	 * @return
	 */
	<B> B getByConditionObject(String hql,Page page,Object... parameters);
	
	/**
	 * 条件查询并分页
	 * @param hql
	 * @param page
	 * @param paramMap
	 * @return
	 */
	<B> B getByConditionObject(String hql,Page page,Map<String,Object> paramMap);
	
}
