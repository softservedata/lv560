<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Give Meter Reading</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<%@include file="header.jsp" %>
<br>
<br>

<form method="post" action="/${owner_id}/getresult/${gasMeter.id}" commandName="gasMeter">
    <h2 style="margin-left: 620px"><spring:message code="label.give.meter.reading"/></h2>
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
                    <input type="text" name="reading" class="form-control" placeholder="${gasMeter.reading}"
                           required="required">
                </div>

            </td>
            <td>
                <div style="margin-left: 15px">
                <span id="passwordHelpInline" class="form-text">
                       kW*h
                    </span>
                </div>
            </td>

        </tr>

        <tr>
            <td colspan="2">
                <br>
                <div style="margin-left: 60px">
                    <input class="btn btn-primary" type="submit" value='<spring:message code="label.give"/>'/>
                    <input class="btn btn-outline-secondary" type="reset" value='<spring:message code="label.clear"/>'/>
                </div>
                <div class="col-sm-offset-2 col-sm-8" style=" margin-top: 60px;">
                    <a href="/show/gasometers/${owner_id}">Back to Electric Meter</a>
                </div>
            </td>

        </tr>
    </table>
</form>


</body>
</html>
