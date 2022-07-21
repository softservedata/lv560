<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 15.07.2022
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Quiz List</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <c:forEach var="quiz" items="${quizList}">
        <div class="card bg-dark text-light py-5 px-4 mx-auto">
            <p>${quiz.title}</p>
            <p>Enter Your Name</p>
            <form:form action="/getquiz" method="post">
            <input type="text" name="name">
            <p>Enter Your Password</p>
            <input type="text" name="password">
             <p>Start Quiz</p>
            <input type="number" name="idquiz" value="${quiz.idquiz}" />
                <br>
            <input type="submit" value="Let's start">
            </form:form>
        </div>
    </c:forEach>
</body>
</html>
