<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员更新 </title>
</head>
<body>
	管理员更新 <br />
	<form action="adminServlet?id=${requestScope.admin.id}&action=doUpdate" method="post">
		 管理员用户名：<input type="text" name="name" value="${requestScope.admin.name}" /><br />
		密码：<input type="password" name="password" value="${requestScope.admin.password}" /><br /> 
		年龄：<input type="text" name="age" value="${requestScope.admin.age}" /><br /> 
		<input type="submit" value="提交" /><br />
	</form>
</body>
</html>