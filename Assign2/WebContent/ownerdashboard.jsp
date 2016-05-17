<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Owner Dashboard</title>
</head>
<body>
	<%@ include file="navBar.html"%>
	<div class="row">
		<div class="col s12">
			<h3>Owner Dashboard</h3>
			<p>${ message }</p>
			<hr>
			<h4>Occupancy</h4>
			<table>
				<tr>
					<th>Hotel ID</th>
					<th>Hotel</th>
					<th>Rooms Occupied</th>
					<th>Rooms Available</th>
				</tr>
				<c:forEach items="${occupancy}" var="data">
					<tr>
						<td><c:out value="${data.key.id}" /></td>
						<td><c:out value="${data.key.hotelName}" /></td>
						<td><c:out value="${data.value.getFirst()}" /></td>
						<td><c:out value="${data.value.getSecond()}" /></td>
					</tr>
				</c:forEach>
			</table>

			<hr>
			<h4>Room maintenance</h4>
			<form action="dashboard" method="post">
				<input type="hidden" name="action" value="maintenance" />
				<table>
					<tr>
						<th>Hotel ID</th>
						<th>Room</th>
						<th>Available for use</th>
						<th></th>
					</tr>
					<c:forEach items="${rooms}" var="data">
						<tr>
							<td><c:out value="${data.parentHotelID}" /></td>
							<td><c:out value="${data.id}" /></td>
							<td><c:out value="${data.availableStatus}" /></td>
							<td>
								<button class="btn waves-effect waves-light" type="submit"
									name="room_id" value="<c:out value="${data.id}" />">Toggle
									Availability</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
