<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <span> ${principalName}</span>
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
            <a class="active" href="/user/hotels">Hotels</a>
        </div>
    </section>
    <section class="right-sec">
        <form:form method="post" action="/admin/hotel/${hotel.id}/reserve" modelAttribute="hotel" id="wrap"
                   class="input-content buy-content">
            <h2>Reserve room in hotel "${hotel.name}"</h2>
            <form:hidden path="id"/>
            <div class="input-content-wrap buy-content-wrap">
                <dl class="inputbox">
                    <dt class="inputbox-title">Hotel's location:</dt>
                    <dd class="inputbox-content">
                        <span >${locationName}</span>
                    </dd>
                </dl>
                <div class="buy-btn_wrapper">
                        <a class="buy-btn">
                            <input class="text" type="submit" value="   Reserve" />
                            <span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                    viewBox="0 0 24 24">
									<path
                                            d="M 24.015625 5.851562 L 9.0625 20.800781 C 8.933594 20.933594 8.71875 20.933594 8.585938 20.800781 L 0.0976562 12.3125 C -0.03125 12.179688 -0.03125 11.96875 0.0976562 11.835938 L 2.160156 9.773438 C 2.292969 9.640625 2.507812 9.640625 2.636719 9.773438 L 8.824219 15.960938 L 21.476562 3.3125 C 21.609375 3.179688 21.820312 3.179688 21.953125 3.3125 L 24.015625 5.375 C 24.148438 5.503906 24.148438 5.71875 24.015625 5.851562 Z M 24.015625 5.851562 " />
								</svg></span>
                        </a>
                </div>
            </div>
        </form:form>

    </section>
</div>
</body>
</html>
