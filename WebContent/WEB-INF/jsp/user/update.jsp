<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <body>
  		<form action="${pageContext.request.contextPath}/update" method="post">
  		
  			  <input type="hidden" value="${command.id}" name="id"/>
  			  
  			  <label for="name" class="label">用户名:</label>
              <input type="text" name="name" value="${command.name }"/><br/>
              
              <label for="name" class="label">真实姓名:</label>
              <input type="text" name="realname" value="${command.realname }"/><br/>
              
              <label for="name" class="label">年龄:</label>
              <input type="text" name="age" value="${command.age }"/><br/>

              <label for=password class="label">密码:</label>
              <input type="text" name="password" value="${command.password }"/><br/>

              <label for="registerTime" class="label" >注册时间:</label>
              <input type="text" name="registerTime" value="${command.registerTime}"
                              readonly="readonly"
                              class="Wdate"
                              onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>(格式2010-2-2 22:22:22)<br/>

             <label class="label"/><input type="submit" value="提交"/>&nbsp;<a href="<c:url value="/list"/>">取消</a><br/>
  		
  		</form>
  </body>
</html>
