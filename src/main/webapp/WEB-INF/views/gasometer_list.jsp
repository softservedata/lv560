<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Electric Meter List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>


<h1 align="center">
    ${owner.firstName}'s Electric meters
</h1>
<c:if test="${!empty gasMeterList}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="label.gasMeter.id"/></th>
            <th><spring:message code="label.gasMeter.name"/></th>
            <th><spring:message code="label.gasMeter.reading"/></th>
            <th><spring:message code="label.gasMeter.payment"/></th>
            <th colspan="3"></th>

        </tr>
        </thead>
        <c:forEach items="${gasMeterList}" var="gasMeter">
            <tbody>
            <tr>
                <td>${gasMeter.id}</td>
                <td> <a href="/history/gasmeter/${gasMeter.id}"> ${gasMeter.name}</a></td>
                <td>${gasMeter.reading} kW*h</td>

                <td>  ${String.format("%.2f",gasMeter.payment)} грн
                    <c:if test="${gasMeter.payment <0}">
                        (Overpayment)

                    </c:if>
                </td>


                <td><a href="/${gasMeter.owner.id}/update/gasometer/${gasMeter.id}">
                    <spring:message code="label.give.meter.reading"/></a>
                </td>

                <td><a href="/${gasMeter.owner.id}/payment/${gasMeter.id}">
                    <spring:message code="label.gasMeter.pay"/></a>
                </td>


                <td><a href="/delete/${gasMeter.owner.id}/gasometer/${gasMeter.id}">
                    <spring:message code="label.delete"/></a>
                </td>

            </tr>
            </tbody>
        </c:forEach>
    </table>
</c:if>


<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">
    <a href="/create/gasmeter/${owner.id}">Add new Electric Meter</a>
</div>


</body>
</html>

