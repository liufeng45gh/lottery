

var lineLink = window.location.href;
if(lineLink.indexOf("?") != -1)
{
    lineLink = lineLink.split("?")[0];
    console.log(lineLink);
}

var shareContent = "一起来玩吧";
var shareTitle = "官微三周年 寻找幸运的你";
var shareImgUrl = "http://question.stack.xin/web/img/share-icon.png";
var appid = "wx1b792045969d2147";
  
$(document).ready(function() {
    checkLogin();
})

function loadWxConfig(){
    var data_send = {};
        //lineLink = location.href.split('#')[0];
    data_send.shareUrl = location.href.split('#')[0];
    url = "/wx-config"

    var more_request =$.ajax({
       type: 'post',
       url: url,
       data: data_send,
       dataType: 'json'
    });

    more_request.fail(function( jqXHR, textStatus ) {
      if(jqXHR.status==401){
         //openWeiboLogin();

      }
    });

    more_request.done(function(data) {
        executeWxConfig(data);
    });
}

function executeWxConfig(data){
        wx.config({
            debug: false,
            appId: appid,
            timestamp: data.timestamp,
            nonceStr: data.nonceStr,
            signature: data.signature,
            jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 功能列表，我们要使用JS-SDK的什么功能
        });

        wx.ready(function(){
            // 获取"分享到朋友圈"按钮点击状态及自定义分享内容接口
            //alert("wx.ready");
            //$("#bg-music1").get(0).play();
            $("#bg-music").get(0).play();
            wx.onMenuShareTimeline({
                title: shareTitle, // 分享标题
                link: lineLink,
                imgUrl: shareImgUrl // 分享图标
            });


            // 获取"分享给朋友"按钮点击状态及自定义分享内容接口
            wx.onMenuShareAppMessage({
                title: shareTitle, // 分享标题
                desc: shareContent, // 分享描述
                link: lineLink,
                imgUrl: shareImgUrl, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                success: function () {
                         // 用户确认分享后执行的回调函数
                         //alert("onMenuShareAppMessage success");
                },
                cancel: function () {
                         // 用户取消分享后执行的回调函数
                }
            });

        });

        wx.error(function(res){

            // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
           alert("微信设置错误");
           alert(res);
        });
}

function openWxLogin(){
    //alert(1);
  var lineLink = window.location.href;
  if(lineLink.indexOf("?") != -1)
  {
      lineLink = lineLink.split("?")[0];
      console.log(lineLink);
  }
    setSessionCookie("login_redirect_url",lineLink);
    window.location.href = "/wx/login?random="+Math.random();
}

function checkLogin() {

    var url = "/wx/check-login?random="+Math.random();
    var data_send = {};

    var request =$.ajax({
        type: 'get',
        url: url,
        dataType: 'json'
    });

    request.fail(function( jqXHR, textStatus ) {
            openWxLogin();
    });

    request.done(function(data) {
        //do nothing
        loadWxConfig();
    });
}