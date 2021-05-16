<%@ page import="java.util.Date" %>
<%@ page import="com.social.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>
<%
    User user = (User)session.getAttribute("userInfo");
%>
<style>
    /*input type=number裏上下箭頭隱藏*/
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    input[type="number"]{
        -moz-appearance: textfield;
    }
    /*------------------------------------*/
</style>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <div class="row" style="height: 100px">
        <div class="col-md-12"></div>
    </div>
</div>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">留言版</a></li>
    <li><a data-toggle="tab" href="#menu1" onclick="initWebSocket1(0)">聊天室-公頻</a></li>
    <li><a data-toggle="tab" href="#menu2">聊天室-房間</a></li>
    <li><a data-toggle="tab" href="#menu3">好友</a></li>
</ul>
<div class="tab-content">
    <div id="home" class="tab-pane fade in active">
        <br>
        <div class="container-fluid">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/board.do" method="post">
            <div class="form-group">
                <div class="col-sm-3">
                    <label for="room_number">房號:</label>
                    <input type="number" id="room_number" name="room_number" size="10%">
                </div>
                <div class="col-sm-8">
                    <label for="message">訊息:</label>
                    <input id="message" name="message" size="70%" maxlength="100" required placeholder="請輸入留言訊息">
                    <input type="submit" value="新增留言"/><br>
                </div>
            </div>
        </form>
        </div>
        <div class="container-fluid">
            <table id="board" class="display">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>暱名</th>
                        <th>房號</th>
                        <th>訊息</th>
                        <th>編輯</th>
                        <th>日期</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>c大</td>
                        <td>1</td>
                        <td>111111111111111111111111111111111111111111111111111111111111111111111</td>
                        <td>
                            <%if(user.getNickname().equals("c大")){%>
                            <input type = 'button' id = 'updata' value = '修改'>
                            <input type = 'button' id = 'delete' value = '刪除'>
                            <%}%>
                        </td>
                        <td><%= new Date()%></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>aa</td>
                        <td>2</td>
                        <td>2222222222222222222222222222222222222222222222222222222222222222222222</td>
                        <td>
                            <%if(user.getNickname().equals("aa")){%>
                            <input type = 'button' id = 'updata' value = '修改'>
                            <input type = 'button' id = 'delete' value = '刪除'>
                            <%}%>
                        </td>
                        <td><%= new Date()%></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <br>

    <div id="menu1" class="tab-pane fade">
        <div class="container-fluid">
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-9 offset-3">
                        <div class="msg_board1"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="openroom_msg" name="openroom_msg" maxlength="40">
                    </div>
                    <div class="col-sm-2">
                        <input type="button" value="傳送" onclick="send_msg(1)">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="menu2" class="tab-pane fade">
        <div class="container-fluid">
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-12">
                        <label>房間名:</label>
                        <input id="input_roomName" size="80%" maxlength="40">
                        <input type="button"  value="進入聊天室" onclick="initWebSocket2()" />
                        <input type="button" value="退出聊天室" onclick="closeWs()" /><br>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 offset-3">
                        <div class="msg_board2"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="nameroom_msg" name="nameroom_msg" maxlength="40">
                    </div>
                    <div class="col-sm-2">
                        <input type="button" value="傳送" onclick="send_msg(2)">
                    </div>
                </div>
            </div>
        </div>
    </div>

        <div id="menu3" class="tab-pane fade">
            <table align="center" width="90%">
                <tr>
                    <td width="45%">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="45%" align="center">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/friend.do" method="post">
                            <div class="form-group">
                                <input id="insert_friend" name="insert_friend" size="30%" maxlength="20" required placeholder="請輸入好友暱名">
                                <input type="submit" value="新增好友"/><br>
                            </div>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table id="login_member" class="display">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>上線成員</th>
                                    <th>登入時間</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>aa</td>
                                    <td><%=new Date()%></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>bb</td>
                                    <td><%=new Date()%></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                        <td>&nbsp;</td>
                        <td>
                            <table id="friend" class="display">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>好友</th>
                                        <th>登入時間</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>cc</td>
                                        <td><%=new Date()%></td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>dd</td>
                                        <td><%=new Date()%></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/subviews/footer.jsp" />