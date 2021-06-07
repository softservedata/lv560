<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
    <div class="container">
        <div>
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
                    <c:if test="${user.bookings.size() == 0}"><tr>No orders</tr></c:if>
                    <c:if test="${user.bookings.size() != 0}">
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
                                <td align="center">${book.room.hotel.hotelName}</td>
                                <td align="center">${book.room.id}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <p><a href="${contextPath}/management">Back to management page</a></p>
    </div>
    </body>

    <style>
        .container {
            display: flex;
            justify-content: center;
            flex-direction: column;
            margin-top: 100px;
            text-align: center;
        }

        table {
            margin: 0 auto;
        }

        body {
            background-color: #000000;
            background-image: linear-gradient(315deg, #000000 0%, #414141 74%);
            color: antiquewhite;
        }

        a {
            display: inline-block;
            margin: 0 5px;
            color: antiquewhite;
            font-size: 1.2em;
        }
    </style>
</html>
