<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <title>User management page</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>User management page</h1>
    <div class="container">
        <div class="row">
            <c:forEach var="user" items="${users}">
                    <div class="col-md-6 col-xl-3">
                        <div class="card m-b-30">
                            <div class="card-body row">
                                <div class="col-6">
                                    <a href=""><img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="" class="img-fluid rounded-circle w-60"></a>
                                </div>
                                <div class="col-6 card-title align-self-center mb-0">
                                    <h5>Full name: ${user.fullName}</h5>
                                    <p class="m-0">Username: ${user.username}</p>
                                    <p class="m-0">Role: ${user.role.name()}</p>
                                </div>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><i class="fa fa-envelope float-right"></i>Email : ${user.email}</li>
                            </ul>
                            <div class="card-body">
                                <div class="float-right btn-group btn-group-sm">
                                    <a href="${contextPath}/management/editUser/${user.id}" class="btn btn-primary tooltips" data-placement="top" data-toggle="tooltip" data-original-title="Edit"><i class="fa fa-pencil"></i> </a>
                                    <a href="${contextPath}/management/deleteUser/${user.id}" class="btn btn-secondary tooltips" data-placement="top" data-toggle="tooltip" data-original-title="Delete"><i class="fa fa-times"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
            </c:forEach>
        </div>
    </div>
    <script>
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    </script>
</body>
</html>

<style>
    body{
        margin-top:20px;
        background: #f5f5f5;
    }
    .card {
        border: none;
        -webkit-box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
        box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
        margin-bottom: 30px;
    }
    .w-60 {
        width: 60px;
    }
    h1, h2, h3, h4, h5, h6 {
        margin: 0 0 10px;
        font-weight: 600;
    }
    .social-links li a {
        -webkit-border-radius: 50%;
        background-color: rgba(89,206,181,.85);
        border-radius: 50%;
        color: #fff;
        display: inline-block;
        height: 30px;
        line-height: 30px;
        text-align: center;
        width: 30px;
        font-size: 12px;
    }
    a {
        color: #707070;
    }
</style>
