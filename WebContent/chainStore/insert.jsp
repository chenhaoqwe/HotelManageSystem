<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连锁店添加 </title>
</head>
<body>
    连锁店添加 <br />
    <form action="chainStoreServlet?action=doInsert" method="post">
        店名：<input type="text" name="name" /><br />
        位置：<input type="text" name="location"  /><br /> 
        房间数量：<input type="text" name="roomcount" /><br /> 
        <input type="submit" value="提交" /><br />
    </form>
</body>
</html>