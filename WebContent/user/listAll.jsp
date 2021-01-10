<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,javabean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户总览</title>
</head>
<body>

	<a href="userServlet?&action=toInsert">添加</a>
	<br /> 用户列表
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>用戶名</td>
			<td>年龄</td>
			<td colspan="3" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
		</tr>
		<%
		    /* 		    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
		     */ ArrayList<User> allUsers = new ArrayList<User>();
		    Object obj = request.getAttribute("allUsers");
		    if (obj instanceof ArrayList<?>) {
		        ArrayList<?> al = (ArrayList<?>) obj;
		        if (al.size() > 0) {
		            // 一个个转换过来.
		            for (int i = 0; i < al.size(); i++) {
		                // 还得判断是不是对应类型
		                Object o = al.get(i);
		                if (o instanceof User) {
		                    allUsers.add((User) o);//timeSpent是前面自己定义的ArrayList
		                }
		            }
		        }
		    }
		    if (allUsers.size() > 0) {
		        for (User u : allUsers) {
		%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getName()%></td>
			<td><%=u.getAge()%></td>
			<td><a href="userServlet?id=<%=u.getId()%>&action=toUpdate">编辑</a></td>
			<td><a href="userServlet?id=<%=u.getId()%>&action=doDelete">删除</a></td>
			<%-- 			<td><a href="userServlet?id=<%=u.getId()%>&action=toInsert">添加</a></td> --%>
		</tr>
		<%
		    }
		    } else {
		%>
		<tr>
			<td>沒有任何记录</td>
		</tr>
		<%
		    }
		%>
	</table>
	   <a href="../index.jsp">返回主页</a>
</body>

</html>