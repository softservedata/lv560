<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Quiz</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/WEB-INF/views/css/quiz.css" rel="stylesheet">
</head>
<body>
<br/>
<%@ include file="navbar.jsp" %>
<div>
    <div class="container py-5 px-4">
        <h1 class="bg-danger text-light">Only Select The Correct Answers</h1>
        <form:form action="/submit" method="post"  modelAttribute="qForm">
            <c:set var="multipletypevar" value="multiple"/>
            <c:forEach var="question" items="${qForm.questions}" varStatus="status">
                <c:choose>
                <c:when test="${question.question_type != multipletypevar}">
                <div class="ques-box bg-dark text-light">
                <p>${question.title}</p>
                    <div class="option">
                        <input type="radio" name="questions[${status.index}].chose" value="${question.optionA}"/>
                        <p>${question.optionA}</p>
                    </div>

                    <div class="option">
                        <input type="radio" name="questions[${status.index}].chose" value="${question.optionB}"/>
                        <p>${question.optionB}</p>
                    </div>

                    <div class="option">
                        <input type="radio" name="questions[${status.index}].chose" value="${question.optionC}"/>
                        <p>${question.optionC}</p>
                    </div>
                    <input type="hidden" name="questions[${status.index}].ques_id" value="${question.ques_id}"/>
                    <input type="hidden" name="questions[${status.index}].title" value="${question.title}"/>
                    <input type="hidden" name="questions[${status.index}].ans" value="${question.ans}"/>
                    <input type="hidden" name="questions[${status.index}].question_type" value="${question.question_type}"/>

                </div>
                </c:when>
                    <c:otherwise>
                        <div class="ques-box bg-dark text-light">
                            <p>${question.title}</p>
                            <div class="option">
                                <input type="checkbox" name="questions[${status.index}].chose" value="${question.optionA}"/>
                                <p>${question.optionA}</p>
                            </div>

                            <div class="option">
                                <input type="checkbox" name="questions[${status.index}].chose" value="${question.optionB}"/>
                                <p>${question.optionB}</p>
                            </div>

                            <div class="option">
                                <input type="checkbox" name="questions[${status.index}].chose" value="${question.optionC}"/>
                                <p>${question.optionC}</p>
                            </div>
                            <input type="hidden" name="questions[${status.index}].ques_id" value="${question.ques_id}"/>
                            <input type="hidden" name="questions[${status.index}].title" value="${question.title}"/>
                            <input type="hidden" name="questions[${status.index}].ans" value="${question.ans}"/>
                            <input type="hidden" name="questions[${status.index}].question_type" value="${question.question_type}"/>

                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
                <button type="submit" class="btn btn-danger d-block mx-auto mt-4">Submit Answer</button>
        </form:form>


    </div>
</div>

<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>