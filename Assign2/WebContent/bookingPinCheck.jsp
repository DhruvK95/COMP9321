<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${requestScope.booking.getId()}
${requestScope.booking.getStartDate()}
${requestScope.booking.getEndDate()}

<c:if test="${not empty errorLOG}">
	YOU PIN WASNT RIGHT
	<form action="home" method="post">
	     <input type="text" name="password"><br>
	     <input type="hidden" name="action" value="checkBooking" />
	     <input type="submit" value="Remove" />
	     
	</form>
</c:if>

<c:if test="${empty errorLOG}">
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

        </div>
    </div>
	
    <div class="col s12 m4 l2"><p></p></div>
</c:if>



</body>

</html>
