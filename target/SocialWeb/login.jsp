<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>登入</title>
    <style>
        #submit:hover{
            background-color: #ff9800;
        }
    </style>

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
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-header">
        <img src="images/Logo.jpg" alt="logo" class="img-thumbnail" style="width: 100px;height: 50px">
        <a class="navbar-brand" href="index.jsp">TH SocialMedia</a>
    </div>

    <marquee direction="scroll" id="run" style="font-size: 20px">請輸入帳號密碼~</marquee>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <div class="row" style="height: 100px">
        <div class="col-md-12"></div>
    </div>
</div>
<div class="container">
    <label class="col-sm-8" id="headtext">會員登入</label>

    <div class="row">
        <div class="col-sm-12"></div>
    </div>
    <form class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2" for="id"><span class="star">*&nbsp;&nbsp;</span>會員帳號:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="id" name="id" required placeholder="請輸入身份證號"
                       value="<%=request.getParameter("id") ==null ? "":request.getParameter("id")%>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2" for="name"><span class="star">*&nbsp;&nbsp;</span>會員密碼:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" required placeholder="請輸入姓名"
                       value="<%=request.getParameter("name") ==null ? "":request.getParameter("name")%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 col-sm-offset-3">
                <button type="submit" class="form-control" id="submit" class="btn btn-default">確定</button>
            </div>
        </div>

    </form>
    <div class="row" style="width: 100%;height:200px">
        <div class="col-sm-12"></div>
    </div>
</div>
<script type="text/javascript">
    //隱藏text block，顯示password block
    function hideShowPsw1(){
        if (password1.type == "password") {
            password1.type = "text";
        }else {
            password1.type = "password";
        }
    }
    function hideShowPsw2(){
        if (password2.type == "password") {
            password2.type = "text";
        }else {
            password2.type = "password";
        }
    }
</script>
<!-- Footer -->
<footer class="text-center">
    <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP">
        <span class="glyphicon glyphicon-chevron-up"></span>
    </a><br><br>
    <p>Copyright &COPY; TH SocialMedia</p>
</footer>
</body>
</html>
