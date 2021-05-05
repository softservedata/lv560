<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Management page</title>
</head>
<body>
<h2>Management menu</h2>
<p><a href="${contextPath}/hotelList">Hotel editing functions</a></p>
<p><a href="${contextPath}/allBookings">All users and and their bookings</a></p>
<p><a href="${contextPath}/mainMenu">Back to main menu</a></p>
</body>
</html>
