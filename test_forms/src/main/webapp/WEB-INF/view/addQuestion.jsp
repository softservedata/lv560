<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" scope="session" type="com.hehetenya.test_forms.dto.UserDTO"/>
<jsp:useBean id="tests" scope="session" type="java.util.List"/>
<html>
<head>
    <title>Додати запитання</title>
    <style>
        <%@include file="../reset.css"%>
        <%@include file="../style.css"%>
    </style>
    <script type="text/javascript" charset="UTF-8" src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    </script>
    <link rel="stylesheet" href=
            "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<div id="wrapper">
    <%@include file="header.jspf" %>
    <div id="main-block">
        <form action="${pageContext.request.contextPath}/saveQuestion"  method="get">
            <label>
                <p class="option-text-label">Текст запитання:</p>
                <input class="question-area-input"  name="questionText">
            </label> <br>
            <div class="input-group">
                <p class="points-amount-label">Кількість балів:</p>
                <input type="number" min="1" max="600" name="points" class="points-input">
            </div>
            <div class="adding-options-container">
                <p class="points-amount-label">Правильні відповіді:</p>
                <div>
                    <div class="adding-options">
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
                                           class="option-input" name="correct">
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
                        '<input type="text"  class="option-input" name="correct" > </div> </div>';

                    $('#newinput1').append(newRowAdd);
                });

                $("body").on("click", "#DeleteRow1", function () {
                    $(this).parents("#row1").remove();
                })
            </script>

            <div class="adding-options-container">
                <p class="points-amount-label">Хибні відповіді:</p>
                <div>
                    <div class="adding-options">
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
                                           class="option-input" name="incorrect">
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
                        '<input type="text" class="option-input" name="incorrect"> </div> </div>';

                    $('#newinput').append(newRowAdd);
                });

                $("body").on("click", "#DeleteRow", function () {
                    $(this).parents("#row").remove();
                })
            </script>

            <div class="submit-option">
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