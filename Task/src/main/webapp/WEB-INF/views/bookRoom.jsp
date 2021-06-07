<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
        <div class="container">
            <h2>The room is available book now!</h2>
            <h3>Do you really want to book this room from ${arrivalDate.toString()} to ${departureTime.toString()}</h3>
            <form action="${contextPath}/findAvailableRoom/${id}/roomBooking" method="post">
                <input type="hidden" name="checkIn" value="${arrivalDate.toString()}">
                <input type="hidden" name="checkOut" value="${departureTime.toString()}">
                <input type="submit" value="Book room now">
            </form>
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

        body {
            background-color: #000000;
            background-image: linear-gradient(315deg, #000000 0%, #414141 74%);
            color: antiquewhite;
        }
    </style>
</html>