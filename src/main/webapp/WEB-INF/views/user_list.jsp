<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 14.07.2022
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="icon" href="https://us.123rf.com/450wm/alexwhite/alexwhite1512/alexwhite151204975/49784187-test-icon.jpg" type="image/icon type">

    <style>
        .custom {
            width: 200px;
        }
        .contt{
            display: block;
            text-align: center;
            justify-content: center;
            align-content: center;
            background: aquamarine;
        }
        .button-container-div {
            text-align: center;
            justify-content: center;
            align-content: center;
        }
        h3{
            text-align: center;
        }
        .bg-light{
            justify-content: center;
        }
    </style>
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/user_list.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="card bg-light py-5 px-4">
<c:forEach var="user" items="${users}" varStatus="status">
    <h3 class="bg-dark text-light">${user.name}</h3>
    <form:form class="card bg-dark" action="/admin/users/getuserinfo" method="POST">
        <div class="button-container-div">
        <input type="hidden" name="user_id" value="${user.user_id}"/>
        <input type="submit" class="btn btn-light custom" value="Details">
    </form:form>
    <form:form class="card bg-dark" action="/admin/users/checkres" method="POST">
        <div class="button-container-div">
            <input type="hidden" name="name" value="${user.name}"/>
            <input type="submit" class="btn btn-secondary custom " value="Check User Results">
        </div>

    </form:form>

    <form:form class="card bg-dark" action="/admin/users/blockUser" method="POST">
        <div class="button-container-div">
            <input type="hidden" name="name" value="${user.name}"/>
            <input type="submit" class="btn btn-primary custom" value="Block User">
        </div>
    </form:form>
</c:forEach>
        </div>
    <%@ include file="footer.jsp" %>
</body>
</html>


