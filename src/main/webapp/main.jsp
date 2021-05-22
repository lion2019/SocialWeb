<%@ page import="com.social.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>
<fmt:formatDate type="both" value="${date}" var="nowDate"/>

<script>


</script>
<div class="row" style="height:25px">
</div>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">留言版</a></li>
    <li><a data-toggle="tab" href="#menu1" onclick="initWebSocket1()">聊天室-公頻</a></li>
    <li><a data-toggle="tab" href="#menu2">聊天室-房間</a></li>
    <li><a data-toggle="tab" href="#menu3">好友</a></li>
</ul>
<div class="tab-content">
    <div id="home" class="tab-pane fade in active">
        <br>
        <div class="container-fluid">
            <form class="form1">
                <div class="form-group">
                    <div class="col-sm-3">
                        <label for="room_number">房號:</label>
                        <input type="number" id="room_number" name="room_number" size="10%"
                               oninput="if(value.length>5 || value<=0)value=value.slice(0,5)" placeholder="請輸入數字-限制5碼">
                    </div>
                    <div class="col-sm-8">
                        <label for="message">訊息:</label>
                        <input id="message" name="message" size="70%" maxlength="100" required placeholder="請輸入留言訊息">
                        <input type="button" id="message_btn" onclick="insertBoard()" value="新增留言"/><br>
                    </div>
                </div>
            </form>
        </div>
        <div class="container-fluid">
            <table id="board" class="display" cellspacing="0" width="100%">
                <thead>
<%--                    <tr>--%>
<%--                        <th>#</th>--%>
<%--                        <th>暱名</th>--%>
<%--                        <th>房號</th>--%>
<%--                        <th>訊息</th>--%>
<%--                        <th>編輯</th>--%>
<%--                        <th>日期</th>--%>
<%--                    </tr>--%>
                </thead>
                <tbody>
<%--                    <tr>--%>
<%--                        <td>2</td>--%>
<%--                        <td>會員b</td>--%>
<%--                        <td>2</td>--%>
<%--                        <td>bootstrap RWD技術交流</td>--%>
<%--                        <td>--%>
<%--                            <c:if test="${sessionScope.userInfo.nickname eq '會員b'}">--%>
<%--                                <input type = 'button' id = 'updata' value = '修改'>--%>
<%--                                <input type = 'button' id = 'delete' value = '刪除'>--%>
<%--                            </c:if>--%>
<%--                        </td>--%>
<%--                        <td>${nowDate}</td>--%>
<%--                    </tr>--%>
                </tbody>
            </table>
        </div>
    </div>
    <br>

    <div id="menu1" class="tab-pane fade">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9 offset-3">
                     <div class="msg_board1"></div>
                </div>
            </div><br>
            <div class="row">
                <div class="col-sm-8">
                     <input type="text" class="form-control" id="openroom_msg" name="openroom_msg" maxlength="40">
                </div>
                <div class="col-sm-2">
                     <input type="button" id="openroom_btn" name="openroom_btn" value="傳送" onclick="send_msg(1)">
                </div>
            </div>
        </div>
    </div>

    <div id="menu2" class="tab-pane fade">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-8">
                     <label for="input_roomNo">房號:</label>
                     <input type="number" id="input_roomNo" name="input_roomNo" size="10%"
                           oninput="if(value.length>5 || value<=0)value=value.slice(0,5)" placeholder="請輸入數字-限制5碼">
                     <input type="button"  value="進入房間" onclick="initWebSocket2()" />
                     <input type="button" value="退出房間" onclick="closeWs()" /><br>
                </div>
            </div>
            <div class="row">
                 <div class="col-sm-9 offset-3">
                      <div class="msg_board2"></div>
                 </div>
            </div><br>
            <div class="row">
                <div class="col-sm-8">
                     <input type="text" class="form-control" id="roomNo_msg" name="roomNo_msg" maxlength="40">
                </div>
                <div class="col-sm-2">
                     <input type="button" id="roomNo_btn" name="roomNo_btn" value="傳送" onclick="send_msg(2)">
                </div>
            </div>
        </div>
    </div>

        <div id="menu3" class="tab-pane fade">
            <table align="center" width="90%">
                <tr>
                    <td width="45%">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="45%" align="right">
                        <input id="nickname_to" name="nickname_to" size="30%" maxlength="20" required placeholder="請輸入好友暱名">
                        <input type="button" onclick="insertFriend()" value="新增好友"/><br><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table id="login_member" class="display">
                            <thead>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="i" begin="1" end="${sessionScope.userInfo.nickname.length()}" varStatus="loop">
<%--                                <c:out escapeXml="false" value="<p>${i}</p>" />--%>
                                <tr>
                                    <td></td>
                                    <td>${sessionScope.userInfo.nickname}</td>
                                    <td>${nowDate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                        <td>&nbsp;</td>
                        <td>
                            <table id="friend" class="display">
                                <thead>
<%--                                <tr>--%>
<%--                                    <td>#</td>--%>
<%--                                    <td>好友清單</td>--%>
<%--                                    <td>加入時間</td>--%>
<%--                                </tr>--%>
                                </thead>
                                <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>${nowDate}</td>
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