<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Main Menu</title>
</head>
<body>
<div class="container">
    <form action="${contextPath}/mainMenu" method="post">
        <h2>Main Menu</h2>

        <p><a href="${contextPath}/findHotelByCountry">Find hotels</a></p>

        <sec:authorize access="hasAuthority('all_permissions')">
            <p><a href="${contextPath}/management">Management page</a></p>
        </sec:authorize>
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
