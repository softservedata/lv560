<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 13.07.2022
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/WEB-INF/views/css/index.css" rel="stylesheet">
    <style>
        h3{
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<div class="container py-10 px-12">
    <form class="card bg-dark py-5 px-4" action="/register" method="POST">
        <h3 class="bg-dark text-light ">Register</h3>
        <p class="text-light ">Enter Your Name</p>
        <input type="text" name="username" />
<%--        <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

        <p class="text-light ">Enter Your Password</p> te
        <input type="text" name="userpass" />
        <br>
<%--        <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
        <button type="submit" class="btn btn-warning py-2 px-2">Register</button>
    </form>
    </div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
