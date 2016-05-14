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
                    </p>
                </div>
                <div class="card-action">
                    <a href="#">This is a link</a>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
    <div class="col s12 m4 l2"><p></p></div>
</div>
</body>
</html>


<%-- Example Control Flow --%>
<%--<form action="home" method="post">--%>
    <%--<input type="text" name="searchValue" />--%>
    <%--<input type="hidden" name="action" value="toRegister" />--%>
    <%--<input type="submit" value="Search" />--%>
<%--</form>--%>