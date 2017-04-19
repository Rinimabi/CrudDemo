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
	<div class="div_loadcontain" id="Div">
		<form action="login.spring" method="post" class="form_load">
			<input type="text" class="input_load_username" placeholder="Username" name="username">
			<br> 
			<input type="password" class="input_load_password" placeholder="Password" name="password">
			<br>
			<button class="buttom_load_submit" type="submit">Submit</button>
			<br> 
			<a href="Register.jsp" class="a_load_text">Register</a> 
			<a href="Loading.jsp" class="a_load_text" style="margin-left: 8px;">Retrieve</a>
			<a href="Loading.jsp" class="a_load_text" style="margin-left: 8px;">OtherWay</a>
		</form>
	</div>
</body>
</html>