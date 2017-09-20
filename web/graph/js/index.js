var _BASE_PATH="";
$(document).ready(function(){
    _BASE_PATH=$("#basepath").val();
    $("#submit").click(function(){
        window.location.href=_BASE_PATH+'/announceMagement'
    });
})