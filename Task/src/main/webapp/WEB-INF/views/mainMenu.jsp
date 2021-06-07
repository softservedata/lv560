<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Travel Agency</title>
    </head>
    <body>
        <div class="container">
            <h1>Main Menu</h1><br />

            <div class="container2">
                <a href="${contextPath}/findHotel">Find hotels</a>
                <sec:authorize access="hasAuthority('all_permissions')">
                    <a href="${contextPath}/management">Management page</a><br />
                </sec:authorize>
            </div>

            <form action="${contextPath}/mainMenu" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </body>


    <style>
        .container {
            display: flex;
            justify-content: center;
            flex-direction: column;
            margin-top: 100px;
            text-align: center;
        }

        .container2 {
            padding-bottom: 15px;
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
    </style>
</html>
