<%--
  User: Dhruv
  Date: 15/05/2016
  Time: 5:53 PM
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Profile</title>
</head>

<body>

<%@ include file="navBar.html"%>
<form class="col s12" action="home" method="post">

    <div class="row">
        <div class="col s12"><p></p></div>
        <div class="col s12 m4 l2"><p></p></div>
        <div class="col s12 m4 l8"><p></p>
            <br>
            <p class="text-right">Details for CheckOut</p>
            <c:choose>
                <c:when test="${empty sessionScope.currUser.getCc_name()}">
                    <h5 style="color:orange;"> Please fill in these fields to Checkout </h5>
                        <div class="row">
                            <div class="section input-field col s6">
                                <input id="first_name" type="text" name="first_name" class="validate" value="${sessionScope.currUser.getFirst_name()}" required>
                                <label for="first_name">First Name</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="last_name" type="text" name="last_name"
                                       value="${sessionScope.currUser.getLast_name()}" class="validate" required>
                                <label for="last_name">Last Name</label>
                            </div>
                        </div>
                        <div class="input-field col s12">
                            <input id="email" type="email" name="email" class="validate"
                                   value="${sessionScope.currUser.getEmail()}" required>
                            <label for="email">Email</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="address" type="text" name="address" class="validate" required>
                            <label for="address">Address</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_number" type="number" name="cc_number" class="validate" required>
                            <label for="cc_number">Credit Card Number</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_name" type="text" name="cc_name" class="validate" required>
                            <label for="cc_name">Name on Credit Card</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_expiry" type="date" name="cc_expiry" class="datepicker" required>
                            <label for="cc_expiry">Expiry Date</label>
                        </div>
                        
                </c:when>
                <c:otherwise>
                        <h4><c:out value="${sessionScope.currUser.getUser_name()}" /></h4>
                        <hr>
                        <div class="row">
                            <div class="section input-field col s6">
                                <input id="first_name" type="text" name="first_name" class="validate"
                                       value="${sessionScope.currUser.getFirst_name()}" required>
                                <label for="first_name"><c:out value="First Name" /></label>
                            </div>
                            <div class="input-field col s6">
                                <input id="last_name" type="text" name="last_name" class="validate"
                                       value="${sessionScope.currUser.getLast_name()}" required>
                                <label for="last_name"><c:out value="Last Name" /></label>
                            </div>
                        </div>
                        <div class="row">
                        </div>
                            <%--<div class="row">--%>
                        <div class="input-field col s12">
                            <input id="email" type="email" name="email" class="validate"
                                   value="${sessionScope.currUser.getEmail()}" required>
                            <label for="email"><c:out value="Email" /></label>
                        </div>
                        <div class="input-field col s12">
                            <input id="address" type="text" name="address" class="validate"
                                   value="${sessionScope.currUser.getAddress()}" required>
                            <label for="address"><c:out value="Address" /></label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_number" type="number" name="cc_number" class="validate"
                                   value="${sessionScope.currUser.getCc_number()}" required>
                            <label for="cc_number"><c:out value="Credit Card number" /></label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_name" type="text" name="cc_name" class="validate"
                                   value="${sessionScope.currUser.getCc_name()}" required>
                            <label for="cc_name"><c:out value="Name on Credit Card" /></label>
                        </div>
                        <div class="input-field col s12">
                            <input id="cc_expiry" type="date" name="cc_expiry" class="datepicker" value="${sessionScope.currUser.getCc_expiry()}">
                            <label for="cc_expiry"><c:out value="Credit card Expiry Date" /></label>
                        </div>
                       
                </c:otherwise>
            </c:choose>

            <div class="row">
                <div class="col s12 m7">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col s12"><p></p></div>
        <div class="col s12 m4 l2"><p></p></div>
        <div class="col s12 m4 l8">
            <br>
            <h5>Check Out Items</h5>
            <hr>

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
					

						
					</c:if>

					<%-- Remove room button --%>
					
        			
				</c:forEach>
                </ul>
            </c:forEach>
			<div class="row">
				<blockquote> Total $ <c:out value="${finalPrice}"/></blockquote>
			</div>
				<input type="hidden" name="action" value="confirmCheckOut" />
				<button class="btn waves-effect waves-light green" type="submit">Confirm AND Email
					<i class="material-icons right">credit_card</i>
				</button>
			</form>
        </div>
    </div>
	
    <div class="col s12 m4 l2"><p></p></div>


	
</form>

</body>
<script>
$(document).ready(function() {
    var pickr = $('.datepicker').pickadate({
        format: 'dd/mm/yyyy',
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5, // Creates a dropdown of 15 years to control year
        editable: true
    });
    $('select').material_select();
    $('.datepicker').click(function() {
        pickr.pickadate('open');
    })
});
</script>


</html>
