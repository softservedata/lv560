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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;600;700&display=swap">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/flatpickr.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.min.css">
    <style>
        .input-content .inputbox-title {
            line-height: 1;
        }
    </style>
</head>
<body>
<header>
    <div class="header-container">
        <span> ${principalName}</span>
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
            <a href="/admin/users">Users</a>
            <a href="/admin/hotels">Hotels</a>
            <a class="active" href="/admin/reservations">Reservations</a>
        </div>
    </section>
    <section class="right-sec">
        <div id="wrap" class="input-content">
            <h2>Reservation details:</h2>
            <div class="input-content-wrap">
                <dl class="inputbox">
                    <dt class="inputbox-title">Client's username:</dt>
                    <dd class="inputbox-content">
                        <span>${reservation.user.username}</span>
                    </dd>
                </dl>
                <dl class="inputbox">
                    <dt class="inputbox-title">Client's full name:</dt>
                    <dd class="inputbox-content">
                        <span>${reservation.user.firstName} ${reservation.user.lastName}</span>
                    </dd>
                </dl>
                <dl class="inputbox">
                    <dt class="inputbox-title">Hotel, location:</dt>
                    <dd class="inputbox-content">
                        <span>${reservation.room.hotel.name}, ${reservation.room.hotel.location.name}</span>
                    </dd>
                </dl>
                <dl class="inputbox">
                    <dt class="inputbox-title">Room number:</dt>
                    <dd class="inputbox-content">
                        <span>${reservation.room.id}</span>
                    </dd>
                </dl>
                <dl class="inputbox">
                    <dt class="inputbox-title">Price per day, arrival <br>and departure days:</dt>
                    <dd class="inputbox-content">
                        <span>$${reservation.room.price}, from ${reservation.startDate} to ${reservation.endDate}</span>
                    </dd>
                </dl>
                <dl class="inputbox">
                    <dt class="inputbox-title">Final price:</dt>
                    <dd class="inputbox-content">
                        <span>$${reservation.finalPrice}</span>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="interactive-container">
            <div class="interactive-container-blocker"></div>
            <div class="hotel-container">
                <div class="rooms-container"
                     style="grid-template-columns: repeat(${numberOfColumns}, ${columnWidth}vw)">
                    <c:forEach var="room_" items="${rooms}">
                        <a class="room <c:if test="${reservation.room.id==room_.id}">active-room</c:if> <c:if test="${reservation.room.id!=room_.id}">disabled-room</c:if>"
                           style="height: ${cubeWidth}vw; width: ${cubeWidth}vw; margin: ${(columnWidth-cubeWidth)/4}vw 0 "
                           id="${room_.id}"></a>
                    </c:forEach>
                </div>
                <img src="${contextPath}/resources/hotel.png" alt="hotel">
            </div>
            <div class="calendar-container" style="opacity: 1">
                <form>
                    <input id="input_calendar" type="datetime-local" placeholder="Select DateTime">
                </form>
                <div class="delete-btn_wrapper" style="z-index: 101; padding: 30px 0 0; margin: 0 auto">
                    <form:form method="post" action="/admin/reservation/${reservation.id}/cancel">
                        <a class="delete-btn">
                            <input class="text" type="submit" value="   Cancel"/>
                            <span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                    viewBox="0 0 24 24"><path
                                    d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"></path></svg></span>
                        </a>
                    </form:form>
                </div>
            </div>
        </div>

    </section>
</div>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
    config = {
        inline: true,
        mode: "range",
        "locale": {
            "firstDayOfWeek": 1
        },
        defaultDate: ["${reservation.startDate}", "${reservation.endDate}"]
    }
    flatpickr("input[type=datetime-local]", config)
</script>
</body>
</html>
