<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,javabean.ChainStore"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连锁店总览</title>
</head>
<body>

	<a href="chainStoreServlet?&action=toInsert">添加</a>
	<br /> 连锁店列表
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>店名</td>
			<td>位置</td>
			<td>房间数量</td>
			<td colspan="3" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
		</tr>
		<%
		    /* 		    List<ChainStore> allChainStores = (List<ChainStore>) request.getAttribute("allChainStores");
		     */ ArrayList<ChainStore> allChainStores = new ArrayList<ChainStore>();
		    Object obj = request.getAttribute("allChainStores");
		    if (obj instanceof ArrayList<?>) {
		        ArrayList<?> al = (ArrayList<?>) obj;
		        if (al.size() > 0) {
		            // 一个个转换过来.
		            for (int i = 0; i < al.size(); i++) {
		                // 还得判断是不是对应类型
		                Object o = al.get(i);
		                if (o instanceof ChainStore) {
		                    allChainStores.add((ChainStore) o);//timeSpent是前面自己定义的ArrayList
		                }
		            }
		        }
		    }
		    if (allChainStores.size() > 0) {
		        for (ChainStore c : allChainStores) {
		%>
		<tr>
			<td><%=c.getId()%></td>
			<td><%=c.getName()%></td>
			<td><%=c.getLocation()%></td>
			<td><%=c.getRoomcount()%></td>
			<td><a
				href="chainStoreServlet?id=<%=c.getId()%>&action=toUpdate">编辑</a></td>
			<td><a
				href="chainStoreServlet?id=<%=c.getId()%>&action=doDelete">删除</a></td>
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