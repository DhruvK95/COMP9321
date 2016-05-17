<%--
  Created by IntelliJ IDEA.
  User: Dhruv
  Date: 15/05/2016
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*" %>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MHGA - Results</title>
</head>
<body>
<%@ include file="navBar.html" %>
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>

        <%-- Search Pannel --%>
        <div class="row">
            <div class="section">
                <h4 class="header">Search for Rooms </h4>
                <blockquote> Refine your criteria or run a new search. </blockquote>

                <hr>
                <form class="col s12" action="home" method="post">
                    <div class="row">
                        <div class="section input-field col s6">
                            <input id="check_in_date" name="check_in_date" type="date" name="check_in_date"
                                   class="datepicker">
                            <label for="check_in_date">Check In Date</label>
                        </div>
                        <div class="section input-field col s6">
                            <input id="check_out_date" type="date" name="check_out_date" name="check_out_date" class="datepicker">
                            <label for="check_out_date">Check Out Date</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <select name="city" required>
                                <option value="" disabled selected>Choose a City</option>
                                <option value="Sydney">Sydney</option>
                                <option value="Brisbane">Brisbane</option>
                                <option value="Melbourne">Melbourne</option>
                                <option value="Perth">Perth</option>
                                <option value="Adelaide">Adelaide</option>
                                <option value="Hobart">Hobart</option>
                            </select>
                            <label>Select City</label>
                        </div>
                        <div class="input-field col s3">
                            <input id="max_price" type="number" name="max_price" class="validate">
                            <label for="max_price">Max Price</label>
                        </div>
                        <div class="input-field col s3">
                            <input id="number_of_rooms" type="number" name="number_of_rooms" class="validate" required>
                            <label for="number_of_rooms">Number of Rooms</label>
                        </div>
                    </div>

                    <input type="hidden" name="action" value="roomSearch" />
                    <button class="btn waves-effect waves-light" type="submit" name="action">Search
                        <i class="material-icons right">search</i>
                    </button>
                    <br>
                    <br>
                </form>
            </div>

        </div>

        <div class="section row">
            <h4 class="header">Search Results </h4>
            <hr>
        </div>
        <c:choose>
        <c:when test="${searchRooms.size() == 0}">
            <h5 style="color:Red;"> No available rooms match your search criteria <br> Please search with different
                values </h5>
            <hr>
        </c:when>
        <c:otherwise>
        <form action="home" method="POST">
            <div class="row">
                <c:forEach items="${searchRooms}" var="data">
                    <div class="col s6">
                        <div class="card">
                            <div class="card-image">
                                <img src="
                        <c:choose>
                            <c:when test="${data.key.name == 'Single'}">${pageContext.request.contextPath}/static/Single.jpg</c:when>
                            <c:when test="${data.key.name == 'Twin'}">${pageContext.request.contextPath}/static/Twin.jpg</c:when>
                            <c:when test="${data.key.name == 'Queen'}">${pageContext.request.contextPath}/static/Queen.jpg</c:when>
                            <c:when test="${data.key.name == 'Executive'}">${pageContext.request.contextPath}/static/Executive.jpg</c:when>
                            <c:when test="${data.key.name == 'Suite'}">${pageContext.request.contextPath}/static/Suite.jpg</c:when>
                        </c:choose>
                    ">
                                <span class="card-title"><c:out value="${data.value.hotelName}"/> - <c:out
                                        value="${data.key.name}"/> Room</span>
                            </div>
                            <div class="card-content">

                                <p>
                                    <c:out value="${data.value.location}"/>
                                    <br> $ <c:out value="${data.key.price}"/> Per Night
                                </p>
                            </div>

                            <input type="hidden" name="action" value="bookingSubmit"/>
                            <c:choose>
                                <c:when test="${data.key.availableStatus}">

                                    <div class="card-action" style="background-color: #ccffdd">
                                        <p>
                                            <input type="checkbox" name="roomsBookingsOptions" id="${data.key.id}"
                                                   value="${data.key.id}"/>
                                            <label for="${data.key.id}">Book This Room</label>
                                        </p>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="card-action" style="background-color: #ffb3b3">
                                        <p>
                                            <input type="checkbox" id="test8" disabled="disabled"/>
                                            <label for="test8">Cant book this room, Unavailable!!</label>
                                        </p>
                                    </div>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <input type="hidden" name="startDateSQL" value="${startDateSQL}" /> 
		    <input type="hidden" name="endDateSQL" value="${endDateSQL}" /> 
            <button class="btn waves-effect waves-light" type="submit" name="action" value="Yes">Book Selected Rooms
                <i class="material-icons right">today</i>
            </button>
            </c:otherwise>
            </c:choose>
    </div>
    </form>
    <div class="col s12 m4 l2"><p></p></div>
</div>
</body>
<script>
    $('.datepicker').pickadate({
        format: 'dd/mm/yyyy',
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5 // Creates a dropdown of 15 years to control year
    });
    $(document).ready(function() {
        $('select').material_select();
    });
</script>
</html>