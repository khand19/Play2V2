<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/main.css">
</head>
<body>

	<header class="topbar">
	<h1 class="fill">
		<a href="/Play2v2/Computers.html"> Kevin database </a>
	</h1>
	</header>
<div style="position:absolute;top:65px;left:60px;">
	<center><form method="post" id="loginForm" action="<c:url value='j_spring_security_check'/>">
	<input type="text" name="j_username" id="j_username" />
	<input type="password" name="j_password" id="j_password"/>
	<input type="submit" value="OK" class="btn success"/>
	</form>
	</center>
	</div>
</body>
</html>