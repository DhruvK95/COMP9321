<%--
  User: Dhruv
  Date: 13/05/2016
  Time: 12:52 PM
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Register</title>
</head>

<body>
<%@ include file="navBar.html"%>
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>
        <%-- Registration form to be copied over to another file--%>
        <br>
        <form class="col s12" action="usercontroller" method="post">
            <h4>Registration</h4>
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text" name="regUser">
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="section input-field col s6">
                    <input id="first_name" type="text" name="fName" class="validate">
                    <label for="first_name">First Name</label>
                </div>
                <div class="input-field col s6">
                    <input id="last_name" type="text" name="lName" class="validate">
                    <label for="last_name">Last Name</label>
                </div>
            </div>
            <div class="row">
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="regPass" class="validate">
                    <label for="password">Password</label>
                </div>
            </div>
            <%--<div class="row">--%>
            <div class="input-field col s12">
                <input id="email" type="email" name="email" class="validate">
                <label for="email">Email</label>
            </div>
            <div class="input-field col s12">
                <input id="address" type="text" name="address" class="validate">
                <label for="address">Address</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_number" type="number" name="ccNum" class="validate">
                <label for="cc_number">Credit Card Number</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_name" type="text" name="ccNam" class="validate">
                <label for="cc_name">Name on Credit Card</label>
            </div>
            <div class="input-field col s12">
                <input id="cc_expiry" type="date" name="ccExp" class="datepicker">
                <label for="cc_expiry">Expiry Date</label>
            </div>
            <input type="hidden" name="action" value="toRegister" />
            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                <i class="material-icons right">send</i>
            </button>
        </form>
        <div class="row">
            <div class="col s12 m7">
            </div>
        </div>
    </div>
</div>
<div class="col s12 m4 l2"><p></p></div>

</body>
<script>
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
</script>
</html>

 <%--Example Control flow --%>
<%--<form action="home" method="post">--%>
<%--<input type="text" name="username" />--%>
<%--<input type="text" name="password" />--%>

<%--<input type="hidden" name="action" value="toRegister" />--%>
<%--<input type="submit"/>--%>
<%--</form>--%>
