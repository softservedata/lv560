<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>All hotels room</title>
</head>
<body>
<div>
    <div>
        <h2>All Rooms</h2>
        <table>
            <thead>
            <tr>
                <th>Room number</th>
                <th>Capacity</th>
                <th>Room price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr>
                    <td align="center" >${room.roomNumber}</td>
                    <td align="center">${room.capacity}</td>
                    <td align="center">${room.roomPrice}</td>
                </tr>
                <c:set var="hotelId" value="${room.hotel.id}"/>
            </c:forEach>
            <tr>
                <td align="center" ><a href="/addRoom/${hotelId}">Add room</a></td>
            </tr>
            </tbody>

        </table>
        <p><a href="${contextPath}/mainMenu">Back to main menu</a></p>
    </div>

</div>
</body>
</html>
