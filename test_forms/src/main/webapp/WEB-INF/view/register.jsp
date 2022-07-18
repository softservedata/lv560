<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Реєстрація</title>
    <style>
        <%@include file="../reset.css"%>
        <%@include file="../style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <div id="main-block">
        <div class="login-container">
            <h2 class="login-title">Реєстрація</h2>
            <form class="login-form" action="${pageContext.request.contextPath}/register" method="post">
                <label>
                    Логін: <br>
                    <input name="login" class="login-input"  required minlength="8" maxlength="20">
                </label> <br>
                <label>
                    Пароль: <br>
                    <input type="password" name="password" class="login-input"  required minlength="8"
                           maxlength="20">
                </label> <br>
                <input type="submit" class="test-details-button" value="Зареєструватися!">
            </form>
            <c:if test="${param.err != null}">
                <p class="login-error">Користувач з таким логіном уже існує.</p>
            </c:if>
            <p class="login-link">Уже зареєстровані? <a href="${pageContext.request.contextPath}/login">Вхід</a> </p>
        </div>
        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>


</body>
</html>