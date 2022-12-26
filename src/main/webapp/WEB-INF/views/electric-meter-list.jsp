<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>Gas meters List</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>


<h1 align="center">
  ${owner.firstName}'s Gas meters
</h1>
<c:if test="${!empty electricMeterList}">


  <table class="table table-striped">
    <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Reading</th>
      <th>Payment</th>
      <th colspan="3"></th>

    </tr>
    </thead>
    <c:forEach items="${electricMeterList}" var="electricMeter">


      <tbody>
      <tr>
        <td>${electricMeter.id}</td>
        <td> <a href="/history/electric-meter/${electricMeter.id}"> ${electricMeter.name}</a></td>
        <td>${electricMeter.reading} (m³/h)</td>

        <td>  ${String.format("%.2f",electricMeter.payment)} грн
          <c:if test="${electricMeter.payment <0}">
            (Overpayment)

          </c:if>
        </td>


        <td><a href="/${electricMeter.owner.id}/update/electric-meter/${electricMeter.id}">
          <spring:message code="label.give.meter.reading"/></a>
        </td>

        <td><a href="/${owner.id}/payment/electric-meter/${electricMeter.id}">
          <spring:message code="label.gasMeter.pay"/></a>
        </td>


        <td><a href="/delete/${owner.id}/electric-meter/${electricMeter.id}">
          <spring:message code="label.delete"/></a>
        </td>

      </tr>
      </tbody>
    </c:forEach>
  </table>
</c:if>


<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">
  <a href="/create/electric-meter/${owner.id}">Add new Gas Meter</a>
</div>


</body>
</html>

