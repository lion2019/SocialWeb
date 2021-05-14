<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>
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
        <div class="form-group">
            <div class="col-sm-12">
                <label for="message">訊息:</label>
                <input id="message" name="message" size="80%" maxlength="100" required placeholder="請輸入訊息">
                <input type="button" value="新增留言" onclick="" /><br>
            </div>
        </div>
        <div class="container-fluid">
        <table id="myTable" class="display">
        <thead>
        <tr>
            <th>編號</th>
            <th>暱名</th>
            <th>留言</th>
            <th>日期</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Row 1 Data 1</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 3</td>
            <td>Row 1 Data 4</td>
        </tr>
        <tr>
            <td>Row 2 Data 1</td>
            <td>Row 2 Data 2</td>
            <td>Row 2 Data 3</td>
            <td>Row 2 Data 4</td>
        </tr>
        </tbody>
        </table>
    </div>
    </div>
    <br><br>
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
        <h3>頁籤 3</h3>
        <p>相對一點帶來樂隊房間那天以前大盤效率感覺到東莞部門家電，種類污染說明事實上放心當時性質同一我，除了於是是指組合搜索追求收入也許都，機構三星緩緩主流幾乎人員優惠少女評估一把字幕，協助設為不需要聯賽網頁那些因而論壇此處老。</p>
    </div>
</div>

<jsp:include page="/WEB-INF/subviews/footer.jsp" />

