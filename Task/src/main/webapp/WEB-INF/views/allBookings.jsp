<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>All user and their orders</title>
</head>
<body>
<div>
    <div >
        <h2>All users and their orders </h2>
        <table>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <th>Username</th>
                </tr>
                <tr>
                    <td align="center">${user.name} </td>
                </tr>
                <tr>
                    <th>Orders</th>
                </tr>
                <c:forEach var="book" items="${user.bookings}">
                    <tr>
                        <th>Date of arrival</th>
                        <th>Date of departure</th>
                        <th>Hotel name</th>
                        <th>Room number</th>
                    </tr>
                    <tr>
                        <td align="center">${book.checkIn}</td>
                        <td align="center">${book.checkOut}</td>
                        <td align="center">${book.room.roomNumber}</td>
                        <td align="center">${book.room.hotel.hotelName}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <p><a href="${contextPath}/management">Back to management page</a></p>
</div>
</body>
</html>