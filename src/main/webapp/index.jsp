<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/WEB-INF/views/css/index.css" rel="stylesheet">
    <title>Main Page</title>
</head>
<body>

<%@ include file="WEB-INF/views/navbar.jsp" %>
<div class="main-container container py-7 px-4">
    <div class="image-box">
<%--    <img src="https://i.ytimg.com/vi/1Ne1hqOXKKI/maxresdefault.jpg" alt="image"/>--%>
<%--        <%@ include file="WEB-INF/img/cat.jpg" %>--%>
    </div>
        <div class="text-box">
            <h1 class="mb-5 text-center">Quiz App</h1>
            <form:form action="/quiz" class="card bg-dark py-5 px-4" method="post">
                <p>Enter Your Name</p>
                <input type="text" name="name">
            <%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
                <p>Enter Your Password</p>
                <input type="text" name="password">
            <%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
                <button type="submit" class="btn btn-warning">Start Quiz</button>
            </form:form>
            <form:form class="card bg-dark py-2 px-4" method="get" action="/register">
                <button type="submit" class="btn btn-success">Sign Up!</button>
            </form:form>

            <form:form class="card bg-dark py-2 px-4" method="get" action="/admin/login">
                <button type="submit" class="btn btn-secondary">Admin Button</button>
            </form:form>
        </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Importing Footer -->
<%@ include file="WEB-INF/views/footer.jsp" %>
</body>
</html>
