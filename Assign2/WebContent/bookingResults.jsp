<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${requestScope.booking.getId()}
${requestScope.booking.getStartDate()}
${requestScope.booking.getEndDate()}

<c:if test="${empty errorLOG}">

	<c:out value="Your Pin Was Right!!"/>
</c:if>


<form action="home" method="post">
     <input type="text" name="password"><br>
     <input type="hidden" name="action" value="checkBooking" />
     <input type="submit" value="Remove" />
     
</form>
</body>
</html>
