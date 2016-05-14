<%--
  User: Dhruv
  Date: 13/05/2016
  Time: 12:52 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Register form</h1>
    <%-- Registration form to be copied over to another file--%>
    <br>
    <%-- Example Control Flow --%>
    <form action="home" method="post">
        <input type="text" name="searchValue" />
        <input type="hidden" name="action" value="toRegister" />
        <input type="submit" value="Search" />
    </form>
</body>
</html>
