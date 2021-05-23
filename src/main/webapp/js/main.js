let webSocket;
let webSocket2;
let url;
//contextPath、nickname宣告在header.jsp
//公頻連線
function initWebSocket1() {
    //不讓前端知道公頻號碼
    let openroom = "null";
    if ("WebSocket" in window) {
//            alert("您的瀏覽器支援 WebSocket!");
        if (webSocket == null) {
            url = "ws://192.168.50.62:900/SocialWeb/webSocket/chat/" + openroom;
            // 開啟一個 web socket
            webSocket = new WebSocket(url);
        }
        // else{
        //     alert("公共頻道已連線...");
        // }

        webSocket.onopen = function () {
            alert("加入公共頻道...");
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
        let roomNo = document.getElementById("input_roomNo").value;
        // 房間名不能為空
        if (roomNo == null || roomNo == "") {
            alert("請輸入房間名");
            return;
        }
    if ("WebSocket" in window) {
//            alert("您的瀏覽器支援 WebSocket!");
        if (webSocket2 == null) {
            url = "ws://192.168.50.62:900/SocialWeb/webSocket/chat/" + roomNo;
            // 開啟一個 web socket
            webSocket2 = new WebSocket(url);
        }
        // else{
        //     alert("房間頻道已連線...")
        // }

        webSocket2.onopen = function () {
            alert("加入房間頻道...");
            document.getElementsByClassName("msg_board2")[0].innerHTML = nickname + "加入聊天室<br>";
        };
        webSocket2.onmessage = function (evt) {
            let msg_board2 = document.getElementsByClassName("msg_board2")[0];
            let received_msg2 = nickname + ":" +evt.data;
            let old_msg2 = msg_board2.innerHTML;
            msg_board2.innerHTML = old_msg2 + received_msg2 + "<br>";

            // 讓滾動塊往下移動
            msg_board2.scrollTop = msg_board2.scrollTop + 40;
        };
        webSocket2.onclose = function () {
            // 關閉 websocket，清空資訊板
            // alert("連線已關閉...");
            webSocket2 = null;
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
    if (webSocket != null || webSocket2 != null) {
        let openroom_msg = document.getElementById("openroom_msg").value.trim();
        let roomNo_msg = document.getElementById("roomNo_msg").value.trim();

        if (openroom_msg == "" && roomNo_msg == "") {
            alert('請輸入訊息')
            return;
        }else{
            if(num == 1){
                webSocket.send(openroom_msg);
            }else{
                let roomNo = $('#input_roomNo').val();
                if(roomNo == null || roomNo == '') alert('請輸入房號')
                webSocket2.send(roomNo_msg);
            }
        }
        // 清除input框裡的資訊
        document.getElementById("openroom_msg").value = "";
        document.getElementById("roomNo_msg").value = "";
    } else {
        alert("您已掉線，請重新進入聊天室...");
    }
};
//關閉連線
function closeWs() {
    webSocket2.close();
    alert('退出房間')
};


$(document).ready(function() {
    console.log("contextPath:"+contextPath)

    //公頻傳送按鈕按Enter傳送
    $("#openroom_msg").keyup(function(event){
        if(event.which == 13){
            $("#openroom_btn").click();
        }
        // Make sure the form isn't submitted
        event.preventDefault();
    });
//房號傳送按鈕按Enter傳送
    $("#roomNo_msg").keyup(function(event){
        if(event.which == 13){
            $("#roomNo_btn").click();
        }
        // Make sure the form isn't submitted
        event.preventDefault();
    });

    //留言版表格
    let board = $('#board').DataTable( {// 和<table>的id對應，指定初始化datatables。
        ajax: {
            url: contextPath + '/board.do',
            type: "GET",
            dataType: "json",
            'dataSrc': function (data){
                return data;
            },
            // "success": function(){
            //     console.log("你是右邊!!")
            // }, //成功取得回傳時的事件
            // "error": function(){
            //     console.log("資料取得失敗 回去檢討檢討")
            // } //失敗事件
        },

        // 在初始表格的左上有個可選擇的每頁列數的選單設定
        lengthChange: true,   // 呈現選單
        lengthMenu: [5, 10, 25, 50],   // 選單值設定
        pageLength: 25,   // 不用選單設定也可改用固定每頁列數
        searching: true,   // 搜索功能
        ordering: true,   // 開啟排序
        // 下列 2 個一起用，就可以設定列出全部資料、可滑動又固定尺寸的表格
        paging: false,   // 是否建立分頁
        scrollY: 400,   // 固定可以上下滑動的高度
        // sScrollX : 820, //DataTables的宽
        bStateSave: true,//刪除返回時，保留在同一頁上
        // [指定的列 , 排序方向] 。
        // 預設 [[0, 'asc']] ，asc 升冪排列、desc 降冪排列。
        order: [[ 1, 'asc' ], [ 2, 'asc' ]],
        language: {
            "processing": "處理中...",
            "loadingRecords": "載入中...",
            "lengthMenu": "顯示 _MENU_ 項結果",
            "zeroRecords": "沒有符合的結果",
            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
            "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
            "infoFiltered": "(從 _MAX_ 項結果中過濾)",
            "infoPostFix": "",
            "search": "搜尋:",
            "paginate": {
                "first": "第一頁",
                "previous": "上一頁",
                "next": "下一頁",
                "last": "最後一頁"
            },
            "aria": {
                "sortAscending": ": 升冪排列",
                "sortDescending": ": 降冪排列"
            },
        },
        // 鎖定行//給table內自定義按鈕
        // columnDefs: [{
        //     // targets用於指定操作的列，從第0列開始，-1為最後一列，這裡第六列
        //     targets: [4],
        //     // return後邊是我們希望在指定列填入的按鈕程式碼，也可寫在"columns"裡
        //     render: function ( data, type, full, meta ) {
        //         return "<input type = 'button' id = 'updata' value = '修改'>" +
        //                "<input type = 'button' id = 'delete' value = '刪除'>"
        //     },
        //     // 禁止排序
        //     orderable: false,
        // }],

        //給列賦值，這裡的列名需要和data的資料名對應
        //注意這裡第六列資料指定為null，用來存放我們加入的按鈕
        "columns": [
            { "data": null,"title":"#",
              "sClass" : "text-center"
            },
            { "data": "nickname" ,"title":"暱名"},
            { "data": "message" ,"title":"訊息"},
            { "data": "room_number" ,"title":"房號"},
            { "data": "nickname","title":"編輯",
              "render":function ( data, type, row, meta ) {
                //render方法有四個引數，分別為data、type、row、meta，其中主要是使用data和row來進行操作，
                //data是對應當前cell的值，row是對應當前行中的所有cell的值。
                if(nickname == row.nickname){
                let button = "<input type = 'button' id = 'updata' value = '修改'>" +
                             "<input type = 'button' id = 'delete' value = '刪除'>";
                    return button;
                }
                    return "";
                },},
            { "data": "createDateStr" ,"title":"創建時間"}
        ]
    } );

    //留言版第1欄流水號
    board.on('order.dt search.dt',function (){
        board.column(0,{search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell,i){
            cell.innerHTML = i+1;
        })
    }).draw();

    //上線成員表格
    let login_member = $('#login_member').DataTable( {
        ajax: {
            url: contextPath + '/onlineUser.do',
            type: "GET",
            dataType: "json",
            'dataSrc': function (data) {
                return data;
            }
        },
        // 在初始表格的左上有個可選擇的每頁列數的選單設定
        lengthChange: true,   // 呈現選單
        lengthMenu: [5, 10, 25, 50],   // 選單值設定
        pageLength: 20,   // 不用選單設定也可改用固定每頁列數

        searching: true,   // 搜索功能
        ordering: true,   // 開啟排序

        // 下列 2 個一起用，就可以設定列出全部資料、可滑動又固定尺寸的表格
        paging: false,   // 是否建立分頁
        scrollY: 400,   // 固定可以上下滑動的高度

        // [指定的列 , 排序方向] 。
        // 預設 [[0, 'asc']] ，asc 升冪排列、desc 降冪排列。
        order: [[ 1, 'asc' ], [ 2, 'asc' ]],

        "columns": [
            { "data": null,"title":"#",
                "sClass" : "text-center"
            },
            { "data": "nickname" ,"title":"上線成員"},
            { "data": "loginTimeStr","title":"上線時間",},
        ]

    } );
    //上線成員第1欄流水號
    login_member.on('order.dt search.dt',function (){
        login_member.column(0,{search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell,i){
            cell.innerHTML = i+1;
        })
    }).draw();

    //好友表格
    let friend = $('#friend').DataTable( {
        ajax: {
            url: contextPath + '/friend.do',
            type: "GET",
            dataType: "json",
            'dataSrc': function (data){
                return data;
            },
        },

        // 在初始表格的左上有個可選擇的每頁列數的選單設定
        lengthChange: true,   // 呈現選單
        lengthMenu: [5, 10, 25, 50],   // 選單值設定
        pageLength: 20,   // 不用選單設定也可改用固定每頁列數

        searching: true,   // 搜索功能
        ordering: true,   // 開啟排序

        // 下列 2 個一起用，就可以設定列出全部資料、可滑動又固定尺寸的表格
        paging: false,   // 是否建立分頁
        scrollY: 400,   // 固定可以上下滑動的高度

        // [指定的列 , 排序方向] 。
        // 預設 [[0, 'asc']] ，asc 升冪排列、desc 降冪排列。
        order: [[ 1, 'asc' ], [ 2, 'asc' ]],

        "columns": [
            { "data": null,"title":"#",
                "sClass" : "text-center"
            },
            { "data": "nickname_to" ,"title":"好友清單"},
            { "data": "createDateStr" ,"title":"加入時間"},
        ]

    } );
    //上線成員第1欄流水號
    friend.on('order.dt search.dt',function (){
        friend.column(0,{search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell,i){
            cell.innerHTML = i+1;
        })
    }).draw();

    //表頭擠在一起時
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        //当切换tab时，强制重新计算列宽
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    } );
} );

//新增留言版
function insertBoard(){

    let room_number = $("#room_number").val();
    let message = $("#message").val();

    let row_lenght = document.getElementById("board").rows.length;
    for(let i=0;i<row_lenght;i++){
        let row = document.getElementById("board").rows[i].cells[3].innerHTML;
        if(row == room_number){
            alert('房號不可重複')
            return;
        }
    }

    if(room_number.trim() == null){
        alert('房號必須輸入，數字長度限制5字，請輸入正確格式！')
    }
    if(message == null || (message.length >100)){
        alert('訊息不得為空及長度限制100字，請輸入正確格式！')
    }
    $.ajax({
        url:contextPath + '/board.do',
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
            window.location.reload();
        },
        error:function (xhr, textStatus, errorThrown){
            alert('新增失敗:' + xhr.status + ',' + textStatus + errorThrown)
        }
    });
}

//新增好友
function insertFriend(){
    var nickname_to = $('#nickname_to').val();
    if(nickname_to == nickname) {
        alert('請勿輸入自己暱名')
        return;
    }
    $.ajax({
        url:contextPath + '/friend.do',
        type:'POST',
        data:{
            nickname_to:nickname_to,
        },
        dataType:'json',
        success:function (response){
            //0為新增成功
            if(response.code == 0){
                alert('新增成功')
            }else{
                //300用戶不存在.201新增error
                alert('新增失敗:' +  response.message)
            }
            window.location.reload();
            $('#home').attr('class','tab-pane fade');
            $('#menu3').attr('class','tab-pane fade in active');
        },
        error:function (xhr, textStatus, errorThrown){
            alert('新增失敗:' + xhr.status + ',' + textStatus + errorThrown)
        }
    });
}