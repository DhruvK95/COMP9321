<%--
  Created by IntelliJ IDEA.
  User: Dhruv
  Date: 14/05/2016
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="mvcModel.*, java.util.*"%>

<jsp:useBean id="roomDTO" class="mvcModel.RoomDTO" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="navBar.html"%>
<div class="row">
    <div class="col s12"><p></p></div>
    <div class="col s12 m4 l2"><p></p></div>
    <div class="col s12 m4 l8"><p></p>
        <br>
        <form class="col s12" action="home" method="post">
            <h4>Login</h4>
            <p class="text-right">Not Registered? <a href="register">Sign up now.</a> </p>
            <hr>
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text" name="username">
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="password" class="validate">
                    <label for="password">Password</label>
                </div>
            </div>

            <input type="hidden" name="action" value="login" />
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
</html>
