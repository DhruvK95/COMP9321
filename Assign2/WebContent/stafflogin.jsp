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
    <div class="col s12 m8">
        <br>
		<font size="5" color="red">${ error }</font>

        <form class="col s12" action="dashboard" method="post">
            <h4>Staff Login</h4>
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text" name="username" required>
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="password" class="validate" required>
                    <label for="password">Password</label>
                </div>
            </div>

            <input type="hidden" name="action" value="stafflogin" />
            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                <i class="material-icons right">send</i>
            </button>
        </form>
    </div>
</div>
</body>
</html>
