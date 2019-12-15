var swiper = new Swiper('.swiper-container', {
  direction: 'vertical',

});

$(document).ready(function(){
    //checkLogin();
    $("#start-btn").click(function(){
        swiper.slideTo(1);
    });

    $("#p2-submit").click(function(){
            swiper.slideTo(2);
    });
    $("#opportunity-text").click(function () {
        openWin(1);
    });
});



function openWin(id){
    var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "/win?id=" + id
    layer.open({
      type: 2,
      shade: 0.3,
      skin: 'layer-test' ,
      title: false, //不显示标题
      area: ['89%', '60%'], //宽高
      //offset: ['50%', '50%'],
      closeBtn: 0,
      content: [url, 'yes'],//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
      cancel: function(){
        //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000, icon:6});
      }
    });
}


