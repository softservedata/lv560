<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="tests" scope="session" type="java.util.List"/>
<html>
<head>
    <title>Тести</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf"%>
    <div id="main-block">
        <%@include file="header.jspf"%>
        <c:if test="${user.role.id == 2}">
            <a class="main-page-link" href="${pageContext.request.contextPath}/addTest">Додати тест</a>
        </c:if>
        <c:forEach var="test" items="${tests}">
            <%@include file="test.jspf"%>
        </c:forEach>
        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>