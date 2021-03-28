<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="Home"/>
</jsp:include>

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
<jsp:include page="/WEB-INF/subviews/footer.jsp" />

