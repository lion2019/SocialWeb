<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="首頁"/>
</jsp:include>

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
            <a href="https://www.cdc.gov.tw/" target="_blank">
                <img src="images/CDC.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告1</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="http://www.hwh.edu.tw/bin/hwhhome.php" target="_blank">
                <img src="images/Haw_Hsia.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告2</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="https://cahh.hwh.edu.tw/" target="_blank">
                <img src="images/Haw_Yun.jpg" data-color="#eee">
                <div class="carousel-caption">
                    <h3>廣告3</h3>
                    <p>&nbsp;</p>
                </div>
            </a>
        </div>
        <div class="item">
            <a href="http://student.hwh.edu.tw/student/" target="_blank">
                <img src="images/Student_Parent.jpg" data-color="#eee">
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

<jsp:include page="/WEB-INF/subviews/footer.jsp" />


