<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'result.jsp' starting page</title>
    
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">

		$(function(){

			$("#add").click(function(){
				$("#form div").append("上传文件：").append($('<input type="file" name="file" /><br/>'));
				return false;
			});
		});
	</script>
  </head>
  
  <body>
  	${fileUrl }<br/>
    --------------------------------------------
    
    <br/>
    <form method="post" enctype="multipart/form-data" id="form">
    	<div>
		上传文件：<input type="file" name="file" /><br/>
		</div>
				<input type="submit" value="添加" id="add"/>
		
				 <input type="submit" value="提交" />
	</form>
	<br/>
	--------------------------------------------
	<br/>
	<form action="${pageContext.request.contextPath}/topic/download" method="post">
		 <input type="submit" value="submit" />
	</form>
  </body>
</html>
