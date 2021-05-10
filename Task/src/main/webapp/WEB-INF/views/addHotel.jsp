<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add hotel</title>
</head>
<body>
<h2>Add hotel</h2>
<form:form action="${contextPath}/addHotel" modelAttribute="hotel" method="post">
    <label for="hotelName">Hotel name</label>
    <form:input path="hotelName" type="text" id="hotelName" placeholder="Hotel name" required="true"/>

    <p>
        <label for="countryName">Country name</label>
        <select name="countryName" id="countryName">
            <c:forEach var="c" items="${countryList}">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </select>
    </p>
    <p>
        <label for="hotelRating">Hotel rating</label>
        <form:input path="hotelRating" type="number" id="hotelRating" placeholder="Rating" min="0" max="10" required="true"/>
    </p>
    <p>
        <input type="submit" value="Add hotel">
    </p>
</form:form>
</body>
</html>
