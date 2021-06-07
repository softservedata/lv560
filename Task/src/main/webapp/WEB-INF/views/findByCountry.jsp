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
            <h2>Find hotels in the country</h2>
            <h4>
                <c:if test="${not empty errorMessage}">
                    ${errorMessage}
                </c:if>
            </h4>

            <form action="${contextPath}/findHotel" method="get" >
                <label for="countryLabel">Country name</label>
                <select id="countryLabel" name="country" onChange="this.form.submit()">
                    <option value="" selected disabled hidden>Choose country</option>
                    <c:forEach var="item" items="${countryList}">
                        <option value="${item.name}">${item.name}</option>
                    </c:forEach>
                </select>
                <br />
            </form>

            <c:if test="${not empty hotels}">
                <table>
                    <thead>
                    <tr>
                        Here you can see all hotels in the ${hotels[0].country.name}
                    </tr>
                    <tr>
                        <th>Hotel name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="hotel" items="${hotels}">
                        <tr>
                            <td>${hotel.hotelName}</td>
                            <td>
                                <form action="${contextPath}/allHotelRooms/${hotel.id}" method="post">
                                    <input type="submit" value="check a specific room" class="subm">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <p><a href="${contextPath}/mainMenu">Back to main menu</a></p>
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
