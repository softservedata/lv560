<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Book room</title>
</head>
<body>
<h2>The room is available book now!</h2>
<h3>Do you really want to book this room from ${arrivalDate.toString()} to ${departureTime.toString()}</h3>
<form action="${contextPath}/findAvailableRoom/${id}/roomBooking" method="post">
    <input type="hidden" name="checkIn" value="${arrivalDate.toString()}">
    <input type="hidden" name="checkOut" value="${departureTime.toString()}">
    <input type="submit" value="book">
</form>

</body>
</html>