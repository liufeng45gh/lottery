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
    }, 50);

    var ranInteger = getRndInteger(0,5);
    setTimeout(function() {
        showReward(ranInteger);
    },10000);
}

function stopRotate(){
     clearInterval(rotateIntervalId);
     rotateIntervalId = null;
}

function getRndInteger(min, max) {
  return Math.floor(Math.random() * (max - min + 1) ) + min;
}

function showReward(index){
     clearInterval(rotateIntervalId);
     var times = angle/360;
     if (index == 0){
        angle = times* 360 + 360;
     } else if (index == 1){
        angle = times* 360 + 360 + 323;
     } else if (index == 2){
        angle = times* 360 + 360 + 251;
     }else if (index == 3){
        angle = times* 360 + 360 + 180;
     }else if (index == 4){
        angle = times* 360 + 360 + 107;
     }else if (index == 5){
        angle = times* 360 + 360 + 34;
     }

     $('#rotate-dish').rotate(angle);
     if (index != 0) {
      setTimeout(function() {
             openWin(index);
         },1000);

     }

     rotateIntervalId = null;
}

$(document).ready(function(){
    $("#lottery-start").click(function (){
        startRotate();
    });
});
