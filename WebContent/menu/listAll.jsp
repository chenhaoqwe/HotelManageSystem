<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,javabean.Menu"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单总览</title>
</head>
<body>

	<a href="menuServlet?&action=toInsert">添加</a>
	<br /> 菜单列表
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>菜名</td>
			<td>描述</td>
			<td colspan="3" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
		</tr>
		<%
		    /*List<Menu> allMenus = (List<Menu>) ; 这个不安全，用以下替代*/
		    ArrayList<Menu> allMenus = new ArrayList<Menu>();
		    Object obj = request.getAttribute("allMenus");
		    if (obj instanceof ArrayList<?>) {
		        ArrayList<?> al = (ArrayList<?>) obj;
		        if (al.size() > 0) {
		            // 一个个转换过来.
		            for (int i = 0; i < al.size(); i++) {
		                // 还得判断是不是对应类型
		                Object o = al.get(i);
		                if (o instanceof Menu) {
		                    allMenus.add((Menu) o);//timeSpent是前面自己定义的ArrayList
		                }
		            }
		        }
		    }

		    if (allMenus.size() > 0) {
		        for (Menu m : allMenus) {
		%>
		<tr>
			<td><%=m.getId()%></td>
			<td><%=m.getName()%></td>
			<td><%=m.getInfo()%></td>
			<td><a href="menuServlet?id=<%=m.getId()%>&action=toUpdate">编辑</a></td>
			<td><a href="menuServlet?id=<%=m.getId()%>&action=doDelete">删除</a></td>
			<%-- 			<td><a href="menuServlet?id=<%=m.getId()%>&action=toInsert">添加</a></td> --%>
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