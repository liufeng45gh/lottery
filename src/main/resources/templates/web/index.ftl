<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>官微三周年 幸运大转盘,每天三次机会,赢100元加油卡,超高中奖概率</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>

    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="/swiper/dist/css/swiper.min.css"/>
    <link rel="stylesheet" href="/web/css/mobile.css?v=1.0"/>
    <script  src="/web/js/jquery-3.1.1.js"></script>
    <script  type="text/javascript" src="/layer/layer.js"></script>
    <!-- Swiper JS -->
    <script src="/swiper/dist/js/swiper.min.js"></script>
    <script  type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>

    <script  type="text/javascript" src="/web/js/wx-share.js?version=1.0"></script>
    <script src="/web/js/common.js"></script>
    <style>

        body .layer-test{background: none; box-shadow: none;}
    </style>

</head>
<body>
<!-- Swiper -->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide swiper-no-swiping">
            <#include "1-page.ftl">
        </div>
        <div class="swiper-slide swiper-no-swiping">
            <#include "2-page.ftl">
        </div>
        <div class="swiper-slide swiper-no-swiping">
            <#include "3-page.ftl">
        </div>

    </div>


</div>





<!-- Initialize Swiper -->

<script src="/web/js/index.js?v=1.1"></script>


</body>
</html>
