<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MHGA -Home</title>
</head>
<body>
<%@ include file="navBar.html"%>
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>
        <%-- Room Search section below --%>

        <div class="section">
            <h4 class="header">Search for Rooms </h4>

            <hr>
            <form class="col s12" action="home" method="post">
                <div class="row">
                    <div class="section input-field col s6">
                        <input id="check_in_date" name="check_in_date" type="date"
                               class="datepicker" required>
                        <label for="check_in_date">Check In Date</label>
                    </div>
                    <div class="section input-field col s6">
                        <input id="check_out_date" name="check_out_date" type="date"
                               class="datepicker1" required>
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
        <%-- Special Deals section! --%>
        <div class="row">
            <br>
            <h5>Special Deals</h5>
            <hr>
            <ul class="collection">
            	<c:forEach items="${specialDeals}" var="data">
                <li class="collection-item avatar">
                    <i class="material-icons circle orange">star</i>
                    <span class="title">
                    <c:out value="${data.key.hotel.hotelName}"/> -- <c:out value="${data.key.room.name}"/>
                    </span>
                    <p>
                	  	<p> Perecent Off : <c:out value="${data.value.discountPercent}"/> % </p>                    
                    	<p> Deal Range: <c:out value="${data.value.startDate}"/> -- 
                    	<c:out value="${data.value.endDate}"/> </p>
                    </p>
                </li>
                </c:forEach>
            </ul>
        </div>


        <%-- Featured rooms section below --%>
        <div class="section">
            <h4 class="header">Featured Rooms </h4>
            <hr>
        </div>
        <c:forEach items="${randomRooms}" var="data">
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
                        <span class="card-title"><c:out value="${data.value.hotelName}"/> - <c:out value="${data.key.name}"/> Room</span>
                    </div>
                    <div class="card-content">
                        <p>
                            <c:out value="${data.value.location}"/>
                            <br> $ <c:out value="${data.key.price}"/> Per Night
                            <%@ include file="maps.html"%>
                        </p>
                    </div>
                    <c:if test="${empty sessionScope.currUser.getUser_name()}">
                    <div class="card-action">
                        <a href="login">Sign-up/Sign-In</a>
                    </div>
					</c:if>
					
                </div>
            </div>
        </c:forEach>
        <br>
    </div>
    <div class="col s12 m4 l2"><p></p></div>
</div>
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
    $(document).ready(function() {
        var pickr = $('.datepicker1').pickadate({
            format: 'dd/mm/yyyy',
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 5, // Creates a dropdown of 15 years to control year
            editable: true
        });
        $('select').material_select();
        $('.datepicker1').click(function() {
            pickr.pickadate('open');
        })
    });
</script>
</html>


<%-- Example Control Flow --%>
<%--<form action="home" method="post">--%>
<%--<input type="text" name="searchValue" />--%>
<%--<input type="hidden" name="action" value="toRegister" />--%>
<%--<input type="submit" value="Search" />--%>
<%--</form>--%>