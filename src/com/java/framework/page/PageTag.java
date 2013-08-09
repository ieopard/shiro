package com.java.framework.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

/**
 * @author pengluwei
 *
 */
public class PageTag extends TagSupport{

	
	private String bean = "page";
	
	private String url = null;
	
	private boolean skip = true;
	
	private String classStyle = "";
	
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter writer = pageContext.getOut();
		url = resolveUrl(url,pageContext);
		
		Page page = (Page) pageContext.getRequest().getAttribute(bean);
		
		if(page == null) return SKIP_BODY;
		
		StringBuilder sbd = new StringBuilder();
		
		sbd.append("<span>共"+page.getInfo().getTotalRecord()+"条记录,&nbsp;</span>");
		sbd.append("<span>当前"+page.getIndex()+"/</span><span id='pageSpan'>"+page.getInfo().getTotalPage()+"</span>页&nbsp;");
		sbd.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		if(page.getIsHasPre()){
			sbd.append("<a href="+appendUrl(url,"pn",1)+">首页</a>");
			sbd.append("&nbsp;&nbsp;");
			sbd.append("<a href="+appendUrl(url,"pn",(page.getIndex()-1))+">上一页</a>");
		}else{
			sbd.append("<a href='javascript:;'>首页</a>");
			sbd.append("&nbsp;&nbsp;");
			sbd.append("<a href='javascript:;'>上一页</a>");
		}
		sbd.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		if(page.getIsHasNext()){
			sbd.append("<a href="+appendUrl(url,"pn",(page.getIndex()+1))+">下一页</a>");
			sbd.append("&nbsp;&nbsp;");
			sbd.append("<a href="+appendUrl(url,"pn",page.getInfo().getTotalPage())+">末页</a>");
		}else{
			sbd.append("<a href='javascript:;'>下一页</a>");
			sbd.append("&nbsp;&nbsp;");
			sbd.append("<a href='javascript:;'>末页</a>");
		}
		sbd.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		if(skip){
			sbd.append(skip(page));
		}
		try {
			writer.write(sbd.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	private String skip(Page page) {
		StringBuilder sbd = new StringBuilder();
		sbd.append(" 跳转到");
		sbd.append("<input type='text' name='pn' id='pn' value="+page.getIndex()+">");
		sbd.append("&nbsp;&nbsp;");
		sbd.append("<a href='javascript:;' id='btn' class='"+classStyle+"'>GO</a>");
		return sbd.toString();
	}
	private String resolveUrl(String url,PageContext pageContext){
		try {
			url = UrlSupport.resolveUrl(url, null, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	private String appendUrl(String url,String key,int pn){
		
		if(url.indexOf("?") == -1){
			
			url = url + "?"+key+"="+pn;
			
		}else{
			
			if(url.endsWith("?")){
				url = url +key+"="+pn;
			}else{
				 url = url + "&amp;" + key + "=" + pn;
			}
		}
		return url;
	}
	
	public String getBean() {
		return bean;
	}
	public void setBean(String bean) {
		this.bean = bean;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isSkip() {
		return skip;
	}
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
	
}
