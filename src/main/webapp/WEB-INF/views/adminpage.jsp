<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Admin Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin.css" rel="stylesheet">
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>

<div class="main-container container py-7 px-4">
    <form:form action="/admin/users" method="post">
        <input type="submit" class="btn btn-warning" value="Users Info">
    </form:form>

    <form:form action="/admin/create_quiz" method="post">
        <input type="submit" class="btn btn-warning" value="Create Quiz">
    </form:form>
    <form:form action="/admin/create_que" method="post">
        <input type="submit" class="btn btn-warning" value="Create Question">
    </form:form>
</div>
<%--<a class="nav-link" href="/create">Create Quiz</a>--%>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
