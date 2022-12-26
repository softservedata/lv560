<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>History</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>

<br>

<h1 align="center">
  History
</h1>
<c:if test="${!empty HistoryList}">
  <table class="table table-striped">
    <thead>
    <tr>
      <th><spring:message code="label.history.id"/></th>
      <th><spring:message code="label.history.last.rating"/></th>
      <th><spring:message code="label.history.new.rating"/></th>
      <th><spring:message code="label.history.consumed"/></th>
      <th><spring:message code="label.history.balance.before.paid"/></th>
      <th><spring:message code="label.history.balance.after.paid"/></th>
      <th><spring:message code="label.history.paid"/></th>
      <th></th>

    </tr>
    </thead>
    <c:forEach items="${HistoryList}" var="history">
      <tbody>
      <tr>
        <td>${history.id}</td>


        <td>
          <c:if test="${history.lastReading >0}">
            ${history.lastReading} (m³/h)
          </c:if>
        </td>


        <td>
          <c:if test="${history.newReading >0}">
            ${history.newReading} (m³/h)
          </c:if>
        </td>


        <td>
          <c:if test="${history.consumed >0}">
            ${history.consumed} (m³/h)
          </c:if>
        </td>


        <td>

            ${String.format("%.2f",history.balanceBeforePaid)} грн
          <c:if test="${history.balanceBeforePaid <0}">
            (Overpayment)

          </c:if>
        </td>
        <td>
            ${String.format("%.2f",history.balanceAfterPaid)} грн
          <c:if test="${history.balanceAfterPaid <0}">
            (Overpayment)

          </c:if>
        </td>
        <td>${String.format("%.2f",history.balanceBeforePaid - history.balanceAfterPaid)} грн</td>


      </tr>
      </tbody>
    </c:forEach>
  </table>
</c:if>


<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">
  <a href="/show/electric-meter/${owner_id}">Back to Gas Meter</a>
</div>


</body>
</html>


