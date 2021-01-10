<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告添加</title>
</head>
<body>
   公告添加  <br />
    <form action="notificServlet?action=doInsert" method="post">
        内容：<input type="text" name="newscontent" /><br />
        编写者：<input type="text" name="writer"  /><br /> 
        <input type="submit" value="提交" /><br />
    </form>
</body>
</html>