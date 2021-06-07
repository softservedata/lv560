<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
        <div class="container">
            <h2>All Rooms</h2>
            <c:if test="${not empty errorMessage}">
                <h4><strong>${errorMessage}</strong></h4>
            </c:if>
            <table>
                <thead>
                <tr>
                    <th>Room number</th>
                    <th>Capacity</th>
                    <th>Room price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <td align="center">${room.roomNumber}</td>
                        <td align="center">${room.capacity}</td>
                        <td align="center">${room.roomPrice}</td>
                        <td>
                            <form action="${contextPath}/findAvailableRoom/${room.id}" method="post">
                                <div>
                                    <label for="dateOfArrival">Date of arrival</label>
                                    <input type="date" name="checkIn" id="dateOfArrival" required>
                                    <p>
                                        <label for="dateOfDeparture">Date of departure</label>
                                        <input type="date" name="checkOut" id="dateOfDeparture" required>
                                    </p>
                                    <p>
                                        <input type="hidden" name="hotelId" value="${room.hotel.id}">
                                    </p>
                                    <input type="submit" value="Check the Room">

                                </div>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
    </style>
</html>
