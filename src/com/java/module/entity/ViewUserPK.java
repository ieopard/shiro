/**
 * 
 */
package com.java.module.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author pengluwei
 * @since 2013-7-4下午02:21:14
 * @version 1.0
 */
@Embeddable
public class ViewUserPK implements Serializable{
	
	private Long uid;
	
	private Long pid;

	
	private ViewUserPK() {
	}

	private ViewUserPK(Long uid, Long pid) {
		super();
		this.uid = uid;
		this.pid = pid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewUserPK other = (ViewUserPK) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ViewUserPK [uid=" + uid + ", pid=" + pid + "]";
	}
}
