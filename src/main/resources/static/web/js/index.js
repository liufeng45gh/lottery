$(document).ready(function(){
    checkLogin();
});

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

    var url = "/wx/check-login";
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
    });
}