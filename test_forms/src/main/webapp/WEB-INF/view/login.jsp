<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
    <title>Вхід</title>
</head>
<body>
<div id="wrapper">
    <div id="main-block">
        <div class="login-container">
            <h2 class="login-title">Вхід </h2>
            <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                <label>
                    Логін: <br>
                    <input type="text" class="login-input" name="login"/>
                </label> <br>
                <label>
                    Пароль: <br>
                    <input type="password" class="login-input" name="password"/>
                </label> <br>
                <input type="submit" class="test-details-button" value="Увійти!"/>
            </form>
            <c:if test="${param.err != null}">
                <p class="login-error">Неправильно введено логін або пароль</p>
            </c:if>
            <p class="login-link">Ще не зареєстровані? <a href="${pageContext.request.contextPath}/register">Реєстрація</a></p>
        </div>
        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>
