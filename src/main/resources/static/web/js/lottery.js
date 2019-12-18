var angle = 0;
var rotateIntervalId = null;

function startRotate(){
    rotateIntervalId = setInterval(function(){
        angle +=3;
        $('#rotate-dish').rotate(angle);
    }, 50);
}

function stopRotate(){
     clearInterval(rotateIntervalId);
}
