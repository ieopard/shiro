/**
 * 
 */
package com.java.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author pengluwei
 * @since 2013-7-4下午02:20:26
 * @version 1.0
 */

@Entity
@Table(name="view_user")
public class ViewUser implements Serializable{

	private ViewUserPK viewUserPK;
	
	private String name;
	
	private String password;
	
	private Date registerTime;

	private String productName;
	
	@EmbeddedId
	public ViewUserPK getViewUserPK() {
		return viewUserPK;
	}

	public void setViewUserPK(ViewUserPK viewUserPK) {
		this.viewUserPK = viewUserPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="register_time")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	@Column(name="pname")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((viewUserPK == null) ? 0 : viewUserPK.hashCode());
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
		ViewUser other = (ViewUser) obj;
		if (viewUserPK == null) {
			if (other.viewUserPK != null)
				return false;
		} else if (!viewUserPK.equals(other.viewUserPK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ViewUser [viewUserPK=" + viewUserPK + ", name=" + name
				+ ", password=" + password + ", registerTime=" + registerTime
				+ ", productName=" + productName + "]";
	}
}
