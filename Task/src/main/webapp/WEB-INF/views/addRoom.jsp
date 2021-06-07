<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
        <div class="container">
            <h1>Add room</h1>
            <form:form action="${contextPath}/addRoom" modelAttribute="room" method="post">
                <table>
                    <tr>
                        <td><label for="roomNumber">Room number</label></td>
                        <td><form:input path="roomNumber" type="number" id="roomNumber" placeholder="Room number" min="1" required="true"/></td>
                    </tr>
                    <tr>
                        <td><label for="capacity">Capacity</label></td>
                        <td><form:input path="capacity" type="number" id="capacity" placeholder="Capacity" required="true" min="1"/></td>
                    </tr>
                    <tr>
                        <td><label for="roomPrice">Room price</label></td>
                        <td><form:input path="roomPrice" type="number" id="roomPrice" placeholder="Room price" required="true" min="1"/></td>
                    </tr>
                    <tr>
                        <td><form:input type="hidden" path="hotel.id"/></td>
                        <td><input type="submit" value="Add room" class="subm"></td>
                    </tr>
                </table>
            </form:form>
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

        .subm {
            margin-top: 10px;
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
