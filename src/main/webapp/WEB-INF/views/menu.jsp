<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title><spring:message code="label.menu"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>


<h1 align="center">
    Hello ${owner.firstName}, <br>Welcome to your account!!!
</h1>


<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">

    <a type="button"
       class="btn btn-primary btn-lg"
       href="/show/gasometers/${owner.id}" role="button">Go to Electric Meters</a>

</div>



<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">

    <a type="button" class="btn btn-primary btn-lg"
       role="button"
       href="/show/electric-meter/${owner.id}">   Go to Gas Meters   </a>

</div>



</body>
</html>

