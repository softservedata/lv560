<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <div class="container">
            <form method="post" action="${contextPath}/registration">
                <h1>Sign Up</h1>
                <div>
                    <c:if test="${not empty message}">${message}</c:if>
                </div>
                <table>
                    <tr>
                        <td><label for="username">Username</label></td>
                        <td><input type="text" id="username" name="username" placeholder="Username" onchange="check_pass()" required></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="password" id="password" name="password" placeholder="Password" required></td>
                    </tr>
                    <tr>
                        <td><label for="confirmPassword">Confirm Password</label></td>
                        <td><input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm password" onchange="check_pass()" required></td>
                    </tr>
                    <tr>
                        <td><button id="submit" type="submit">Sign in</button></td>
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
    </style>

    <script>
        function check_pass() {
            if (document.getElementById('password').value ==
                document.getElementById('confirmPassword').value) {
                document.getElementById('submit').disabled = false;
            } else {
                document.getElementById('submit').disabled = true;
            }
        }
    </script>

</html>
