<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page import="mvcModel.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Shopping Cart</title>
</head>
<body>
<%@ include file="navBar.html" %>
<div class="row">
	<div class="col s12"><p></p></div>
	<div class="col s12 m4 l2"><p></p></div>
	<div class="col s12 m4 l8"><p></p>
		<br>
		<h5>Shopping Cart</h5>
		<hr>
		<ul class="collection">
		<c:set var="sum" value="${0}"/>     
		
			<c:forEach items="${sessionScope.shoppingCart}" var="data">
			<li class="collection-item avatar">
				<i class="material-icons circle green">shopping_cart</i>
                    <span class="title">
						<c:out value="${data.id}"/>
						<c:out value="${data.customerID}"/>
						<c:out value="${data.startDate}"/>
						<li class="collection-item avatar">
						<c:forEach items="${data.getAllRooms()}" var="rooms">
							<br>
							<c:out value=${room.name}/>
						    <fmt:parseNumber var="i" type="number" value="${room.price}" />
						    <c:out value="${i}"/>
						    	
						    <c:set var="sum" value="${sum + i}" />
						    
						</c:forEach>
						</li>
						
					</span>					
			</c:forEach>
			</li>
		</ul>
	</div>
	<c:out value="${sum}"/>
	
</div>
<div class="col s12 m4 l2"><p></p></div>

</body>
</html>