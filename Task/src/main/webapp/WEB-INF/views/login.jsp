<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
        <div class="container">
            <form method="post" action="${contextPath}/login">
                <h2 class="form-heading">Sign In</h2>
                <div>
                    <c:if test="${errorMessage}">Wrong user name or password</c:if>
                </div>
                <table>
                    <tr>
                        <td><label for="username">Username</label></td>
                        <td><input type="text" id="username" name="username" class="form-control" placeholder="Username" required></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="password" id="password" name="password" class="form-control" placeholder="Password" required></td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button></td>
                        <td class="end"><a href="/registration">Sign Up</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>

    <style>
        .container {
            display: flex;
            justify-content: center;
            margin-top: 100px;
        }

        body {
            background-color: #000000;
            background-image: linear-gradient(315deg, #000000 0%, #414141 74%);
            color: antiquewhite;
        }

        a {
            display: inline-block;
            margin: 0 5px;
            color: antiquewhite;
            font-size: 1.5em;
        }

        .end {
            text-align: end;
        }
    </style>
</html>


