<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>All hotels</title>
</head>
<body>
<div>
    <div>
        <h2>All Hotels</h2>
        <table>
            <thead>
            <tr>
                <th>Hotel name</th>
                <th>Country</th>
                <th>Rating of hotel</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hotel" items="${hotels}">
                <tr>
                    <td>${hotel.hotelName}</td>
                    <td>${hotel.countryName}</td>
                    <td align="center">${hotel.hotelRating}</td>
                    <td><a href="/updateHotel/${hotel.id}">Edit</a></td>
                    <td><a href="/deleteHotel/${hotel.id}">Delete</a></td>
                    <td><a href="/addRoom/${hotel.id}">Add room</a></td>
                    <td><a href="/allHotelRooms/${hotel.id}/">View all rooms</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <p><a href="<c:url value="/addHotel"/>">Add hotel</a></p>
    <p><p><a href="${contextPath}/mainMenu">Back to main menu</a></p></p>
</div>
</body>
</html>
