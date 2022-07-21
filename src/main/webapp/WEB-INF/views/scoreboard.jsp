<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 12.07.2022
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <title>Score Board</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/score.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            width: 100%;
            overflow-x: hidden;
        }

        .main-container {
            min-height: calc(100vh - 136px);
        }

        table {
            width: 100%;
            text-align: center;
        }

        tr {
            border: 1px solid white;
        }

        th, td {
            padding: 10px;
        }

        footer {
            height: 80px;
            text-align: center;
            line-height: 80px;
        }
    </style>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <div class="main-container container py-5 px-4">
        <h3 class="bg-danger text-center mb-5 p-2">Top Scores Of All Time</h3>

        <table class="bg-dark text-light">
            <tr>
                <th>Name</th>
                <th>Score</th>
            </tr>
            <c:forEach var="score" items="${sList}">
            <tr>
                <td>${score.username}</td>
                <td>${score.total_correct}</td>
            </tr>
            </c:forEach>
        </table>

    </div>

<%--    <div class="main-container container py-5 px-4">--%>
<%--        <h3 class="bg-danger text-center mb-5 p-2">Top Scores Of All Time</h3>--%>

<%--        <table class="bg-dark text-light">--%>
<%--            <tr>--%>
<%--                <th>Name</th>--%>
<%--                <th>Score</th>--%>
<%--            </tr>--%>
<%--            <tr th:each="score : ${sList}">--%>
<%--                <td th:text="${score.username}"></td>--%>
<%--                <td th:text="${score.totalCorrect}"></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </div>--%>
    <%@ include file="footer.jsp" %>
</body>
</html>
