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
            <h1>Add hotel</h1>
            <form:form action="${contextPath}/addHotel" modelAttribute="hotel" method="post">
                <label for="hotelName">Hotel name</label>
                <form:input path="hotelName" type="text" id="hotelName" placeholder="Hotel name" required="true"/>
                <br />
                <label for="countryName">Country name</label>
                <select name="countryName" id="countryName">
                    <c:forEach var="c" items="${countryList}">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
                <br />
                <label for="hotelRating">Hotel rating</label>
                <form:input path="hotelRating" type="number" id="hotelRating" placeholder="Rating" min="0" max="10" required="true"/>
                <br />
                <input type="submit" value="Add hotel">

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
