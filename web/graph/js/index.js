var _BASE_PATH="";
$(document).ready(function(){
    _BASE_PATH=$("#basepath").val();
    $("#submit").click(function(){
        var account=$.trim($("#account").val());
        var password=$.trim($("#password").val());
        password=$.md5(password);
        console.log(password);
        if(account==""||password=="")
              return;
        // $.ajax({
        //     type:"POST",
        //     url:_BASE_PATH+"/Login",
        //     async:true,
        //     data:{"UserId":account,"Pass":password},
        //     dataType:"json",
        //     success:function(data){
        //         console.log(data);
        //         if(data["res"]=="OK")
        //         {
        //             // var jsonObj={};
        //             // jsonObj["account"]=account;
        //             // jsonObj["token"]=data["token"];
        //             // jsonObj["type"]=data["type"]
        //             // $.cookie('loginInfo', JSON.stringify(jsonObj), { expires: 7;HTTPOnly });
        //
        //             if(data["type"]==1)//学生
        //             {
        //
        //             }
        //             else if(data["type"]==2)//管理员
        //             {
        //                 window.location.href=_BASE_PATH+'/announceMagement'
        //             }
        //         }
        //         else
        //         {
        //             alert("密码错误！");
        //         }
        //
        //         layer.closeAll('loading');
        //
        //     },
        //     error:function(msg){
        //         alert("与服务器连接断开...."+JSON.stringify(msg));
        //         layer.closeAll('loading');
        //     }
        // })

    });
})