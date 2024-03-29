<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>

<div class="row" style="height:25px">
</div>
<div class="container" style="width:100%">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">留言板</a></li>
        <li><a data-toggle="tab" href="#menu1" onclick="initOpenRoom()">聊天室-大廳</a></li>
        <li><a data-toggle="tab" href="#menu2">聊天室-房間</a></li>
        <li><a data-toggle="tab" href="#menu3">好友</a></li>
    </ul>
    <div class="tab-content">
        <%--留言板--%>
        <div id="home" class="tab-pane fade in active">
            <div class="container-fluid">
                <form class="form1">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="room_number">房號:</label>
                            <input type="number" id="room_number" name="room_number" size="10%" required
                               oninput="if(value.length>5 || value<=0)value=value.slice(0,5)" placeholder="請輸入數字-限制5碼">
                        </div>
                        <div class="col-sm-8">
                            <label for="message">訊息:</label>
                            <input id="message" name="message" size="70%" maxlength="100" required placeholder="請輸入留言訊息">
                            <input type="submit" id="message_btn" onclick="insertBoard()" value="新增留言"/><br>
                        </div>
                    </div>
                </form>
            </div>
            <div class="container-fluid">
                <%--留言板表格--%>
                <table id="board" class="display" width="100%">
                    <%--jQuery datatable API 導入--%>
                </table>
            </div>
        </div>
        <br>
        <%--聊天室-大廳--%>
        <div id="menu1" class="tab-pane fade">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-9 offset-3">
                        <div id="open_board" class="msg_board1"></div>
                    </div>
                </div><br>
                <div class="row">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="openRoom_msg" name="openRoom_msg" maxlength="100">
                    </div>
                    <div class="col-sm-2">
                        <input type="button" id="openRoom_btn" name="openRoom_btn" value="傳送" onclick="send_msg(1)">
                    </div>
                </div>
            </div>
        </div>
        <%--聊天室-房間--%>
        <div id="menu2" class="tab-pane fade">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-8">
                        <label for="input_roomNo">房號:</label>
                        <input type="number" id="input_roomNo" name="input_roomNo" size="10%"
                               oninput="if(value.length>5 || value<=0)value=value.slice(0,5)" placeholder="請輸入數字-限制5碼">
                        <input type="button"  value="進入房間" onclick="initRoomNo()" />
                        <input type="button" value="退出房間" onclick="closeWs()" /><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-9 offset-3">
                        <div id="room_board" class="msg_board2"></div>
                    </div>
                </div><br>
                <div class="row">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="roomNo_msg" name="roomNo_msg" maxlength="100">
                    </div>
                    <div class="col-sm-2">
                        <input type="button" id="roomNo_btn" name="roomNo_btn" value="傳送" onclick="send_msg()">
                    </div>
                </div>
            </div>
        </div>
        <%--好友--%>
        <div id="menu3" class="tab-pane fade">
            <table align="center" width="90%">
                <tr>
                    <td width="40%">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="55%" align="right">
                        <input id="nickname_to" name="nickname_to" size="30%" maxlength="20" placeholder="請輸入好友暱名">
                        <input type="button" onclick="insertFriend()" value="新增好友"/><br><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <%--線上成員表格--%>
                        <table id="onlineUser" class="display">
                            <%--jQuery datatable API 導入--%>
                        </table>
                    </td>
                        <td>&nbsp;</td>
                        <td>
                            <%--好友清單表格--%>
                            <table id="friend" class="display">
                                <%--jQuery datatable API 導入--%>
                            </table>
                        </td>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%--套用Dialog--%>
<jsp:include page="DialogDIV.jsp" />
<jsp:include page="/WEB-INF/subviews/footer.jsp" />