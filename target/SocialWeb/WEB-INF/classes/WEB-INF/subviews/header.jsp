<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>首頁</title>
    <script>
        function changeColor(){
            var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
            color=color.split("|");
            document.getElementById("run").style.color=color[parseInt(Math.random() * color.length)];
        }
        setInterval("changeColor()",200);
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
            <img src="images/Logo.jpg" alt="logo" class="img-thumbnail" style="width: 100px;height: 50px">
            <a class="navbar-brand" href="index.jsp">TH SocialMedia</a>
        </div>
        <div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="register.jsp">註冊</a></li>
                    <li><a href="login.jsp">登入</a></li>
                    <li><a href="main.jsp">聊天室</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">遊戲<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#section41">小遊戲</a></li>
                        </ul>
                    </li>
                    <li><a href="">登出</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">設定<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#section41">會員資料修改</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="row" style="font-size: 20px">
                <marquee direction="scroll" id="run">歡迎進入聊天室</marquee>
            </div>
        </div>
    </div>
</nav>
