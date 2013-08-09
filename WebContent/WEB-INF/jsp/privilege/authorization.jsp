<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>


<form method="post" >
	角色名称：
	<c:if test="${! empty list }">
		<c:forEach items="${list }" var="p" varStatus="ps">
			<div>
				<label for="p"><input type="checkbox" name="roleid" value="${p.id }"/>${p.name }</label>
				<c:if test="${(ps.index+1) % 5 == 0 }">
					<br/>
				</c:if>
			</div>
		</c:forEach>
	</c:if>
	<br/>
	<br/>
	用户名称：
	<c:if test="${! empty users }">
		<c:forEach items="${users }" var="p" varStatus="ps">
			<div>
				<label for="p"><input type="checkbox" name="userid" value="${p.id }"/>${p.name }</label>
				<c:if test="${(ps.index+1) % 5 == 0 }">
					<br/>
				</c:if>
			</div>
		</c:forEach>
	</c:if>
	<input type="submit" value="授权"/>
</form>


</body>
</html>