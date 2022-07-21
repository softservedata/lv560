<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 12.07.2022
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Creation</title>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

</head>
<body>
<%@ include file="navbar.jsp" %>

<form class="card bg-dark py-5 px-4" action="/admin/create" method="POST">
    <p class="text-light ">Enter Question</p>
    <input type="text" name="title" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p class="text-light ">Enter Option A</p>
    <input type="text" name="optionA" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p class="text-light ">Enter Option B</p>
    <input type="text" name="optionB" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p class="text-light ">Enter Option C</p>
    <input type="text" name="optionC" />
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>

    <p class="text-light ">Enter what is the answer?</p>
    <select type="number" name="ans">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
<%--    <p class="text-danger warning" th:if="${warning}" th:text="${warning}"></p>--%>
    <button type="submit" class="btn btn-warning">Create</button>
</form>

<%@ include file="footer.jsp" %>
</body>
</html>
