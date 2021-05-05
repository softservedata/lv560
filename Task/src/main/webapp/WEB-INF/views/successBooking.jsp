<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Success booking</title>
</head>
<body>
<h1>The room was successfully booked</h1>
<p><a href="${contextPath}/mainMenu">Back to main menu</a></p>
</body>
</html>
