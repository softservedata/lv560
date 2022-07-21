<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

</head>
<body>
<form class="card bg-dark py-5 px-4" action="/admin/login" method="POST">
    <p>Enter Your Name</p>
    <input type="text" name="name" />
    <%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p>Enter Your Password</p>
    <input type="text" name="password"/>
    <%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
    <br>
    <button type="submit" class="btn btn-warning">Login</button>
</form>
</body>
</html>
