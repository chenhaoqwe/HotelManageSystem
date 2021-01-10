<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店添加</title>
</head>
<body>
   酒店添加  <br />
    <form action="hotelServlet?action=doInsert" method="post">
        房间名：<input type="text" name="name" /><br />
        状态：<input type="text" name="state"  /><br /> 
        <input type="submit" value="提交" /><br />
    </form>
</body>
</html>