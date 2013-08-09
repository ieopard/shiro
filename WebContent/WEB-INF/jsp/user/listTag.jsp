<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript">

		if("${message}" != ""){
			alert("${message}")
		}
	
		$(function(){
			
			
		})
	</script>
	
  </head>
  
  <body>
  
 	<shiro:user>
     	<h2>welcome ${user.name } use the shiro framework</h2>
    </shiro:user>
    
    <shiro:guest>
     	<h2>you are no login ,please login</h2>
    </shiro:guest>
    
    <!-- 打印toString -->
    Hello, <shiro:principal/>, how are you today?
    
    <!--combined with the type attribute . Hello, <shiro:principal type="com.foo.User" property="firstName"/>, how are you today? -->
    
    <br/>
     ===================================================
    Hello, <shiro:principal property="name"/>
    <br/>
    <!-- The lacksRole tag is the logical opposite of the hasRole tag -->
    <shiro:lacksRole name="administrator">
    	Sorry, you are not allowed to administer the system.
	</shiro:lacksRole>
	 ===================================================
    <br/>
    <shiro:hasAnyRoles name="developer, project manager, administrator">
    	You are either a developer, project manager, or administrator.
	</shiro:hasAnyRoles>
	 ====================hasPermission===============================
	<br/>
	<shiro:hasPermission name="user:add">
    	Create a new User
	</shiro:hasPermission>
	<!-- The hasPermission tag is the logical opposite of the lacksPermission tag. -->
    <br/>
    =====================has==============================
    <br/>
    <!-- ???????????? -->
    <shiro:hasRole name="admin">
    	admin use the system
	</shiro:hasRole>    
	
	<shiro:hasRole name="again">
    	again use the system
	</shiro:hasRole> 
	<br/>
	==================================
	<br/> 
    <!-- The hasRole tag is the logical opposite of the lacksRole tag. -->
    <!-- 比user标签更严格 -->
     <shiro:authenticated>
    	Update your contact information
	</shiro:authenticated>  
	<br/>
	<a href="<c:url value='/logout'/>">登出</a>
	<br/>
	
	
	--------------------------------------------------
	<br/>
	<br/>
	<br/>
	<shiro:hasPermission name="user:add">
    	<a href="<c:url value='/toAdd'/>">新增</a>
    </shiro:hasPermission>
  	<table border="1px solid #ccc" width="50%">
  		
  		<tr> 
  			<td>编号</td>
  			<td>名称</td>
  			<td>真实姓名</td>
  			<td>密码</td>
  			<td>注册时间</td>
  			<td>操作</td>
  		</tr>
  		<c:forEach items="${list}" var="user">
  			<tr>
  				<td>&nbsp;${user.id }</td>
  				<td>&nbsp;${user.name }</td>
  				<td>&nbsp;${user.realname }</td>
  				<td>&nbsp;${user.password }</td>
  				<td>&nbsp;${user.registerTime }</td>
  				<td>&nbsp;
  				<shiro:hasPermission name="user:update">
  					<a href="<c:url value='/toUpdate?id=${user.id}'/>">修改</a> |
  				</shiro:hasPermission> 
  				<shiro:hasPermission name="user:delete">
  					<a href="javascript:if(confirm('确认删除吗？'))window.location.href='${pageContext.request.contextPath}/delete?id=${user.id}'">删除 </a>
  				</shiro:hasPermission>
  				</td>
  			</tr>
  		</c:forEach>
  	</table>
  	<a href="<c:url value='/privilege'/>">创建角色</a>&nbsp;&nbsp;&nbsp;<a href="<c:url value='/authorization'/>">授权</a>
  </body>
</html>
