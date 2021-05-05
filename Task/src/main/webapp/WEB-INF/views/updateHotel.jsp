<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Update hotel</title>
</head>
<body>
<h2>Update hotel</h2>
<form:form action="${contextPath}/updateHotel" modelAttribute="hotel" method="post">
    <p>
        <form:hidden path="id"/>
    </p>
    <label for="hotelName">Hotel name</label>
    <form:input path="hotelName" type="text"  id="hotelName" placeholder="Hotel name"/>
    <p>
        <label for="countryName">Country name</label>
        <form:input path="countryName" type="text"  id="countryName" placeholder="Country name"/>
    </p>
    <p>
        <label for="hotelRating">Hotel rating</label>
        <form:input path="hotelRating" type="number" name="hotelRating" id="hotelRating" placeholder="Hotel rating"/>
    </p>
    <p>
        <input type="submit" value="Update hotel">
    </p>
</form:form>
</body>
</html>
