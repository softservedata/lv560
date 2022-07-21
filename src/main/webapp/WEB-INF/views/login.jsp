<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 12.07.2022
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

<form class="card bg-dark py-5 px-4" action="/login" method="POST">
    <p class="text-light ">Enter Your Name</p>
    <input type="text" name="name" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p class="text-light ">Enter Your Password</p>
    <input type="text" name="password" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
    <button type="submit" class="btn btn-warning">Login</button>
</form>

</body>
</html>
