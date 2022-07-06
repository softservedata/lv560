<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <jsp:useBean id="test" scope="session" type="com.hehetenya.test_forms.dto.TestDTO"/>
    <title>${test.name}</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf"%>
    <div id="main-block">
        <div>
            <p class="result-test-name">${test.name}</p>
            <p class="result-description">Тривалість: ${test.durationMinutes} хв <br>
                Кількість запитань: ${test.questionNumber} </p>
            <form method="post" action="${pageContext.request.contextPath}/test">

                <c:forEach var="question" items="${test.questions}">
                    <div class="test-container">
                        <p class="question-text">${question.text} - Кількість балів: ${question.points}</p>
                        <c:forEach var="answer" items="${question.answers}">
                            <input type="checkbox" id="${answer.id}" name="answerId" value="${answer.id}" >
                            <label for="${answer.id}"> ${answer.text} <br></label>
                        </c:forEach>
                    </div>
                </c:forEach>
                <input type="submit" class="test-details-button" value="Підтвердити">
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
