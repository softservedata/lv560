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
    <link rel="stylesheet" href="../../../resources/css/flatpickr.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/flatpickr.min.css">
    <link rel='stylesheet' href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="../../../resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.min.css">
    <link rel="stylesheet" href="../../../resources/css/main.min.css">
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
        <div id="wrap"
             class="input-content buy-content">
            <h2>Reserve room in hotel "${hotel.name}"</h2>
            <div class="input-content-wrap buy-content-wrap">
                <dl class="inputbox">
                    <dt class="inputbox-title">Hotel's location:</dt>
                    <dd class="inputbox-content">
                        <span>${locationName}</span>
                    </dd>
                </dl>
                <div class="interactive-container">
                    <div id="data-form_wrapper">
                        <form:form method="POST" action="/user/hotel/${hotel.id}/reserve" id="data-form">
                            <input id="input_room-id" name="roomId" value=" " type="text"/>
                            <input id="input_start-date-id" name="startDate" value=" " type="text"/>
                            <input id="input_end-date-id" name="endDate" value=" " type="text"/>
                            <input id="input_price" name="price" value=" " type="text"/>
                        </form:form>
                    </div>
                    <div class="hotel-container">
                        <div class="rooms-container"
                             style="grid-template-columns: repeat(${numberOfColumns}, ${columnWidth}vw)">
                            <%--                            <div class="room" id="1"></div>--%>
                            <c:forEach var="room" items="${rooms}">
                                <div class="room"
                                     style="height: ${cubeWidth}vw; width: ${cubeWidth}vw; margin: ${(columnWidth-cubeWidth)/4}vw 0 "
                                     data-price="${room.price}" id="${room.id}"></div>

                            </c:forEach>
                        </div>
                        <img src="../../../resources/hotel.png" alt="hotel">
                    </div>
                    <div class="calendar-container">
                        <form>
                            <input id="input_calendar" type="datetime-local" placeholder="Select DateTime">
                        </form>
                        <div class="reservation-data">
                            <h3>Reservation data:</h3>
                            <p id="reservation-data_room-id"></p>
                            <p id="reservation-data_date-range"></p>
                            <p id="reservation-data_price"></p>
                        </div>
                        <div class="buy-btn_wrapper reserve-btn_wrapper">
                            <a class="buy-btn reserve-btn">
                                <input class="text" type="submit" value="   Reserve"/>
                                <span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                        viewBox="0 0 24 24">
																<path
                                                                        d="M 24.015625 5.851562 L 9.0625 20.800781 C 8.933594 20.933594 8.71875 20.933594 8.585938 20.800781 L 0.0976562 12.3125 C -0.03125 12.179688 -0.03125 11.96875 0.0976562 11.835938 L 2.160156 9.773438 C 2.292969 9.640625 2.507812 9.640625 2.636719 9.773438 L 8.824219 15.960938 L 21.476562 3.3125 C 21.609375 3.179688 21.820312 3.179688 21.953125 3.3125 L 24.015625 5.375 C 24.148438 5.503906 24.148438 5.71875 24.015625 5.851562 Z M 24.015625 5.851562 "/>
															</svg></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
</div>

<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
    config = {
        onChange: function (selectedDates, dateStr, instance) {
            dateRange = dateStr.split(" to ")
            $("#input_start-date-id").val(dateRange[0])
            $(".reservation-data").css({
                opacity: '0'
            })
            if (dateRange[1] === undefined) {
                $("#input_end-date-id").val(dateRange[0])
                $("#reservation-data_date-range").text("Date: " + dateRange[0])

            } else {
                $("#input_end-date-id").val(dateRange[1])
                $("#reservation-data_date-range").text("Date range: " + dateRange[0] + " to " + dateRange[1])

            }
            setTimeout(function () {
                $(".reservation-data").css({
                    opacity: '1'
                })
            }, 80);
            priceFunction()
        },
        inline: true,
        mode: "range",
        minDate: "today"
    }
    flatpickr("input[type=datetime-local]", config)
    previous = 0;
    $('.room').click(function () {

        $(previous).removeClass('active-room')
        previous = this
        id = $(this).attr('id')
        $("#input_room-id").val(id)
        $(this).toggleClass('active-room')
        roomPriceByDay = $(this).data("price")

        $(".reservation-data").css({
            opacity: '0'
        })
        $("#reservation-data_room-id").text("Room number: " + id)
        setTimeout(function () {
            $(".reservation-data").css({
                opacity: '1'
            })
        }, 80);
        priceFunction()
    })
    $(".reserve-btn").click(function () {
        document.getElementById('data-form').submit()
    })
    priceFunction = function () {
        if (($("#reservation-data_date-range").text() != "") &&
            ($("#reservation-data_room-id").text() != "")
        ) {
            countOfDays = getNumberOfDays(dateRange[0], dateRange[1])
            $("#reservation-data_price").text("Price: " + "$" + roomPriceByDay * countOfDays)
            $("#input_price").val(roomPriceByDay * countOfDays)
            $(".reserve-btn").css({
                opacity: '1',
                'z-index': '100'
            })
        }
    }

    function getNumberOfDays(start, end) {
        const date1 = new Date(start);
        const date2 = new Date(end);
        const oneDay = 1000 * 60 * 60 * 24;
        const diffInTime = date2.getTime() - date1.getTime();
        const diffInDays = 1 + Math.round(diffInTime / oneDay);
        if (isNaN(diffInDays))
            return 1
        return diffInDays;
    }
</script>
</body>

</html>