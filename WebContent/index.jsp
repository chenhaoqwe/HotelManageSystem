<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="error/error.jsp" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
    <a href="user/userServlet?action=listAll" >客户列表</a><br/>
    <a href="admin/adminServlet?action=listAll" >管理员列表</a><br/>
    <a href="hotel/hotelServlet?action=listAll" >本店列表</a><br/>
    <a href="chainStore/chainStoreServlet?action=listAll" >连锁店列表</a><br/>
    <a href="menu/menuServlet?action=listAll" >菜单列表</a><br/>
    <a href="notific/notificServlet?action=listAll" >告知栏列表</a><br/>
</body>
</html>