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
    <title>註冊</title>

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

    <marquee direction="scroll" id="run" style="font-size: 20px">喜歡聊天、玩遊戲、任何興趣交流的同好，歡迎入內參觀~</marquee>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <div class="row" style="height: 100px">
        <div class="col-md-12"></div>
    </div>
</div>
<div class="container">
    <label class="col-sm-9" id="headtext">會員註冊</label>

    <div class="row">
        <div class="col-sm-12"></div>
    </div>
    <form class="form-horizontal">
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="id"><span class="star">*&nbsp;&nbsp;</span>會員帳號:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="id" name="id" required placeholder="請輸入身份證號"
                       value="<%=request.getParameter("id") ==null ? "":request.getParameter("id")%>">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="name"><span class="star">*&nbsp;&nbsp;</span>會員姓名:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" name="name" required placeholder="請輸入姓名"
                       value="<%=request.getParameter("name") ==null ? "":request.getParameter("name")%>">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="password1"><span class="star">*&nbsp;&nbsp;</span>會員密碼:</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password1" name="password1" required placeholder="請輸入密碼">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="password2"><span class="star">*&nbsp;&nbsp;</span>確認密碼:</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password2" name="password2" required placeholder="請輸入確認密碼">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2"><span class="star">*&nbsp;&nbsp;</span>會員性別:</label>
            <label class="col-sm-1">
                <input type="radio" id="male" name="gender" value="M" required
                    <%=request.getParameter("gender") != null && request.getParameter("gender").equals("M")? "checked":""%>>
                &nbsp;&nbsp;&nbsp;&nbsp;男
            </label>
            <label class="col-sm-1">
                <input type="radio" id="female" name="gender" value="F" required
                    <%=request.getParameter("gender") != null && request.getParameter("gender").equals("F")? "checked":""%>>
                &nbsp;&nbsp;&nbsp;&nbsp;女
            </label>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2"><span class="star">*&nbsp;&nbsp;</span>電子郵件:</label>
            <div class="col-sm-5">
                <input type="email" class="form-control" id="email" name="email" required placeholder="請輸入電子郵件"
                    <%=request.getParameter("email") == null? "": request.getParameter("email")%>>
            </div>
        </div>

    </form>
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
