var angle = 0;
var rotateIntervalId = null;

function startRotate(){
    if (rotateIntervalId != null){
        return
    }
    angle = 0;
    rotateIntervalId = setInterval(function(){
        angle += 15;
        $('#rotate-dish').rotate(angle);
    }, 60);


    $.ajax({
        type: "POST",
        url: "/do-lottery",
        dataType: "json",
        success: function (data) {
            if (data.ok) {
                var ranInteger = 0;
                if (null != data.data) {
                    ranInteger = data.data.awardId;
                }

                setTimeout(function() {
                    showReward(ranInteger);
                },10000);
            }else {
                layer.msg("系统错误",{icon: 5});
            }

        },
        error: function (message) {
            layer.msg("系统错误",{icon: 5});
        }
    });


}

function stopRotate(){
     clearInterval(rotateIntervalId);
     rotateIntervalId = null;
}

function getRndInteger(min, max) {
  return Math.floor(Math.random() * (max - min + 1) ) + min;
}
var rewardLevel;
function showReward(index){
     rewardLevel = index;
     clearInterval(rotateIntervalId);

     var times = angle/360;
     times = parseInt(times);
     if (index == 0){
        angle = times* 360 + 360;
     } else if (index == 2){
        angle = times* 360 + 360 + 323;
     } else if (index == 3){
        angle = times* 360 + 360 + 251;
     }else if (index == 4){
        angle = times* 360 + 360 + 180;
     }else if (index == 5){
        angle = times* 360 + 360 + 107;
     }else if (index == 1){
        angle = times* 360 + 360 + 34;
     }
     $('#rotate-dish').rotate(angle);


     if (index != 0) {
      setTimeout(function() {
             openWin(index);
         },2000);

     }

     rotateIntervalId = null;
}

$(document).ready(function(){
    $("#lottery-start").click(function (){
        startRotate();
    });
});

function refreshResidue(){
     $.ajax({
            type: "GET",
            url: "/my-residue-count",
            dataType: "json",
            success: function (data) {
                if (data.ok) {
                    $("#opportunity-text").text("您今天还有"+data.data+"次抽奖机会");
                    $("#opportunity-text2").text("剩余机会 "+data.data);
                }else {
                    layer.msg("系统错误",{icon: 5});
                }

            },
            error: function (message) {
                layer.msg("系统错误",{icon: 5});
            }
        });
}
