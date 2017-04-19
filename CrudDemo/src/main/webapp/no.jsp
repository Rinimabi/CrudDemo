<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/body.css">
<title>Loading</title>
</head>
<body class="body_background">
	<div class="div_loadcontain">
		<form action="login.spring" method="post" class="form_load">
		    <font color="red">Sorry</font>，没有${username }这个用户!
            <br />
            <a href="Loading.jsp">重试一下！</a>
		</form>
	</div>
</body>
</html>