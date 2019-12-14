<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>今天，昆仑能源向您发出邀请</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>

    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="/swiper/dist/css/swiper.min.css"/>
    <link rel="stylesheet" href="/web/css/mobile.css?v=1.0"/>
    <script  src="/web/js/jquery-3.1.1.js"></script>
    <script  type="text/javascript" src="/layer/layer.js"></script>
    <!-- Swiper JS -->
    <script src="/swiper/dist/js/swiper.min.js"></script>
    <script  type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script  type="text/javascript" src="/web/js/wx-share.js?version=1.0"></script>
    <script src="/web/js/common.js"></script>

</head>
<body>
<!-- Swiper -->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <#include "1-page.ftl">
        </div>
        <div class="swiper-slide">
            <#include "2-page.ftl">
        </div>
        <div class="swiper-slide">
            <#include "3-page.ftl">
        </div>

    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
    <div class="arrow-down" id="nextPage"></div>
    <img class="hart" src="/img/hart.png"></img>
</div>

<audio id="bg-music1" controls="controls" autoplay="autoplay" style="display: none">
    <source src="/music/tkzc.mp3" />
</audio>
<img id="music" class="play" style="visibility: visible; opacity: 1;" src="/img/music.png"></img>


<!-- Initialize Swiper -->
<script>
    var swiper = new Swiper('.swiper-container', {
      direction: 'vertical',
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
    });
  </script>

<script src="/web/js/index.js?v=1.1"></script>


</body>
</html>
