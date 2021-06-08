<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--修改留言板對話框內容--%>
<div id="dialog" title="修改留言板訊息" hidden>
    <div class="container-fluid">
        <form class="form_updata">
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="upRoom_number">房號:</label>
                    <input type="number" id="upRoom_number" name="upRoom_number" required
                           oninput="if(value.length>5 || value<=0)value=value.slice(0,5)">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="upMessage">訊息:</label>
                    <input id="upMessage" name="upMessage" maxlength="100" required>
                </div>
            </div>
        </form>
    </div>
</div>