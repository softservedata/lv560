<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <title>User Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/WEB-INF/views/css/quiz.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<div class="card bg-dark text-light py-5 px-4 mx-auto">
    <p>Name</p>
    <p>${user.name}</p>
    <p>Password</p>
    <p>${user.password}</p>
    <p>Registered</p>
    <p>${user.password}</p>
    <button type="submit" class="btn btn-danger d-block mx-auto mt-4">Edit(but not now)</button>
<%--    <button type="button" onclick="window.location.href='/backtolist'"--%>
<%--            class="btn btn-outline-warning mx-auto">View Score Board</button>--%>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
