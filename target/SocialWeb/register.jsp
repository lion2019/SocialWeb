<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
      <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <title>註冊</title>
      <style>
          body {
              font: 400 20px/1.8 Lato, sans-serif;
              color: #777;
              background-color: #111111;
          }
          h3, h4 {
              margin: 10px 0 30px 0;
              letter-spacing: 10px;
              font-size: 20px;
              color: #111;
          }

          .carousel-inner img {
              /*-webkit-filter: grayscale(90%);*/
              /*filter: grayscale(90%); !* make all photos black and white *!*/
              width: 100%; /* Set width to 100% */
              margin-top: 90px;
          }
          .carousel-caption h3 {
              color: #fff !important;
          }
          @media (max-width: 600px) {
              .carousel-caption {
                  display: none; /* Hide the carousel text when the screen is less than 600 pixels wide */
              }
          }

          .bg-1 h3 {color: #fff;}
          .bg-1 p {font-style: italic;}
          .list-group-item:first-child {
              border-top-right-radius: 0;
              border-top-left-radius: 0;
          }

          .thumbnail p {
              margin-top: 15px;
              color: #555;
          }

          .nav-tabs li a {
              color: #777;
          }
          .navbar {
              font-family: Montserrat, sans-serif;
              margin-bottom: 0;
              background-color: #2d2d30;
              border: 0;
              font-size: 11px !important;
              letter-spacing: 4px;
              /*opacity: 0.9;*/
          }
          .navbar li a, .navbar .navbar-brand {
              color: #d5d5d5 !important;
          }
          .navbar-nav li a:hover {
              color: #fff !important;
          }
          .navbar-nav li.active a {
              color: #fff !important;
              background-color: #29292c !important;
          }
          .navbar-default .navbar-toggle {
              border-color: transparent;
          }

          .dropdown-menu li a {
              color: #000 !important;
          }
          .dropdown-menu li a:hover {
              background-color: red !important;
          }
          footer {
              background-color: #2d2d30;
              color: #f5f5f5;
              padding-bottom: 5px;
          }
          footer a {
              color: #f5f5f5;
          }
          footer a:hover {
              color: #777;
              text-decoration: none;
          }
          textarea {
              resize: none;
          }
          .logo{
              width: 100px;
              height: 50px;
          }
          .star{
              color: red;
          }
          #headtext{
              font-size: 45px;
              text-align: center;
          }
          .form-group label{
              font-size: 25px;
              text-align: right;
          }
          label:hover{
              color: #ff9800;
          }
      </style>
      <script>
          function changeColor(){
              var color="cornflowerblue|chartreuse|aqua|yellow|greenyellow|#ff9800|white";
              color=color.split("|");
              document.getElementById("run").style.color=color[parseInt(Math.random() * color.length)];
          }
          setInterval("changeColor()",200);
      </script>
  </head>
  <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="10">
        <nav class="navbar navbar-default navbar-fixed-top">
             <div class="navbar-header">
                   <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                   </button>
                 <img src="images/Logo.jpg" alt="logo" class="logo">
                 <a class="navbar-brand" href="#myPage">TH SocialMedia</a>
             </div>
             <div class="collapse navbar-collapse" id="myNavbar">
                  <ul class="nav navbar-nav navbar-right">
                      <li><a href="register.jsp">Register</a></li>
                      <li><a href="login.jsp">Login</a></li>
                      <li><a href="register.jsp">LogOut</a></li>
                  </ul>
             </div>
             <marquee direction="scroll" id="run" style="font-size: 20px">喜歡聊天、玩遊戲、任何興趣交流的同好，歡迎入內參觀~</marquee>
        </nav>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
      <div class="row" style="height: 100px">
          <div class="col-md-12"></div>
      </div>

      <a class="btn btn-primary btn-lg" role="button" href="index.jsp" style="margin-left: 15px">返回</a>
</div>
<div class="container">

<%--        <div class="col-sm-10"><h1 class="text-center">會員註冊</h1></div>--%>
    <label class="col-sm-10" id="headtext">會員註冊</label>

    <div class="row">
        <div class="col-sm-12"></div>
    </div>
       <form class="form-horizontal">
           <div class="form-group">
               <label class="col-sm-2" for="id"><span class="star">*&nbsp;&nbsp;</span>會員帳號:</label>
               <div class="col-sm-6"><!--required必填欄位-->
                   <input type="text" class="form-control" id="id" name="id" required placeholder="請輸入身份證號"
                          value="<%=request.getParameter("id") ==null ? "":request.getParameter("id")%>">
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2" for="name"><span class="star">*&nbsp;&nbsp;</span>會員姓名:</label>
               <div class="col-sm-6"><!--required必填欄位-->
                   <input type="text" class="form-control" id="name" name="name" required placeholder="請輸入姓名"
                          value="<%=request.getParameter("name") ==null ? "":request.getParameter("name")%>">
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2" for="password1"><span class="star">*&nbsp;&nbsp;</span>會員密碼:</label>
               <div class="col-sm-6"><!--required必填欄位-->
                   <input type="password" class="form-control" id="password1" name="password1" required placeholder="請輸入密碼">
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2" for="password2"><span class="star">*&nbsp;&nbsp;</span>確認密碼:</label>
               <div class="col-sm-6"><!--required必填欄位-->
                   <input type="password" class="form-control" id="password2" name="password2" required placeholder="請輸入確認密碼">
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2"><span class="star">*&nbsp;&nbsp;</span>會員性別:</label>
<!--required必填欄位-->
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
  </div>
  <!-- Footer -->
  <footer class="text-center">
      <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP">
          <span class="glyphicon glyphicon-chevron-up"></span>
      </a><br><br>
      <p>Copyright &COPY; TH SocialMedia</p>
  </footer>
  </body>
</html>
