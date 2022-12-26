<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Top up your account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<%@include file="header.jsp" %>
<br>
<br>

<form method="post" action="/${owner_id}/payment/electric-meter/${id}" commandName="electricMeter">
    <h2 style="margin-left: 620px">Top up your account</h2>
    <br><br>
    <table align="center">
        <tr>
            <td>
                <h4 style="margin-left: 40px">CURRENT DATA</h4>
            </td>

        </tr>
        <tr>
            <td>

                <div align="center" class="mb-3" style="margin-left: 10px">
                    <input type="text" name="payment" class="form-control" placeholder="${electricMeter.payment}"
                           required="required">
                </div>

            </td>
            <td>
                <div style="margin-left: 15px">
                <span id="passwordHelpInline" class="form-text">
                       грн
                    </span>
                </div>
            </td>

        </tr>


        <tr>
            <td colspan="2">
                <br>
                <div align="center">
                    <input class="btn btn-primary" type="submit" value='<spring:message code="label.gasMeter.pay"/>'/>
                    <input class="btn btn-outline-secondary" type="reset" value='<spring:message code="label.clear"/>'/>
                </div>
            </td>


        </tr>
    </table>


</form>

<div class="col-sm-offset-2 col-sm-8" align="center" style=" margin-top: 30px;">
    <a href="/show/electric-meter/${owner_id}">Back to Gas Meter</a>
</div>


</body>
</html>

