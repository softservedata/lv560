<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="test" value="${test}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>View Test</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<section id="about-section" class="pt-5 pb-5">
    <div class="container wrapabout">
        <div class="red"></div>
        <div class="row">
            <div class="col-lg-6 align-items-center justify-content-left d-flex mb-5 mb-lg-0">
                <div class="blockabout">
                    <div class="blockabout-inner text-center text-sm-start">
                        <div class="title-big pb-3 mb-3">
                            <h3>${test.testName}</h3>
                        </div>
                        <p class="description-p text-muted pe-0 pe-lg-0">
                            ${test.testTheme}
                            ${test.creator.username}
                        </p>
                        <p class="description-p text-muted pe-0 pe-lg-0">${test.testDescription}</p>
                        <div class="sosmed-horizontal pt-3 pb-3">
                        </div>
                        <a href="#" class="btn rey-btn mt-3">Take test</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-sm-6">
                <div class="card-box bg-red">
                    <div class="inner">
                        <p> Times taken: </p>
                        <h3>${completions}</h3>
                    </div>
                    <div class="icon">
                        <i class="fa fa-users"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
<style>
    #about-section {
        background: rgba(32, 33, 36, 0.1);
        position: relative;
    }

    .blockabout {
        padding: 20px;
        background: white;
    }

    .blockabout-inner {
        padding: 30px;
        border: 1px solid rgba(32, 33, 36, 0.1);
    }

    .sosmed-horizontal a i {
        border: 1px solid #070707;
        border-radius: 50%;
        color: #070707;
        display: inline-block;
        height: 30px;
        width: 30px;
        line-height: 30px;
        margin: auto 3px;
        font-size: 15px;
        text-align: center;
        transition: all 0.3s;
    }

    .rey-btn {
        border: 2px solid #070707;
        padding: 10px 40px;
        text-transform: uppercase;
        letter-spacing: 2px;
        font-size: 13px;
        font-weight: 700;
        border-radius: 50px;
        transition: all 0.3s;
    }
    body{
        background:#eee;
    }

    .card-box {
        position: relative;
        color: #fff;
        padding: 20px 10px 40px;
        margin: 20px 0px;
    }
    .card-box:hover {
        text-decoration: none;
        color: #f1f1f1;
    }
    .card-box:hover .icon i {
        font-size: 100px;
        transition: 1s;
        -webkit-transition: 1s;
    }
    .card-box .inner {
        padding: 5px 10px 0 10px;
    }
    .card-box h3 {
        font-size: 27px;
        font-weight: bold;
        margin: 0 0 8px 0;
        white-space: nowrap;
        padding: 0;
        text-align: left;
    }
    .card-box p {
        font-size: 15px;
    }
    .card-box .icon {
        position: absolute;
        top: auto;
        bottom: 5px;
        right: 5px;
        z-index: 0;
        font-size: 72px;
        color: rgba(0, 0, 0, 0.15);
    }
    .card-box .card-box-footer {
        position: absolute;
        left: 0px;
        bottom: 0px;
        text-align: center;
        padding: 3px 0;
        color: rgba(255, 255, 255, 0.8);
        background: rgba(0, 0, 0, 0.1);
        width: 100%;
        text-decoration: none;
    }
    .card-box:hover .card-box-footer {
        background: rgba(0, 0, 0, 0.3);
    }
    .bg-red {
        background-color: #d9534f !important;
    }

</style>
