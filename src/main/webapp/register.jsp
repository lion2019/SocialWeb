<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>

<div class="container">
    <div class="row">
        <label class="col-sm-8" id="headtext">會員註冊</label>
    </div>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/register.do" method="post">
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="name"><span class="star">*&nbsp;&nbsp;</span>會員姓名:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" maxlength="20" required placeholder="請輸入姓名"
                       value="<%=request.getParameter("name") ==null ? "":request.getParameter("name")%>">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="password1"><span class="star">*&nbsp;&nbsp;</span>會員密碼:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password1" name="password1" maxlength="20" required placeholder="請輸入密碼">
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="password2"><span class="star">*&nbsp;&nbsp;</span>確認密碼:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password2" name="password2" maxlength="20" required placeholder="請輸入確認密碼">
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
            <label class="col-sm-2" for="email"><span class="star">*&nbsp;&nbsp;</span>電子郵件:</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="email" name="email" required placeholder="請輸入電子郵件"
                    <%=request.getParameter("email") == null? "": request.getParameter("email")%>>
            </div>
        </div>
        <div class="form-group"><!--required必填欄位-->
            <label class="col-sm-2" for="nickname"><span class="star">*&nbsp;&nbsp;</span>會員暱名:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nickname" name="nickname" maxlength="20" required placeholder="請輸入聊天室用暱名"
                       value="<%=request.getParameter("nickname") ==null ? "":request.getParameter("nickname")%>">
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
<div class="row" style="height:80px">
</div>
<jsp:include page="/WEB-INF/subviews/footer.jsp" />

