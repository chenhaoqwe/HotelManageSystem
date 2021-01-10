<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,javabean.Notific"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告总览</title>
</head>
<body>

	<a href="notificServlet?&action=toInsert">添加</a>
	<br /> 公告列表
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>内容</td>
			<td>编写者</td>
			<td colspan="3" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
		</tr>
		<%
		    /*List<Notific> allNotifics = (List<Notific>) ; 这个不安全，用以下替代*/
		    ArrayList<Notific> allNotifics = new ArrayList<Notific>();
		    Object obj = request.getAttribute("allNotifics");
		    if (obj instanceof ArrayList<?>) {
		        ArrayList<?> al = (ArrayList<?>) obj;
		        if (al.size() > 0) {
		            // 一个个转换过来.
		            for (int i = 0; i < al.size(); i++) {
		                // 还得判断是不是对应类型
		                Object o = al.get(i);
		                if (o instanceof Notific) {
		                    allNotifics.add((Notific) o);//timeSpent是前面自己定义的ArrayList
		                }
		            }
		        }
		    }

		    if (allNotifics.size() > 0) {
		        for (Notific m : allNotifics) {
		%>
		<tr>
			<td><%=m.getId()%></td>
			<td><%=m.getNewscontent()%></td>
			<td><%=m.getWriter()%></td>
			<td><a href="notificServlet?id=<%=m.getId()%>&action=toUpdate">编辑</a></td>
			<td><a href="notificServlet?id=<%=m.getId()%>&action=doDelete">删除</a></td>
			<%-- 			<td><a href="notificServlet?id=<%=m.getId()%>&action=toInsert">添加</a></td> --%>
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