<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--css樣式--%>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    <%--js函式庫--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!--引用dataTables.js-->
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/jquery.linkify.js"></script>--%>

    <title>首頁</title>
    <script>
        //取得網頁根目錄
        let contextPath = "${pageContext.request.contextPath}";
        //登入者暱名，聊天室取值用
        let nickname = "${sessionScope.userInfo.nickname}";
        //從後端取得錯誤訊息
        let errMsg = "${requestScope.errorMsg}";
        if(errMsg){
            alert(errMsg);
        }
    </script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <%--RWD畫面的漢堡選單按鈕--%>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">TH Chatroom</a>
        </div>
        <div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <%--如果使用者沒登入，註冊及登入按鈕就不隱藏--%>
                        <c:when test="${sessionScope.userInfo == null}">
                            <li><a class="glyphicon glyphicon-user" href="register.jsp">註冊</a></li>
                            <li><a class="glyphicon glyphicon-log-in" href="login.jsp">登入</a></li>
                        </c:when>
                        <%--否則主頁、登出按鈕就不隱藏--%>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/main.jsp">主頁</a></li>
                            <li><a class="glyphicon glyphicon-log-out" href="${pageContext.request.contextPath}/logout.do">登出</a></li>
                            <li><a class="glyphicon glyphicon-user" href="#">${sessionScope.userInfo.nickname}</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <div class="row" style="font-size: 20px">
                <%--跑馬燈--%>
                <marquee direction="scroll" id="run">
                    <%--如果使用者有登入就顯示使用者暱名，沒登入就顯示空字串--%>
                    ${sessionScope.userInfo != null?sessionScope.userInfo.nickname:''}
                    您好~歡迎進入聊天室
                </marquee>
            </div>
        </div>
    </div>
</nav>
<div class="row" style="height:90px">
</div>