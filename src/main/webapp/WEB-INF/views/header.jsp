<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>

<head>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 10px">

            <sec:authorize access="hasAuthority('user')">
                <a class="navbar-brand" href="/menu">Home</a>
<%--                <a class="navbar-brand" href="/create/gasmeter/${owner_d}">New Electric Meter</a>--%>
<%--                <a class="navbar-brand" href="/create/electric-meter/${owner_id}">New Gas Meter</a>--%>

            </sec:authorize>

            <sec:authorize access="hasAuthority('admin')">
                <a class="navbar-brand" href="/menu">Home</a>
                <a class="navbar-brand" href="/create">Add new User</a>
                <a class="navbar-brand" href="/all">Show all Users</a>
            </sec:authorize>


            <div style="position: absolute; right: 10px">
                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand" href="/login">LogIn</a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">

                    <a class="navbar-brand" href="/logout"><spring:message code="label.logout"/></a>
                </sec:authorize>
            </div>

        </div>
    </div>


</nav>

</body>
</html>






