<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单添加</title>
</head>
<body>
   菜单添加  <br />
    <form action="menuServlet?action=doInsert" method="post">
        菜名：<input type="text" name="name" /><br />
        描述：<input type="text" name="info"  /><br /> 
        <input type="submit" value="提交" /><br />
    </form>
</body>
</html>