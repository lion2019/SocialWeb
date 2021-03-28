<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="會員註冊"/>
</jsp:include>

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
<jsp:include page="/WEB-INF/subviews/footer.jsp" />

