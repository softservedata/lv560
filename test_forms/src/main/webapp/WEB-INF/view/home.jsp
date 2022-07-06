<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="results" scope="session" type="java.util.List"/>
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
    <%@include file="header.jspf"%>
    <div id="main-block">
        <a class="main-page-link" href="${pageContext.request.contextPath}/home">Мої результати</a>
        <c:if test="${user.role.id == 2}">
            <a class="main-page-link" href="${pageContext.request.contextPath}/allResults">Усі результати</a>
        </c:if>


        <c:forEach var="result" items="${results}">
            <%@include file="result.jspf"%>

        </c:forEach>
        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>
