<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户更新</title>
</head>
<body>
	用户更新
	<br />
	<form action="userServlet?id=${requestScope.user.id}&action=doUpdate" method="post">
		用户名：<input type="text" name="name" value="${requestScope.user.name}" /><br />
		 密码：<input type="password" name="password" value="${requestScope.user.password}" /><br />
		年龄：<input type="text" name="age" value="${requestScope.user.age}" /><br />
		<input type="submit" value="提交" /><br />
	</form>
</body>
</html>