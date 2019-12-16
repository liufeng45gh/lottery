
$(document).ready(function(){
    $("#p2-submit").click(function(){

        var wishText = $("#wish-input").val();
        if (wishText.trim()=="" || wishText.length < 6) {
            layer.msg("祝福不能少于5个字");
            return;
        }


        var request_data = {};
        request_data.text = wishText;

        $.ajax({
            type: "POST",
            url: "/wish/commit",
            data: request_data,
            dataType: "json",
            success: function (data) {
                if (data.ok) {
                    layer.msg("提交成功",{icon: 6});
                    setTimeout(function(){
                        swiper.slideTo(2);
                    },2000)
                }else {
                    layer.msg("系统错误",{icon: 5});
                }

            },
            error: function (message) {
                layer.msg("系统错误",{icon: 5});
            }
        });

        //toPage(2);
    });
});
