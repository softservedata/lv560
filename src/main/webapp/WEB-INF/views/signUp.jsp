<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>User management page</title>
</head>
<body>
<%@ include file="header.jsp"%>
    <div class="container h-100">
        <div class="row h-100">
            <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
                <div class="d-table-cell align-middle">

                    <div class="text-center mt-4">
                        <h1 class="h2">Get started</h1>
                        <p class="lead">
                            Start creating the best possible user experience for you customers.
                        </p>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <div class="m-sm-4">
                                <form method="post" action="${contextPath}/registration">
                                    <div class="form-group">
                                        <label>Full Name</label>
                                        <input class="form-control form-control-lg" type="text" name="full_name" placeholder="Enter your full name in format User Userov" onchange="check_password()" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input class="form-control form-control-lg" type="text" name="username" placeholder="Enter your username" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input class="form-control form-control-lg" type="email" name="email" placeholder="Enter your email" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input class="form-control form-control-lg" id="password" type="password" name="password" placeholder="Enter your password" required>
                                    </div>

                                    <div class="form-group">
                                        <label>Confirm password</label>
                                        <input class="form-control form-control-lg" id="confirm_password" type="password" name="confirm_password" placeholder="Confirm your password" onchange="check_password()" required>
                                    </div>

                                    <div class="text-center mt-3">
                                        <button id="submit" type="submit" class="btn btn-lg btn-primary">Sign up</button>
                                        <a href="${contextPath}/login">Sign in</a></td>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>

<style>
    body{
        margin-top:20px;
        background-color: #f2f3f8;
    }
    .card {
        margin-bottom: 1.5rem;
        box-shadow: 0 1px 15px 1px rgba(52,40,104,.08);
    }

    .card {
        position: relative;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-direction: column;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid #e5e9f2;
        border-radius: .2rem;
    }
</style>

<script>
    function check_password() {
        if (document.getElementById('password').value == document.getElementById("confirm_password").value){
            document.getElementById('submit').disabled = false;
        }else{
            document.getElementById('submit').disabled = true;
        }
    }
</script>