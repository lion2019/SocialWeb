<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>
<fmt:formatDate type="both" value="${date}" var="nowDate"/>
<style>

</style>
<script>

    function insert(){
        let room_number = $("#room_number").val();
        let message = $("#message").val();
        if(room_number.trim() == null || ((room_number.trim()).length <0 || (room_number.trim()).length >5)){
            alert('房號不得為空及數字長度限制5字，請輸入正確格式！')
        }
        if(message == null || (message.length >100)){
            alert('訊息不得為空及長度限制100字，請輸入正確格式！')
        }
        $.ajax({
            url:'${pageContext.request.contextPath}/board.do',
            type:'POST',
            data:{
                room_number:room_number,
                message:message,
            },
            dataType:'json',
            success:function (response){
                //0為新增成功
                if(response.code == 0){
                    alert('新增成功')
                }else{
                    //300用戶不存在.201新增error
                    alert('新增失敗:' +  response)
                }
            },
            error:function (xhr, textStatus, errorThrown){
                alert('新增失敗:' + xhr.status + ',' + textStatus + errorThrown)
            }
        });
    }
</script>
<div class="row" style="height:35px">
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
                        <input type="button" id="message_btn" onclick="insert()" value="新增留言"/><br>
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
                        <td>會員a</td>
                        <td>1</td>
                        <td>聊聊台灣新冠肺炎疫情怎麼了</td>
                        <td>
                            <c:if test="${sessionScope.userInfo.nickname == '會員a'}">
                                <input type = 'button' id = 'updata' value = '修改'>
                                <input type = 'button' id = 'delete' value = '刪除'>
                            </c:if>
                        </td>
                        <td>${nowDate}</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>會員b</td>
                        <td>2</td>
                        <td>bootstrap RWD技術交流</td>
                        <td>
                            <c:if test="${sessionScope.userInfo.nickname eq '會員b'}">
                                <input type = 'button' id = 'updata' value = '修改'>
                                <input type = 'button' id = 'delete' value = '刪除'>
                            </c:if>
                        </td>
                        <td>${nowDate}</td>
                    </tr>
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
                     <input type="button" value="傳送" onclick="send_msg(1)">
                </div>
            </div>
        </div>
    </div>

    <div id="menu2" class="tab-pane fade">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-8">
                     <label for="input_roomName">房號:</label>
                     <input type="number" id="input_roomName" name="input_roomName" size="10%"
                            oninput="if(value.length>5 || value<=0)value=value.slice(0,5)" placeholder="請輸入數字-限制5碼">
                     <input type="button"  value="進入聊天室" onclick="initWebSocket2()" />
                     <input type="button" value="退出聊天室" onclick="closeWs()" /><br>
                </div>
            </div>
            <div class="row">
                 <div class="col-sm-9 offset-3">
                      <div class="msg_board2"></div>
                 </div>
            </div><br>
            <div class="row">
                <div class="col-sm-8">
                     <input type="text" class="form-control" id="nameroom_msg" name="nameroom_msg" maxlength="40">
                </div>
                <div class="col-sm-2">
                     <input type="button" value="傳送" onclick="send_msg(2)">
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
                        <input id="insert_friend" name="insert_friend" size="30%" maxlength="20" required placeholder="請輸入好友暱名">
                        <input type="submit" value="新增好友"/><br><br>
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
                                    <td>${nowDate}</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>bb</td>
                                    <td>${nowDate}</td>
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
                                        <th>好友清單</th>
                                        <th>登入時間</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>cc</td>
                                        <td>${nowDate}</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>dd</td>
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