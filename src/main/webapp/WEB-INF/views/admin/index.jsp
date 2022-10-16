<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Travel agency</title>
    <link href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/reset.css" rel='stylesheet'>
    <link href="${contextPath}/resources/css/main.min.css" rel="stylesheet">
</head>

<body>

<header>
    <div class="header-container">
        <span id="username"> ${principalName}</span>
        <span>| </span><span style="color: red;">ADMIN</span>
    </div>
    <form id="logoutForm" method="post" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <button class="logout-btn" onclick="document.forms['logoutForm'].submit()" type="button">Logout <i
            class="fa fa-sign-out"></i></button>
</header>

<div class="major-block">
    <section class="left-sec">
        <div class="left-sec_block">
            <a class="active" href="">Users</a>
            <a href="/admin/hotels">Hotels</a>
        </div>
    </section>
    <section class="right-sec">
        <div class="entities">
            <c:forEach var="user" items="${users}">
                <div class="entity-item-wrapper">
                    <div class="entity-item">
                        <a class="entity-link" href="/admin/user/${user.id}">${user.username}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</div>

<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>

<script>
    $(".entity-item").mousemove(function (e, btn) {
        const x = e.pageX - this.offsetLeft
        const y = e.pageY - this.offsetTop

        this.style.setProperty('--x', x + 'px')
        this.style.setProperty('--y', y + 'px')
    });
</script>

</body>
</html>