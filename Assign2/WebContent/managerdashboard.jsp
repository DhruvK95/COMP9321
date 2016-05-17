<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manager Dashboard</title>
</head>
<body>
	<%@ include file="navBar.html"%>
	<div class="row">
		<div class="col s12">
			<h3>Manager Dashboard</h3>
			<p>${ message }</p>
			<hr>
			<h4>Occupied rooms</h4>
			<table>
				<tr>
					<th>Room ID</th>
					<th>Room Name</th>
				</tr>
				<c:forEach items="${occupied_rooms}" var="data">
					<tr>
						<td><c:out value="${data.id}" /></td>
						<td><c:out value="${data.name}" /></td>
					</tr>
				</c:forEach>
			</table>
			<hr>
			<h4>Bookings</h4>
			<div class="row">
				<div class="col s6" style="border-right: 1px solid;">
					<h5>Not Checked in</h5>
					<form action="dashboard" method="post">
						<input type="hidden" name="action" value="checkin" />
						<table>
							<tr>
								<th>Booking ID</th>
								<th>Customer ID</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th></th>
							</tr>
							<c:forEach items="${not_checked_in}" var="data">
								<tr>
									<td><c:out value="${data.id}" /></td>
									<td><c:out value="${data.customerID}" /></td>
									<td><c:out value="${data.startDate}" /></td>
									<td><c:out value="${data.endDate}" /></td>
									<td>
										<button class="btn waves-effect waves-light" type="submit"
											name="booking_id" value="<c:out value="${data.id}" />">
											Check In</button>
									</td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
				<div class="col s6">
					<h5>Checked In</h5>
					<form action="dashboard" method="post">
						<input type="hidden" name="action" value="checkout" />
						<table>
							<tr>
								<th>Booking ID</th>
								<th>Customer ID</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th></th>
							</tr>
							<c:forEach items="${checked_in}" var="data">
								<tr>
									<td><c:out value="${data.id}" /></td>
									<td><c:out value="${data.customerID}" /></td>
									<td><c:out value="${data.startDate}" /></td>
									<td><c:out value="${data.endDate}" /></td>
									<td>
										<button class="btn waves-effect waves-light" type="submit"
											name="booking_id" value="<c:out value="${data.id}" />">
											Check Out</button>
									</td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
