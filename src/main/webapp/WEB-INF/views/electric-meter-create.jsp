<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Create New Gas Meter</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<br>
<br>
<h2 align="center">Create New Gas Meter </h2><br>
<form:form method="post" commandName="electricMeter">


    <table align="center">
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="id" class="form-control" placeholder="Id"
                           required="required">
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="name" class="form-control" placeholder="Name"
                           required="required">
                </div>
            </td>

        </tr>
        <tr>
            <td>
                <div class="mb-3">
                    <input type="text" name="reading" class="form-control" placeholder="Reading"
                           required="required">
                </div>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <br>
                <input class="btn btn-primary" type="submit" value='Add Gas Meter'/>
                <input  class="btn btn-outline-secondary"  type="reset" value='<spring:message code="label.clear"/>'/>
            </td>


        </tr>
    </table>

</form:form>

<div class="col-sm-offset-2 col-sm-8"; align="center" style=" margin-top: 30px;">

    <a href= "/show/electric-meter/${owner_id}">Go to Gas Meter List</a>
</div>


</body>
</html>
