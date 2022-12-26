<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>List of Users</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>


<h1 align="center">
    <spring:message code="label.users"/>
</h1>
<br>
<c:if test="${!empty userList}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="label.fullname"/></th>
            <th><spring:message code="label.email"/></th>
            <th><spring:message code="label.phonenumber"/></th>
            <th colspan="2"></th>

        </tr>
        </thead>

        <c:forEach items="${userList}" var="user">
            <tbody>
            <tr>
                <td><a href="/show/gasometers/${user.id}"> ${user.firstName}, ${user.lastName}</a></td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>


                <td><a href="update/${user.id}">
                    <spring:message code="label.update"/></a>
                </td>


                <td><a href="delete/${user.id}">
                    <spring:message code="label.delete"/></a>
                </td>

            </tr>
            </tbody>
        </c:forEach>
    </table>
</c:if>

</body>
</html>

