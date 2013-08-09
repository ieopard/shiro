package com.java.framework.page;

import java.util.List;

/**
 * @author pengluwei
 *
 */
public class Page {

	/**
	 * 是否首页
	 */
	private boolean isHasPre;
	/**
	 * 是否末页
	 */
	private boolean isHasNext; 
	
	/**
	 * 第几页
	 */
	private int index;
	
	/**
	 * 纪录数
	 */
	private List<?> itemsList;
	
	private PageInfo info = new PageInfo();
	
	public boolean getIsHasPre() {
		return isHasPre = index == 1 ? false : true;
	}
	public boolean getIsHasNext() {
		return isHasNext = index >= getInfo().getTotalPage() ? false : true;
	}
	
	public List<?> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<?> itemsList) {
		this.itemsList = itemsList;
	}
	public PageInfo getInfo() {
		info.setCurrentPage(index);
		return info;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		//if(index != 1 && index > getInfo().getTotalPage()) index = getInfo().getTotalPage();
		if(getInfo().getTotalPage() > 0){
			if(index > getInfo().getTotalPage()){
				index = getInfo().getTotalPage();
			}
		}
		this.index = index;
	}
	@Override
	public String toString() {
		return "[isHasPre=" + isHasPre + ", isHasNext=" + isHasNext + ", index = " + index
				+ ", itemsList=" + itemsList + " PageInfo = "+info+"]\n";
	}
}
