<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告更新</title>
</head>
<body>
	公告更新
	<br />
	<form action="notificServlet?id=${requestScope.notific.id}&action=doUpdate" method="post">
		菜名：<input type="text" name="newscontent" value="${requestScope.notific.newscontent}" /><br /> 
		描述：<input type="text" name="writer" value="${requestScope.notific.writer}" /><br />
		<input type="submit" value="提交" /><br />
	</form>
</body>
</html>