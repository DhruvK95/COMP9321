<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MHGA -Home</title>
</head>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo"> Logo</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="sass.html">Register</a></li>
            <li><a href="badges.html">Login</a></li>
        </ul>
    </div>
</nav>
<body>
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>
        <table class="highlight">
            <c:forEach items="${testHotelData}" var="data">
                <tr>
                    <td> <c:out value="${data}" /> </td>
                </tr>
            </c:forEach>
        </table>
        <a class="waves-effect waves-light btn">button</a>
        <hr>
        <%-- Registration form to be copied over to another file--%>
        <br>

        <table class="highlight">
            <c:forEach items="${randomRooms}" var="data">
                <tr>
                    <th><c:out value="${data.value.hotelName}"/></th>
                    <td> <c:out value="${data.key.name}"/> </td>
                    <td> <c:out value="${data.value.location}"/> </td>
                    <td> <c:out value="${data.key.price}"/> </td>
                </tr>
            </c:forEach>
        </table>


        <form action="home" method="post">
            <input type="text" name="searchValue" />
            <input type="hidden" name="action" value="toRegister" />
            <input type="submit" value="Search" />
        </form>

    </div>
    <div class="col s12 m4 l2"><p></p></div>
    <%--<img src="${pageContext.request.contextPath}/static/Single.jpg">--%>
</div>
</body>
</html>

<%--cc_expiry			VARCHAR(40),--%>
<%--PRIMARY KEY (id)--%>