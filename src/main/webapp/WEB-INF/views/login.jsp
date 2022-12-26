<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <title>Login form</title>
    <style>
        hr {
            display: block;
            height: 1px;
            border: 0;
            border-top: 1px solid #ccc;
            margin: 1em 0;
            width: 470px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<form:form method="post" action="/login">
    <div style="margin-top: 72px; margin-left: 384px; margin-bottom: 0px;">
        <h1 style="margin-bottom: 48px;">Login Page</h1>
        <div class="form-group">
            <label class="col-sm-2 control-label"
                   style="font-size: 18px; font-weight: normal; width: 124px; padding-left: 0px;">Username:</label>
            <div class="col-sm-8" style="margin-left: 0px; padding-left: 0px; width: 358px;">
                <input class="form-control" type="text" name="username"/>
            </div>
            <br><br>
            <div class="form-group" style="margin-bottom: 0px;">
                <label class="col-sm-2 control-label"
                       style="font-size: 18px; font-weight: normal; width: 124px; padding-left: 0px; margin-left: 0px;">Password:</label>
                <div class="col-sm-8" style="margin-left: 0px; padding-left: 0px; width: 358px;">
                    <input class="form-control" type="password" name="password"/>
                </div>
            </div>
        </div>
        <br><br>
        <div style="margin-left: 406px; margin-bottom: 24px; margin-top: 0px;">
            <input type="submit" class="btn btn-info" value="<spring:message code="label.login"/>"/>
        </div>
        <hr/>
        <div>
            <p style="font-size: 16px; margin-left: 92px;">Don't have an account?
                <a href="/create">Register Now!</a>
            </p>
        </div>
    </div>
</form:form>
</body>
</html>