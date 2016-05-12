<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

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
        <form class="col s12">
            <h4>Registration</h4>
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text">
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="section input-field col s6">
                    <input id="first_name" type="text" class="validate">
                    <label for="first_name">First Name</label>
                </div>
                <div class="input-field col s6">
                    <input id="last_name" type="text">
                    <label for="last_name">Last Name</label>
                </div>
            </div>
            <div class="row">
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate">
                    <label for="password">Password</label>
                </div>
            </div>
            <%--<div class="row">--%>
            <div class="input-field col s12">
                <input id="email" type="email" class="validate">
                <label for="email">Email</label>
            </div>
            <div class="input-field col s12">
                <input id="address" type="text" class="validate">
                <label for="address">Address</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_number" type="number" class="validate">
                <label for="cc_number">Credit Card Number</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_name" type="text">
                <label for="cc_name">Name on Credit Card</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_expiry" type="date" class="datepicker">
                <label for="cc_expiry">Expiry Date</label>
            </div>

    </div>
    </form>
</div>
<div class="col s12 m4 l2"><p></p></div>
</div>
</body>
</html>

<%--cc_expiry			VARCHAR(40),--%>
<%--PRIMARY KEY (id)--%>