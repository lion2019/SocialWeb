<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!--index.jsp輪播功能需要-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!--引用dataTables.js-->
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

    <title>首頁</title>
    <script>
        let contextPath = "${pageContext.request.contextPath}";
        //跑馬燈
        function changeColor(){
            var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
            color=color.split("|");
            document.getElementById("run").style.color=color[parseInt(Math.random() * color.length)];
        }
        setInterval("changeColor()",200);
        //登入者暱名，聊天室取值用
        let nickname = "${sessionScope.userInfo.nickname}";
        //從後端取得錯誤訊息
        let errMsg = "${requestScope.errorMsg}";
        if(errMsg){
            alert(errMsg);
        }
    </script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="10">

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
<%--            <div class="row">--%>
<%--                <div class="col-sm-4">--%>
<%--                        &lt;%&ndash;img-responsive圖片縮放RWD&ndash;%&gt;--%>
<%--                    <img src="images/Logo.jpg" alt="logo" id="logo" class="img-responsive" >--%>
<%--                </div>--%>
<%--                <div class="col-sm-8">--%>
                    <a class="navbar-brand" href="index.jsp">TH Chatroom</a>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        <div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${sessionScope.userInfo == null}">
                            <li><a class="glyphicon glyphicon-user" href="register.jsp">註冊</a></li>
                            <li><a class="glyphicon glyphicon-log-in" href="login.jsp">登入</a></li>
                        </c:when>

                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/main.jsp">主頁</a></li>
                            <li><a class="glyphicon glyphicon-log-out" href="${pageContext.request.contextPath}/logout.do">登出</a></li>
                            <li><a class="glyphicon glyphicon-user" href="#">${sessionScope.userInfo.nickname}</a></li>
                        </c:otherwise>

                    </c:choose>
                </ul>
            </div>
            <div class="row" style="font-size: 20px">
                <marquee direction="scroll" id="run">
                    ${sessionScope.userInfo != null?sessionScope.userInfo.nickname:''}
                    您好~歡迎進入聊天室</marquee>
            </div>
        </div>
    </div>
</nav>
<div class="row" style="height:70px">
</div>