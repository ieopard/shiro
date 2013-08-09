/**
 * 
 */
package com.java.framework.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.framework.page.Page;
import com.java.module.entity.PrivilegeRole;

/**
 * @author pengluwei
 * @version 1.0
 */
public abstract class DaoBaseSupport<T extends Serializable,PK extends Serializable> implements Dao<T,PK>{
	
	 protected static final Logger log = LoggerFactory.getLogger(DaoBaseSupport.class);
	 
	 private Class<T> clazz;
	 final String HQL_ALL;
	 final String HQL_ALL_COUNT;
	
	{
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		HQL_ALL =  "from "+clazz.getName()+" ";
		HQL_ALL_COUNT =  "select count(*) from "+clazz.getName()+" ";
	}
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	public Statistics getStatistics(){
		return sessionFactory.getStatistics();
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void flush()   {
		getSession().flush();
	}
	
	public void clear()   {
		getSession().clear();
	}

	public void deleteEntity(Object obj)   {
		getSession().delete(obj);
	}
	
	public void delete(Serializable id)   {
		getSession().delete(this.get(id));
	} 
	public void delete(Class<?> c,Serializable id)   {
		getSession().delete(this.get(c,id));
	} 

	public void deleteList(Class<?> c,Collection<? extends Serializable> objs) {
		
		for(Serializable obj : objs){
			deleteEntity(obj);
		}
		flush();
	}
	
	public T get(Serializable id)   {
		return get(clazz,id);
	}
	
	public <B> B get(Class<?> c,Serializable id)   {
		return  (B) getSession().get(c,id);
	}

	public T load(Serializable id)   {
		return (T) getSession().load(clazz,id);
	}


	public void save(Object obj) {
		getSession().save(obj);
	}


	public void saveOrUpdate(Object obj)   {
		getSession().saveOrUpdate(obj);
	}

	public void update(Object obj)   {
		getSession().update(obj);
	}
	
	public <B> B getAll(Class<? extends Serializable> c){
		return resultObject("from "+c.getName()+" ", -1, -1);
	}
	
	public List<T> getAll() {
		return getAll(-1,-1);
	}


	public List<T> getAll(int start, int end) {
		return getAll(null, start, end);
	}


	public List<T> getAll(String orderBy, int start, int end) {
		return getAll(orderBy, null, start, end);	
	}

	public List<T> getAll(String orderBy, String groupBy, int start, int end) {
		
		StringBuilder sbd = new StringBuilder(HQL_ALL);
		sbd.append(groupBy == null ? "" : " group by "+groupBy);
		sbd.append(orderBy == null ? "" : " order by "+orderBy);
		
		return resultObject(sbd.toString(), start, end);
	}


	public Integer getAllCount() {
		Query query = getSession().createQuery(HQL_ALL_COUNT);
		return   ((Long) query.uniqueResult()).intValue();
	}

	public Integer getByConditionCount(String hql, Object... parameters) {
		
		Query query = getQuery(resultCount(hql), parameters);
		return result(hql,query);
	}
	
	public Integer getByConditionCount(String hql, Map<String,Object> paramMap) {
		Query query = getQuery(resultCount(hql), paramMap);
		
		return result(hql,query);
	}

	
	@Override
	public <B> B getByConditionObject(String hql, Page page,Object... parameters) {
		return resultObject(hql, page, parameters);
	}
	@Override
	public <B> B getByConditionObject(String hql, Page page,Map<String,Object> paramMap) {
		return resultObject(hql, page, paramMap);
	}

	public List<T> getByCondition(String hql, Object... parameters) {
		return getByCondition(hql, -1,-1, parameters);
	}

	public List<T> getByCondition(String hql, int start, int end,
			Object... parameters) {
		
		return resultObject(hql, start, end, parameters);
	}

	
	public List<T> getByCondition(String hql, int start, int end,
			Map<String,Object> paramMap) {
		
		return resultObject(hql, start, end, paramMap);
	}

	public List<T> getByCondition(String hql, Map<String,Object> paramMap) {
		return getByCondition(hql, -1, -1, paramMap);
	}
	
	public <B> B getByConditionResultObject(String hql,int start,int end,Object... parameters){
		return resultObject(hql, start, end, parameters);
	}

	private Integer result(String hql,Query query){
		
		
		int count;
		if(hql.toLowerCase().indexOf("group by") == -1){
			count = query.uniqueResult() != null ? ((Long) query.uniqueResult()).intValue() : 0;
		}else
			count = query.list().size() > 0 ? Integer.parseInt(query.list().get(0)+"") : 0; 
		
		return count;
	}
	
	private static void setParamenter(Query query,Object... parameters){
		if(parameters != null && parameters.length > 0){
			if(parameters.length == 1 && parameters[0] instanceof Map){
				Map<String,Object> paramMap = (Map<String, Object>) parameters[0];
				for(Entry<String,Object> e : paramMap.entrySet()){
					String key = e.getKey();
					Object value = e.getValue();
					query.setParameter(key,value);
				}
			}else{
				int count = 0;
				for(Object obj : parameters){
					query.setParameter(count++,obj);
				}
			}
		}
	}

	private String resultCount(String hql){
		String countStr = !hql.startsWith("from") && hql.indexOf("from") != -1 ? hql.substring(hql.indexOf("from")) : hql;
		return "select count(*) "+countStr;
	}
	
	private <B> B resultObject(String hql,int start,int end,Object... parameters){
		
		return resultObject(hql, null, start, end, parameters);
	}
	private <B> B resultObject(String hql,Page 
			page,Object... parameters){
		
		return resultObject(hql, page, -1, -1, parameters);
	}
	
	private <B> B resultObject(String hql,Page page,int start,int end,Object... parameters){
		Query query = getQuery(hql, parameters); 
		if(pageValidate(query, page)){
			return (B) page;
		}else{
			pageingValidate(query, start, end);
			return (B) query.list();
		}
	}
	
	private boolean pageValidate(Query query,Page page){
		if(page != null){
			query.setFirstResult(page.getInfo().getStartPage()).setMaxResults(page.getInfo().getEndPage());
			page.setItemsList(query.list());
			return true;
		}
		return false;
	}
	
	private void pageingValidate(Query query,int start,int end){
		if(start != -1 && end != -1){
			query.setFirstResult(start).setMaxResults(end);
		}
	}
	
	private Query getQuery(String hql,Object... obj){
		Query query = getSession().createQuery(hql);
		setParamenter(query, obj);
		query.setCacheable(true);
		System.out.println("--------------query cache---------------");
		return query;
	}
}




