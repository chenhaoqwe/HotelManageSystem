<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,javabean.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员总览</title>
</head>
<body>

	<a href="adminServlet?&action=toInsert">添加</a>
	<br /> 管理员列表
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>用戶名</td>
			<td>年龄</td>
			<td colspan="3" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
		</tr>
		<%
		    /* 		    List<Admin> allAdmins = (List<Admin>) request.getAttribute("allAdmins");
		     */ ArrayList<Admin> allAdmins = new ArrayList<Admin>();
		    Object obj = request.getAttribute("allAdmins");
		    if (obj instanceof ArrayList<?>) {
		        ArrayList<?> al = (ArrayList<?>) obj;
		        if (al.size() > 0) {
		            // 一个个转换过来.
		            for (int i = 0; i < al.size(); i++) {
		                // 还得判断是不是对应类型
		                Object o = al.get(i);
		                if (o instanceof Admin) {
		                    allAdmins.add((Admin) o);//timeSpent是前面自己定义的ArrayList
		                }
		            }
		        }
		    }
		    if (allAdmins.size() > 0) {
		        for (Admin a : allAdmins) {
		%>
		<tr>
			<td><%=a.getId()%></td>
			<td><%=a.getName()%></td>
			<td><%=a.getAge()%></td>
			<td><a href="adminServlet?id=<%=a.getId()%>&action=toUpdate">编辑</a></td>
			<td><a href="adminServlet?id=<%=a.getId()%>&action=doDelete">删除</a></td>
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