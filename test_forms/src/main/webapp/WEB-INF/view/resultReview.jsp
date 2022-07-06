<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="result" scope="session" type="com.hehetenya.test_forms.dto.ResultDTO"/>
<html>
<head>
    <title>Огляд результату</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>

<div id="wrapper">
    <%@include file="header.jspf"%>
    <div id="main-block">
        <p class="result-test-name"> ${result.test.name}</p>
        <p class="result-description">Оцінка: ${result.grade} </p>
        <c:forEach var="question" items="${result.test.questions}">
            <div class="test-container">
                <p class="question-text" >${question.text}</p>
                <c:forEach var="answer" items="${question.answers}">
                    <c:if test="${result.answersheet.contains(answer) && answer.correct}">
                        <p > <span class="material-icons">done</span>  ${answer.text}</p>
                    </c:if>
                    <c:if test="${result.answersheet.contains(answer) && !answer.correct}">
                        <p > <span class="material-icons">close</span>  ${answer.text}</p>
                    </c:if>
                    <c:if test="${!result.answersheet.contains(answer)}">
                        <p ><span class="material-icons">check_box_outline_blank</span>  ${answer.text}</p>
                    </c:if>
                </c:forEach>
                <div>
                    <p class="question-text">Правильна відповідь:</p>
                    <div class="question-correct-answer-list">
                        <c:forEach var="answer" items="${question.answers}">
                            <c:if test="${answer.correct}">
                                <p>${answer.text}</p>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
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
