<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="home" method="POST">
	<input type="hidden" name="action" value="logoutYes" />
	<input type="submit" value="Yes" />
	
	</form>
	<form action="home" method="POST">
	<input type="hidden" name="action" value="logoutNo" />
	<input type="submit" value="No" />
	
	</form>
	
</body>
</html>