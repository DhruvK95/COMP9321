<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Logout ?</title>
</head>
<body>
<%@ include file="navBar.html"%>
<div class="row">
	<div class="col s12"><p></p></div>
	<div class="col s12 m4 l2"><p></p></div>
	<div class="col s12 m4 l8"><p></p>
		<h5> Are you sure u wanna logout? </h5>
		<br>
		<form action="home" method="POST">
			<div class="input-field col s2">
			<input type="hidden" name="action" value="logoutYes" />
			<input type="submit" value="Yes" />
			</div>
		</form>
		<form action="home" method="POST">
			<div class="input-field col s2">
			<input type="hidden" name="action" value="logoutNo" />
			<input type="submit" value="No" />
			</div>
		</form>
	</div>
	<div class="col s12 m4 l2"><p></p></div>
</div>
</body>
</html>