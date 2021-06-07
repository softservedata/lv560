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
            <h1>Update hotel</h1>
            <form:form modelAttribute="hotel" action="${contextPath}/updateHotel/${hotel.id}" method="post">
                <p>
                    <label for="hotelName">Hotel name</label>
                    <form:input path="hotelName" type="text"  id="hotelName" placeholder="Hotel name"/>
                </p>
                <p>
                    <label for="hotelRating">Hotel rating</label>
                    <form:input path="hotelRating" type="number" name="hotelRating" id="hotelRating" placeholder="Hotel rating"/>
                </p>
                <p>
                    <input type="submit" value="Update hotel">
                </p>
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