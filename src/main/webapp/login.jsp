<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="會員登入"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="form-group">
            <label class="col-sm-8" id="headtext">會員登入</label>
        </div>
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
                    <button type="submit" class="btn btn-default btn-lg">送出</button>
                </div>
            </div>
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/subviews/footer.jsp" />

