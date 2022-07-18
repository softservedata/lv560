<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="tests" scope="session" type="java.util.List"/>
<jsp:useBean id="newTest" scope="session" type="com.hehetenya.test_forms.dto.TestDTO"/>
<html>
<head>
    <title>Додати тест</title>
    <style>
        <%@include file="../reset.css"%>
        <%@include file="../style.css"%>
    </style>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf" %>
    <div id="main-block">
        <form action="${pageContext.request.contextPath}/addQuestion" method="get">
            <div class="input-group">
                <div class="input-group">
                    <p class="test-name-label">Назва тесту:</p>
                    <input type="text" class="test-name-input" maxlength="50" minlength="1" name="testName"
                           value="${newTest.name}">
                </div>
            </div>

            <input type="submit" name="addQuestion" value="Додати запитання" class="add-question-button">
        </form>

        <c:forEach items="${newTest.questions}" var="question">
            <div class="test-container">
                <p class="question-text">${question.text} - Кількість балів: ${question.points}</p>
                <c:forEach var="option" items="${question.options}">
                    <c:if test="${option.correct}">
                        <p><span class="material-icons">done</span> ${option.text}</p>
                    </c:if>
                    <c:if test="${!option.correct}">
                        <p><span class="material-icons">close</span> ${option.text}</p>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
        <div class="submit-test-creation">
            <form action="${pageContext.request.contextPath}/addTest" method="post">
                <input type="submit" value="Створити тест" class="add-question-button">
            </form>
            <form action="${pageContext.request.contextPath}/tests" method="get">
                <input type="submit" value="Скасувати" class="add-question-button">
            </form>
        </div>

        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>
