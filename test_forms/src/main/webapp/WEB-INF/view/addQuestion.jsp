<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="tests" scope="session" type="java.util.List"/>
<html>
<head>

    <meta charset="UTF-8">
    <title>Додати запитання</title>
    <style>
        <%@include file="reset.css"%>
        <%@include file="style.css"%>
    </style>

    <script type="text/javascript" charset="UTF-8" src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    </script>

    <%--<link rel="stylesheet" href=
            "//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href=
            "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">


</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf" %>
    <div id="main-block">
        <form action="${pageContext.request.contextPath}/addQuestion" accept-charset="UTF-8" method="post">
            <label>
                <p class="answer-text-label">Текст запитання:</p>
                <textarea class="question-area-input" rows="5" cols="70" name="questionText"></textarea>
            </label> <br>
            <div class="input-group">
                <p class="points-amount-label">Кількість балів:</p>
                <input type="number" min="1" max="600" name="points" class="points-input">
            </div>
            <div class="adding-answers-container">
                <p class="points-amount-label">Правильні відповіді:</p>
                <div>
                    <div class="adding-answers">
                        <div class="col-lg-12">
                            <div id="row1">
                                <div class="input-group m-3">
                                    <div class="input-group-prepend">
                                        <button class="test-details-button"
                                                id="DeleteRow1" type="button">
                                            <i class="bi bi-trash"></i>
                                            Видалити
                                        </button>
                                    </div>
                                    <input type="text"
                                           class="answer-input" name="correct">
                                </div>
                            </div>
                            <div id="newinput1"></div>
                            <button id="rowAdder1" type="button"
                                    class="test-details-button">
                                Додати правильну відповідь
                            </button>
                        </div>
                    </div>
                </div>
            </div>


            <script type="text/javascript" charset="UTF-8">
                $("#rowAdder1").click(function () {
                    newRowAdd =
                        '<div id="row1"> <div class="input-group m-3">' +
                        '<div class="input-group-prepend">' +
                        '<button class="test-details-button" id="DeleteRow1" type="button">' +
                        '<i class="bi bi-trash"></i> Видалити</button> </div>' +
                        '<input type="text"  class="answer-input" name="correct" > </div> </div>';

                    $('#newinput1').append(newRowAdd);
                });

                $("body").on("click", "#DeleteRow1", function () {
                    $(this).parents("#row1").remove();
                })
            </script>

            <div class="adding-answers-container">
                <p class="points-amount-label">Хибні відповіді:</p>
                <div>
                    <div class="adding-answers">
                        <div class="col-lg-12">
                            <div id="row">
                                <div class="input-group m-3">
                                    <div class="input-group-prepend">
                                        <button class="test-details-button"
                                                id="DeleteRow" type="button">
                                            <i class="bi bi-trash"></i>
                                            Видалити
                                        </button>
                                    </div>
                                    <input type="text"
                                           class="answer-input" name="incorrect">
                                </div>
                            </div>

                            <div id="newinput"></div>
                            <button id="rowAdder" type="button"
                                    class="test-details-button">
                                Додати хибну відповідь
                            </button>
                        </div>
                    </div>
                </div>
            </div>


            <script type="text/javascript" charset="UTF-8">
                $("#rowAdder").click(function () {
                    newRowAdd =
                        '<div id="row"> <div class="input-group m-3">' +
                        '<div class="input-group-prepend">' +
                        '<button class="test-details-button" id="DeleteRow" type="button">' +
                        '<i class="bi bi-trash"></i> Видалити</button> </div>' +
                        '<input type="text" class="answer-input" name="incorrect"> </div> </div>';

                    $('#newinput').append(newRowAdd);
                });

                $("body").on("click", "#DeleteRow", function () {
                    $(this).parents("#row").remove();
                })
            </script>
            <div class="submit-answer">
                <input type="submit" value="Створити запитання" class="main-page-link">
            </div>
        </form>

        <div class="clear push50"></div>
    </div>
    <footer>
        <p>Copyright</p>
    </footer>
</div>
</body>
</html>