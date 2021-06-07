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
                <h1>All Rooms</h1>
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

        .subm {
            margin-top: 10px;
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
