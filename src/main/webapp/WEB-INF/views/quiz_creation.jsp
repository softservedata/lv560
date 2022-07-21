<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz Creation</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/WEB-INF/views/css/index.css" rel="stylesheet">
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <style>h3{text-align: center}
    .option{
        display: flex;
        flex-direction: row;
    }
</style>
</head>
<body>
<%@ include file="navbar.jsp" %>
 <h3 class="bg-dark text-light ">Create Your Quiz</h3>
<form:form action="/submitquiz" method="post" modelAttribute="qForm">
        <c:forEach var="question" items="${qForm.questions}" varStatus="status">
            <div class="option card bg-dark">
                <input type="checkbox" name="questions[${status.index}].chose" value="${question.ques_id}"/>
                <p class="bg-dark text-light" >${question.title}</p>
            </div>
        </c:forEach>
    <p class="bg-light">Enter Quiz Name</p>
    <input type="text" name="title" />
    <button type="submit" class="btn btn-danger d-block mx-auto mt-4">Create Quiz</button>
</form:form>
<%@ include file="footer.jsp" %>
</body>
</html>
