<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="會員登入"/>
</jsp:include>
<script type="text/javascript">
    $(function (){
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
    });

</script>
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
    <form class="form-horizontal" action="${pageContext.request.contextPath}/login.do" method="post">
        <div class="form-group">
            <label class="col-sm-2" for="email"><span class="star">*&nbsp;&nbsp;</span>會員帳號:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="email" name="email" required placeholder="請輸入email"
                       value="<%=request.getParameter("email") ==null ? "":request.getParameter("email")%>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2" for="password"><span class="star">*&nbsp;&nbsp;</span>會員密碼:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password" required  placeholder="請輸入密碼">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="text-center">
                    <button type="button" class="btn btn-default btn-lg">送出</button>
                </div>
            </div>
        </div>

    </form>
    <div class="row" style="width: 100%;height:200px">
        <div class="col-sm-12"></div>
    </div>
</div>

<jsp:include page="/WEB-INF/subviews/footer.jsp" />

