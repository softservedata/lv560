<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add room</title>
</head>
<body>
<h2>Add room</h2>
<form:form action="${contextPath}/addRoom" modelAttribute="room" method="post">
    <label for="roomNumber">Room number</label>
    <form:input path="roomNumber" type="number" id="roomNumber" placeholder="Room number" min="1" required="true"/>
    <p>
        <label for="capacity">Capacity</label>
        <form:input path="capacity" type="number" id="capacity" placeholder="Capacity" required="true" min="1"/>
    </p>
    <p>
        <label for="roomPrice">Room price</label>
        <form:input path="roomPrice" type="number" id="roomPrice" placeholder="Room price" required="true" min="1"/>
    </p>
    <p>
        <form:input type="hidden" path="hotel.id"/>
    </p>
    <p>
        <input type="submit" value="Add room">
    </p>
</form:form>
</body>
</html>
