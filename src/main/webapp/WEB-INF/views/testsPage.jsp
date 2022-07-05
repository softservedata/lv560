<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Tests</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="container">
    <div class="text-center mb-5">
        <h3>Tests avaliable to complete</h3>
        <p class="lead">Listing of open tests</p>
    </div>
    <c:forEach var="test" items="${tests}">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="d-flex flex-column flex-lg-row">
                        <div class="row flex-fill">
                            <div class="col-sm-5">
                                <h4 class="h5">${test.testName}</h4>
                                <span class="badge bg-secondary">${test.testDescription}</span>
                            </div>
                            <div class="col-sm-4 py-2">
                                <span class="badge bg-secondary">${test.testTheme}</span>
                            </div>
                            <div class="col-sm-3 text-lg-end">
                                <a href="${contextPath}/tests/${test.id}" class="btn btn-primary stretched-link">View test</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </c:forEach>
</div>
</body>
</html>
<style>
    body{
        background:#eee;
    }

    .card {
        box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
    }

    .card {
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0 solid rgba(0,0,0,.125);
        border-radius: 1rem;
    }

    .card-body {
        -webkit-box-flex: 1;
        -ms-flex: 1 1 auto;
        flex: 1 1 auto;
        padding: 1.5rem 1.5rem;
    }
    .avatar-text {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        background: #000;
        color: #fff;
        font-weight: 700;
    }

    .avatar {
        width: 3rem;
        height: 3rem;
    }
    .rounded-3 {
        border-radius: 0.5rem!important;
    }
    .mb-2 {
        margin-bottom: 0.5rem!important;
    }
    .me-4 {
        margin-right: 1.5rem!important;
    }
</style>
