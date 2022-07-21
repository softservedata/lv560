<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 12.07.2022
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">
    <link href="/WEB-INF/views/css/result.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>


<!-- Main Container Start -->
<div class="main-container container py-5 px-4">
    <div class="card bg-dark text-light py-5 px-4 mx-auto">

<%--        <img th:src="@{/images/exam.svg}" alt="image">--%>

        <h3 class="bg-danger text-center mb-5 p-2">Your Score</h3>

        <div class="d-flex justify-content-center">
            <p>Participant name:&nbsp;&nbsp;</p>
            <p>${result.username}</p>
        </div>

        <div class="d-flex justify-content-center">
            <p>Total correct answer:&nbsp;&nbsp;</p>
            <p>${result.total_correct}</p>
        </div>

        <div class="d-flex justify-content-center">
            <p>Total false answer:&nbsp;&nbsp;</p>
            <p>${5-result.total_correct}</p>

        </div>

        <div class="d-flex justify-content-center mt-4">
            <button type="button" onclick="window.location.href='/score'"
                    class="btn btn-outline-warning mx-auto">View Score Board</button>
            <button type="button" onclick="window.location.href='/'"
                    class="btn btn-outline-warning mx-auto">Back to Homepage</button>
        </div>
    </div>
</div>

<!-- Importing Footer -->
<%@ include file="footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
