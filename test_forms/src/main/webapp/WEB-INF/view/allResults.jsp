<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="results" scope="session" type="java.util.List"/>
<html>
<head>
    <title>Усі результати</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf"%>
    <div id="main-block">
        <a class="main-page-link" href="${pageContext.request.contextPath}/home">Мої результати</a>
        <a class="main-page-link" href="${pageContext.request.contextPath}/allResults">Усі результати</a>


        <c:forEach var="result" items="${results}">
            <jsp:useBean id="result" type="com.hehetenya.test_forms.dto.ResultDTO"/>
            <div class="test-container" >
                <p class="test-name"> ${result.test.name}</p>
                <p class="all-results-user">Користувач: ${result.user.login}</p>
                <p class="test-description">Оцінка: ${result.grade} </p>
                <form action="${pageContext.request.contextPath}/resultReview" method="post">
                    <input name="resultId" value="${result.id}" style="display: none">
                    <input type="submit" class="test-details-button" value="Деталі...">
                </form>
            </div>

        </c:forEach>

        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>
