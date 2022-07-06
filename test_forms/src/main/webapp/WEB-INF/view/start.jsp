<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Головна</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <div id="main-block">
        <p class="welcome">Вітаємо на платформі для проходження тестування.</p>
        <p class="need-to-login">Щоб розпочати, необхідно увійти.</p>
        <a class="welcome-login" href="${pageContext.request.contextPath}/login">Вхід</a>
        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>
