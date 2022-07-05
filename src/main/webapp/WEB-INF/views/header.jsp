<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand" href="${contextPath}/mainPage">Main page</a>
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link active" href="${contextPath}/tests">Tests</a>
        </li>
        <sec:authorize access="hasAuthority('user:write')">
            <li class="nav-item">
                <a class="nav-link active" href="${contextPath}/management/">Management</a>
            </li>
        </sec:authorize>
    </ul>
</nav>
</body>
</html>
