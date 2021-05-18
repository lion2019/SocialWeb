let webSocket;
let url;

//公頻連線
function initWebSocket1(openroom) {
    if ("WebSocket" in window) {
//            alert("您的瀏覽器支援 WebSocket!");
        if (webSocket == null) {
            url = "ws://192.168.50.62:900/SocialWeb/webSocket/chat/" + openroom;
            // 開啟一個 web socket
            webSocket = new WebSocket(url);
        }else{
            alert("您已進入聊天室...");
        }

        webSocket.onopen = function () {
            alert("已進入聊天室...");
            document.getElementsByClassName("msg_board1")[0].innerHTML = nickname + "加入聊天室<br>";
        };
        webSocket.onmessage = function (evt) {
            let msg_board1 = document.getElementsByClassName("msg_board1")[0];
            let received_msg1 = nickname + ":" +evt.data;
            let old_msg1 = msg_board1.innerHTML;
            msg_board1.innerHTML = old_msg1 + received_msg1 + "<br>";

            // 讓滾動塊往下移動
            msg_board1.scrollTop = msg_board1.scrollTop + 40;
        };
        webSocket.onclose = function () {
            // 關閉 websocket，清空資訊板
            alert("連線已關閉...");
            webSocket = null;
            document.getElementsByClassName("msg_board1")[0].innerHTML = "";
        };
    }
    else {
        // 瀏覽器不支援 WebSocket
        alert("您的瀏覽器不支援 WebSocket!");
    }
}

//房名
function initWebSocket2() {
        let roomName = document.getElementById("input_roomName").value;
        // 房間名不能為空
        if (roomName == null || roomName == "") {
            alert("請輸入房間名");
            return;
        }
    if ("WebSocket" in window) {
//            alert("您的瀏覽器支援 WebSocket!");
        if (webSocket == null) {
            url = "ws://192.168.50.62:900/SocialWeb/webSocket/chat/" + roomName;
            // 開啟一個 web socket
            webSocket = new WebSocket(url);
        }else{
            alert("您已進入聊天室...")
        }

        webSocket.onopen = function () {
            alert("已進入聊天室...");
            document.getElementsByClassName("msg_board2")[0].innerHTML = nickname + "加入聊天室<br>";
        };
        webSocket.onmessage = function (evt) {
            let msg_board2 = document.getElementsByClassName("msg_board2")[0];
            let received_msg2 = nickname + ":" +evt.data;
            let old_msg2 = msg_board2.innerHTML;
            msg_board2.innerHTML = old_msg2 + received_msg2 + "<br>";

            // 讓滾動塊往下移動
            msg_board2.scrollTop = msg_board2.scrollTop + 40;
        };
        webSocket.onclose = function () {
            // 關閉 websocket，清空資訊板
            alert("連線已關閉...");
            webSocket = null;
            document.getElementsByClassName("msg_board1")[0].innerHTML = "";
            document.getElementsByClassName("msg_board2")[0].innerHTML = "";
        };
    }
    else {
        // 瀏覽器不支援 WebSocket
        alert("您的瀏覽器不支援 WebSocket!");
    }
}

//發送訊息
function send_msg(num) {
    if (webSocket != null) {
        let openroom_msg = document.getElementById("openroom_msg").value.trim();
        let nameroom_msg = document.getElementById("nameroom_msg").value.trim();

        if (openroom_msg == "" && nameroom_msg == "") {
            return;
        }else{
            if(num == 1){
                webSocket.send(openroom_msg);
            }else{
                webSocket.send(nameroom_msg);
            }
        }
        // 清除input框裡的資訊
        document.getElementById("openroom_msg").value = "";
        document.getElementById("nameroom_msg").value = "";
    } else {
        alert("您已掉線，請重新進入聊天室...");
    }
};
//關閉連線
function closeWs() {
    webSocket.close();
};

//留言版表格
$(document).ready(function() {
    $('#board').DataTable( {// 和<table>的id對應，指定初始化datatables。
        // 在初始表格的左上有個可選擇的每頁列數的選單設定
        lengthChange: true,   // 呈現選單
        lengthMenu: [5, 10, 25, 50],   // 選單值設定
        pageLength: 25,   // 不用選單設定也可改用固定每頁列數

        searching: true,   // 搜索功能
        ordering: true,   // 開啟排序

        // 下列 2 個一起用，就可以設定列出全部資料、可滑動又固定尺寸的表格
        paging: false,   // 是否建立分頁
        scrollY: 400,   // 固定可以上下滑動的高度

        // [指定的列 , 排序方向] 。
        // 預設 [[0, 'asc']] ，asc 升冪排列、desc 降冪排列。
        order: [[ 1, 'asc' ], [ 2, 'asc' ]],

        // 鎖定行//給table內自定義按鈕
        // columnDefs: [{
        //     // targets用於指定操作的列，從第0列開始，-1為最後一列，這裡第六列
        //     targets: [4],
        //     // return後邊是我們希望在指定列填入的按鈕程式碼
        //     render: function ( data, type, full, meta ) {
        //         return "<input type = 'button' id = 'updata' value = '修改'>" +
        //                "<input type = 'button' id = 'delete' value = '刪除'>"
        //     },
        //     // 禁止排序
        //     orderable: false,
        // }],

        //給列賦值，這裡的列名需要和data的資料名對應
        //注意這裡第六列資料指定為null，用來存放我們加入的按鈕
        // "columns": [
        //     { "data": "編號",
        //       "sClass" : "text-center"
        //     },
        //     { "data": "暱名" },
        //     { "data": "房號" },
        //     { "data": "留言" },
        //     { "data": null},
        //     { "data": "日期" },
        // ],
    } );


    //好友表格
    $('#login_member,#friend').DataTable( {// 和<table>的id對應，指定初始化datatables。
        // 在初始表格的左上有個可選擇的每頁列數的選單設定
        lengthChange: true,   // 呈現選單
        lengthMenu: [5, 10, 25, 50],   // 選單值設定
        pageLength: 25,   // 不用選單設定也可改用固定每頁列數

        searching: true,   // 搜索功能
        ordering: true,   // 開啟排序

        // 下列 2 個一起用，就可以設定列出全部資料、可滑動又固定尺寸的表格
        paging: false,   // 是否建立分頁
        scrollY: 400,   // 固定可以上下滑動的高度

        // [指定的列 , 排序方向] 。
        // 預設 [[0, 'asc']] ，asc 升冪排列、desc 降冪排列。
        order: [[ 1, 'asc' ], [ 2, 'asc' ]],

    } );

    //表頭擠在一起時
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        //当切换tab时，强制重新计算列宽
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    } );
} );
