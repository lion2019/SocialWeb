<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="首頁"/>
</jsp:include>

<style type="text/css">
    /*廣告輪播視窗設定<h3>*/
    .full-screen {


    }
    /*廣告輪播超連結<h3>*/
    .item>a{
        display: block;
        height: 100%;
    }

    /*廣告輪播的文字說明設定<h3>*/
    .carousel-caption {
        margin-bottom: 150px;
        color: black;
    }
    /*圖片計數器圓圈外框設定*/
    .carousel-indicators {
        position: absolute; /*整個計數區域絕對定位*/
        bottom: 170px; /*距容器carousel底部10px*/
        z-index: 15; /*設定其在Z軸的層級*/
        /*讓整個計數區水平居中*/
        left: 50%;
        width: 60%;
        padding-left: 0;
        margin-left: -30%;
        text-align: center;
        list-style: none;
    }
    /*圖片計數器圓圈設定*/
    .carousel-indicators li{
        padding: 8px; /*圈圈大小*/
        margin: 0 8px;/*y=0,x=5*/
    }

</style>
</head>
<body>

<div id="mycarousel" class="carousel slide" data-ride="carousel">
    <!--第一步：設計輪播圖片的容器。-->
    <!-- #slidershow 層新增 slide 樣式，使用圖片與圖片切換效果有平滑感-->
    <ol class="carousel-indicators"></ol>
    <!--第二步：設計輪播圖片計數器。-->
    <%--    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>--%>
    <%--    <li data-target="#myCarousel" data-slide-to="1"></li>--%>
    <%--    <li data-target="#myCarousel" data-slide-to="2"></li>--%>
    <%--    <li data-target="#myCarousel" data-slide-to="3"></li>--%>

    <!-- 廣告輪播列表 -->
    <div class="carousel-inner" role="listbox">
        <!--第三步：設計輪播圖片播放區,使用 carousel-inner 樣式來控制-->
        <div class="item active">
            <a href="register.jsp">
                <img src="images/board.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告1</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="login.jsp">
                <img src="images/board.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告2</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="main.jsp">
                <img src="images/board.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告3</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="">
                <img src="images/board.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告4</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
    </div>

    <!--第四步：設計輪播圖片控制器。向前播放left carousel-control和向後播放的控制器-->
    <a class="left carousel-control" href="#mycarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">上一則</span>
    </a>
    <a class="right carousel-control" href="#mycarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">下一則</span>
    </a>
</div>


<script>
    // 輪播設定
    $(function(){
        var $item = $('.carousel .item');
        var $wHeight = $(window).height();
        $item.height($wHeight);
        $item.addClass('full-screen');

        $('.carousel img').each(function() {
            var $src = $(this).attr('src');
            var $color = $(this).attr('data-color');
            $(this).parent().css({
                'background-image' : 'url(' + $src + ')',
                'background-color' : $color,
                'background-size': 'cover',/*背景圖片小於容器時，將背景圖片的大小放大至容器大小並填滿文章*/
                'background-position': 'center',
                'background-repeat': 'no-repeat',
            });
            $(this).remove();
        });

        //下方自動加入控制圓鈕
        var total = $('.carousel .carousel-inner div.item').size();
        append_li();
        function append_li()
        {
            var li = "";
            var get_ac = $( ".carousel .active" );
            var ac =  $( ".carousel .carousel-inner div" ).index( get_ac );

            for (var i=0; i <= total-1; i++){
                if(i == (ac)/2){
                    li += "<li data-target='#mycarousel' data-slide-to='"+i+"' class='active'></li>";
                }else{
                    li += "<li data-target='#mycarousel' data-slide-to='"+i+"' class=''></li>";
                }
            }
            $(".carousel-indicators").append(li);
        }

        //單則隱藏控制鈕
        if ($('.carousel .carousel-inner div.item').length < 2 ) {
            $('.carousel-indicators, .carousel-control').hide();
        }

        //縮放視窗調整視窗高度
        $(window).on('resize', function (){
            $wHeight = $(window).height();
            $item.height($wHeight);
        });

        //輪播秒數與滑入停止
        $('.carousel').carousel({
            interval: 5000,
            pause: "hover"
        });
    });
</script>

<jsp:include page="/WEB-INF/subviews/footer.jsp" />


