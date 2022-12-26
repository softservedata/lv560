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
    <title>Create</title>
</head>
<body>
<%@include file="header.jsp" %>
<br>
<br>
<h2 align="center">Create New User </h2><br>


<form action="<c:url value="/add"/>" method="post" class="form">
    <table align="center">
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="firstName" class="form-control"
                           placeholder="<spring:message code="label.firstname"/>"
                           required="required">
                </div>

            </td>
        </tr>
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="lastName" class="form-control"
                           placeholder="<spring:message code="label.lastname"/>"
                           required="required">
                </div>

            </td>
        </tr>
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="email" class="form-control"
                           placeholder="<spring:message code="label.email"/>"
                           required="required">
                </div>

            </td>
        </tr>
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="phoneNumber" class="form-control"
                           placeholder="<spring:message code="label.phonenumber"/> "
                           required="required">
                </div>

            </td>
        </tr>

        <tr>
            <td>
                <div class="mb-3">
                    <input type="password" name="password" class="form-control"
                           placeholder="<spring:message code="label.password"/> "
                           required="required">
                </div>

            </td>
        </tr>
        <td colspan="2">
            <br>
            <input class="btn btn-primary"  type="submit" value='<spring:message code="label.adduser"/>'/>
            <input class="btn btn-outline-secondary" type="reset" value='<spring:message code="label.clear"/>'/>
        </td>
    </table>

</form>
<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">
    <a href="/all">Back to List of Users</a>
</div>
</body>
</html>
