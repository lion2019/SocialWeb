<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="主頁"/>
</jsp:include>
    <style type="text/css">
        .msg_board {
            width: 400px;
            height: 350px;
            border: solid 1px darkcyan;
            padding: 5px;
            overflow-y: scroll;
        // 文字長度大於div寬度時換行顯示
        word-break: break-all;
        }
        /*set srcoll start*/
        ::-webkit-scrollbar
        {
            width: 10px;
            height: 10px;
            background-color: #D6F2FD;
        }
        ::-webkit-scrollbar-track
        {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
            /*border-radius: 5px;*/
            background-color: #D6F2FD;
        }
        ::-webkit-scrollbar-thumb
        {
            height: 20px;
            /*border-radius: 10px;*/
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
            background-color: #89D7F7;
        }
        /*set srcoll end*/
        #chatframe{
            margin-top: 140px;
            margin-left: 20px;
            margin-bottom: 50px;
        }
    </style>
<script>
    // $(function (){
    //     $(".dropdown-menu li a").click(function (){
    //         let frameUrl = $(this).attr("id");
    //         $("#frame").attr("src",frameUrl);
    //         //$("#frame").load(frameUrl);
    //     });
    // });
</script>
<div class="container-fluid" id="chatframe">
    <div class="row">
        <div class="row-cols-5" style="width: 35%;height: 100%;border: #bdbdbd 1px solid">
            <label>房間名</label>
            <input id="input_roomName" size="15" maxlength="10">
            <input type="button"  value="進入聊天室" onclick="initWebSocket()" />
            <input type="button" value="退出聊天室" onclick="closeWs()" /><br>
            <div class="msg_board"></div>
            <input id="input_msg" size="40" maxlength="40">
            <input type="button" value="傳送" onclick="send_msg()" />
        </div>
<%--        <iframe id="frame" name="frame" height="500px" width="300px"></iframe>--%>
<%--        <div class="row-cols-5" id="frame" name="frame" style="width: 55%;height: 600px;float: left;border: #bdbdbd 1px solid"></div>--%>
    </div>
</div>
<script type="text/javascript">
    let nickname = "${sessionScope.userInfo.nickname}";
    var webSocket;
    function send_msg() {
        if (webSocket != null) {
            var input_msg = document.getElementById("input_msg").value.trim();
            if (input_msg == "") {
                return;
            }
            webSocket.send(input_msg);
            // 清除input框裡的資訊
            document.getElementById("input_msg").value = "";
        } else {
            alert("您已掉線，請重新進入聊天室...");
        }
    };
    function closeWs() {
        webSocket.close();
    };
    function initWebSocket() {
        var roomName = document.getElementById("input_roomName").value;
        // 房間名不能為空
        if (roomName == null || roomName == "") {
            alert("請輸入房間名");
            return;
        }
        if ("WebSocket" in window) {
//            alert("您的瀏覽器支援 WebSocket!");
            if (webSocket == null) {
                let url = "ws://192.168.50.62:900/SocialWeb/webSocket/chat/" + roomName;
                // 開啟一個 web socket
                webSocket = new WebSocket(url);
            } else {
                alert("您已進入聊天室...");
            }
            webSocket.onopen = function () {
                alert("已進入聊天室，暢聊吧...");
            };
            webSocket.onmessage = function (evt) {
                var msg_board = document.getElementsByClassName("msg_board")[0];
                var received_msg = nickname + ":" +evt.data;
                var old_msg = msg_board.innerHTML;
                msg_board.innerHTML = old_msg + received_msg + "<br>";
                // 讓滾動塊往下移動
                msg_board.scrollTop = msg_board.scrollTop + 40;
            };
            webSocket.onclose = function () {
                // 關閉 websocket，清空資訊板
                alert("連線已關閉...");
                webSocket = null;
                document.getElementsByClassName("msg_board")[0].innerHTML = "";
            };
        }
        else {
            // 瀏覽器不支援 WebSocket
            alert("您的瀏覽器不支援 WebSocket!");
        }
    }


</script>
<jsp:include page="/WEB-INF/subviews/footer.jsp" />

