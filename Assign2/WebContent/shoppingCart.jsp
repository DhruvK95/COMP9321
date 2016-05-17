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
	<div class="col s12 m4 l8">
		<br>
		<h5>Shopping Cart</h5>
		<hr>
		<c:set var="sum" value="${0}"/>

		<c:forEach items="${sessionScope.shoppingCart}" var="data">	
			<h6>Booking <b>From:</b> <c:out value="${data.startDate}"/>
				<b>To:</b> <c:out value="${data.endDate}"/>
			</h6>
			<br>
			<ul class="collection">
				<c:forEach items="${data.getAllRooms()}" var="rooms">
													
					<li class="collection-item avatar">
						<i class="material-icons circle green">vpn_key</i>
                    <span class="title">
					<c:out value="${rooms.name}"/> Room
					<fmt:parseNumber var="i" type="number" value="${rooms.price}" />
						<br>
					$ <c:out value="${i}"/> per night

					<c:if test="${rooms.name != 'Single'}">
						<%-- Add Bed button--%>
						<c:forEach items="${data.getExtraBedCheck()}" var="hash">
						<c:if test="${hash.key eq rooms.id}">
							
							<c:if test="${not hash.value}">
							
								<form  action="home" method="post">
								<input type="hidden" name="removeExtraBedID" value="${rooms.id}" />
								<input type="hidden" name="removeExtraBedBookingID" value="${data.id}" />
								<input type="hidden" name="action" value="addBed" />
								<button class="btn waves-effect waves-light green" type="submit"> Add Bed
									<i class="material-icons right">add</i>
								</button>
								</form>
							</c:if>
						<%-- Remove Bed button --%>
							<c:if test="${hash.value}">
								<c:set var="sum" value="${sum + 35}" />
							
								<form  action="home" method="post">
									<input type="hidden" name="removeExtraBedID" value="${rooms.id}" />
									<input type="hidden" name="removeExtraBedBookingID" value="${data.id}" />
									<input type="hidden" name="action" value="removeBed" />
									<button class="btn waves-effect waves-light orange" type="submit"> Remove Bed
										<i class="material-icons right">remove</i>
									</button>
								</form>
							</c:if>
							
						</c:if>
						</c:forEach>
						

					</c:if>

					<%-- Remove room button --%>
					<a class="secondary-content">
                    <form  action="home" method="post">
						<input type="hidden" name="roomToRemoveID" value="${rooms.id}" />
						<input type="hidden" name="roomToRemoveBooking" value="${data.id}" />
						<input type="hidden" name="action" value="removeRoomFromCart" />
						<button class="btn-floating waves-effect waves-light red" type="submit">
							<i class="material-icons right">delete</i>
						</button>
        			</form>
        			</a>
						<br>
						<br>
					<c:set var="sum" value="${sum + i}" />
				</c:forEach>
			</ul>
		</c:forEach>
		<div class="row">
			<blockquote> Total $ <c:out value="${sum}"/> per night </blockquote>
		</div>
		<div class="row">
			<form  action="home" method="post">
				<input type="hidden" name="action" value="toCheckout" />
				<button class="btn waves-effect waves-light green" type="submit">Checkout
					<i class="material-icons right">credit_card</i>
				</button>
			</form>
		</div>
	</div>
</div>

<div class="col s12 m4 l2"><p></p></div>

</body>
</html>