package com.java.framework.page;


/**
 * @author pengluwei
 *
 */
public class PageInfo {

	public static final int showPage = 2;
	/**
	 * 总记录数
	 */
	private static int totalRecord;
	
	/**
	 * 开始页
	 */
	private int startPage;
	
	/**
	 * 结束页
	 */
	private int endPage;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	
	/**
	 * 总页数
	 */
	private int totalPage;

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		PageInfo.totalRecord = totalRecord;
	}

	public int getStartPage() {
		return (currentPage -1)* showPage;
	}

	public int getEndPage() {
		return startPage + showPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return PageInfo.totalRecord % showPage == 0 ? PageInfo.totalRecord / showPage : PageInfo.totalRecord / showPage+1;
	}
	
	@Override
	public String toString() {
		return "[totalRecord=" + totalRecord + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", currentPage=" + currentPage
				+ ", totalPage=" + totalPage + "]";
	}
}
