<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 12.07.2022
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Navbar Start -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" th:fragment="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Quiz App</a>
        <a class="navbar-brand" href="/login">Login</a>
        <a class="navbar-brand" href="/register">Register</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/score">Score Board</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/seequiz">See Quiz List</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Navbar End -->

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>