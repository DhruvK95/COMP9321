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
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>
        <br>
        <form class="col s12" action="home" method="post">
            <h4><c:out value="${sessionScope.currUser.getUser_name()}" />'s Profile</h4>
            <p class="text-right">Update your Profile</p>
            <hr>
            <div class="row">
                <div class="section input-field col s6">
                    <input id="first_name" type="text" name="first_name" class="validate" >
                    <label for="first_name"><c:out value="${sessionScope.currUser.getFirst_name()}" /></label>
                </div>
                <div class="input-field col s6">
                    <input id="last_name" type="text" name="last_name" class="validate" >
                    <label for="last_name"><c:out value="${sessionScope.currUser.getLast_name()}" /></label>
                </div>
            </div>
            <div class="row">
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="password" class="validate" >
                    <label for="password">Password</label>
                </div>
            </div>
            <%--<div class="row">--%>
            <div class="input-field col s12">
                <input id="email" type="email" name="email" class="validate" >
                <label for="email"><c:out value="${sessionScope.currUser.getEmail()}" /></label>
            </div>
            <div class="input-field col s12">
                <input id="address" type="text" name="address" class="validate" >
                <label for="address"><c:out value="${sessionScope.currUser.getAddress()}" /></label>
            </div>
            <div class="input-field col s12">
                <input id="cc_number" type="number" name="cc_number" class="validate" >
                <label for="cc_number"><c:out value="${sessionScope.currUser.getCc_number()}" /></label>
            </div>
            <div class="input-field col s12">
                <input id="cc_name" type="text" name="cc_name" class="validate" >
                <label for="cc_name"><c:out value="${sessionScope.currUser.getCc_name()}" /></label>
            </div>
            <div class="input-field col s12">
                <input id="cc_expiry" type="date" name="cc_expiry" class="datepicker" >
                <label for="cc_expiry"><c:out value="${sessionScope.currUser.getCc_expiry()}" /></label>
            </div>
            <input type="hidden" name="action" value="profileUpdate" />
            <button class="btn waves-effect waves-light" type="submit" name="action">Update Profile
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
        format: 'dd/mm/yyyy', // Change the format of the date picker
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5 // Creates a dropdown of 15 years to control year
    });
</script>
</html>
