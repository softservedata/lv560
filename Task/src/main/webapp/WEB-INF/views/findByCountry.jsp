<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Find by country</title>
    </head>
    <body>
        <h2>Find hotels in the country</h2>
        <h4>
            <c:if test="${not empty errorMessage}">
                ${errorMessage}
            </c:if>
        </h4>
        <form action="${contextPath}/findHotelByCountry" method="post">
            <label for="countryLabel">Country name</label>
            <select id="countryLabel" name="name">
                <c:forEach var="item" items="${countryList}">
                    <option value="${item.name}">${item.name}</option>
                </c:forEach>
            </select>
            <br />
            <input type="submit" value="Find hotels"/>
        </form>
        <p><a href="${contextPath}/mainMenu">Back to main menu</a></p>
    </body>
</html>
