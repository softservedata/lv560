<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<div id="contact" class="contact-area section-padding">
    <div class="container">
        <div class="section-title text-center">
            <h1>Adding a test</h1>
            <p>Page for adding a new test</p>
        </div>
        <div class="row">
            <div class="col-lg-7">
                <div class="contact">
                    <form class="form" method="post" action="${contextPath}/addTest">
                        <div class="row">
                            <div class="form-group col-md-6">
                                <input type="text" name="name" class="form-control" placeholder="Test name" required="required">
                            </div>
                            <div class="form-group col-md-6">
                                <input type="text" name="test_theme" class="form-control" placeholder="Test theme" required="required">
                            </div>
                            <input type="hidden" name="creator_username" id = "creator_username" class="form-control" value="${creator_username}">
                            <div class="form-group col-md-12">
                                <textarea rows="6" name="description" class="form-control" placeholder="Test description"></textarea>
                            </div>
                            <div class="col-md-12 text-center">
                                <button type="submit" value="Submit test" name="submit" id="submitButton" class="btn btn-contact-bg">Submit a new test</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div><!--- END COL -->
        </div><!--- END ROW -->
    </div><!--- END CONTAINER -->
</div>
</body>
</html>
<style>
    body{margin-top:20px;
        background:#eee;
    }

    @media only screen and (max-width:768px) {
        .contact {
            margin-bottom: 60px;
        }
    }

    .contact input {
        background: #fff;
        border: 1px solid #fff;
        border-radius: 3px;
        -webkit-box-shadow: none;
        box-shadow: none;
        color: #232434;
        font-size: 16px;
        height: 60px;
        padding: 10px;
        width: 100%;
        font-family: 'poppins', sans-serif;
        padding-left: 30px;
        -webkit-transition: all 0.3s ease 0s;
        -o-transition: all 0.3s ease 0s;
        transition: all 0.3s ease 0s;
    }

    .contact textarea {
        background: #fff;
        border: 1px solid #fff;
        border-radius: 3px;
        -webkit-box-shadow: none;
        box-shadow: none;
        color: #232434;
        font-size: 16px;
        padding: 10px;
        width: 100%;
        font-family: 'poppins', sans-serif;
        padding-left: 30px;
        -webkit-transition: all 0.3s ease 0s;
        -o-transition: all 0.3s ease 0s;
        transition: all 0.3s ease 0s;
    }

    .contact input:focus {
        background: #fff;
        border: 1px solid #fff;
        color: #232434;
        -webkit-box-shadow: none;
        box-shadow: none;
        outline: 0 none;
    }

    .contact textarea:focus {
        background: #fff;
        border: 1px solid #fff;
        color: #232434;
        -webkit-box-shadow: none;
        box-shadow: none;
        outline: 0 none;
    }

    .form-control::placeholder {
        color: #232434;
        opacity: 1;
    }

    .btn-contact-bg {
        border-radius: 30px;
        color: #fff;
        outline: medium none !important;
        padding: 15px 27px;
        text-transform: capitalize;
        -webkit-transition: all 0.3s ease 0s;
        -o-transition: all 0.3s ease 0s;
        transition: all 0.3s ease 0s;
        background: #7564e5;
        font-family: 'poppins', sans-serif;
        cursor: pointer;
        width: 100%;
    }

    .btn-contact-bg:hover,
    .btn-contact-bg:focus {
        background: #232434;
        color: #fff;
    }

    /*START ADDRESS*/

    .single_address {
        overflow: hidden;
        margin-bottom: 10px;
        padding-left: 40px;
    }

    @media only screen and (max-width:768px) {
        .single_address {
            padding-left: 0px;
        }
    }

    .single_address i {
        background: #f6f6f6;
        color: #7564e5;
        border-radius: 30px;
        width: 60px;
        height: 60px;
        line-height: 60px;
        text-align: center;
        float: left;
        margin-right: 14px;
        font-size: 22px;
        -webkit-box-shadow: 0 5px 30px 0 rgba(0, 0, 0, 0.1);
        box-shadow: 0 5px 30px 0 rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
        -webkit-transition: all 0.3s ease 0s;
        -o-transition: all 0.3s ease 0s;
        transition: all 0.3s ease 0s;
    }

    .single_address:hover i {
        background: #7564e5;
        color: #fff;
    }

    .single_address h4 {
        font-size: 18px;
        margin-bottom: 0px;
        overflow: hidden;
        font-weight: 600;
    }

    .single_address p {
        overflow: hidden;
        margin-top: 5px;
    }

    .section-title h1 {
        font-size: 44px;
        font-weight: 500;
        margin-top: 0;
        position: relative;
        text-transform: capitalize;
        margin-bottom: 15px;
    }
    .section-title p {
        padding: 0 10px;
        width: 70%;
        margin: auto;
        letter-spacing: 1px;
    }
    .section-title {
        margin-bottom: 60px;
    }
    .text-center {
        text-align: center!important;
    }
</style>