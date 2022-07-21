<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserResult</title>
    <style>
        h3,h4{
            text-align: center;
        }
        .card{
            border: 4px double white;
            margin-bottom: 3px/* Параметры границы */
        }
    </style>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h3 class="bg-light ">User's results</h3>
<c:forEach var="result" items="${resultuser}">
    <div class="card bg-dark text-light py-5 px-4 mx-auto">
        <h4 class="bg-dark ">User's name: ${result.username}</h4>
        <h4 class="bg-dark ">User's results: ${result.total_correct}</h4>
    </div>
</c:forEach>
<button type="button" onclick="window.location.href='/'"
        class="btn btn-outline-warning mx-auto">Back to Homepage</button>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
