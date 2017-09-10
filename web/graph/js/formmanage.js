var _BASE_PATH="";

var initInfo=function(info)
{
    for(var cuKey in info )
    {

    }

}

var initPage=function(page)
{


}

var init=function()
{
	var UserId="";
	var Page="";
	var UserToken="";

    $.ajax({
        type:"GET",
        url:"Form/"+UserId+"/"+UserToken+"/"+Page+"/",
        async:true,
        dataType:"json",
        success:function(data){
        	    var info=data["info"];
 				var page=data["page"];

				initInfo(info);
				initPage(page);

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringfy(msg));
        }
    })



}



$(document).ready(function(){
	_BASE_PATH==$("#base_path").val();

	$("[name='preview']").click(function(){
		layer.open({
				  type: 2,
				  title: '预览页',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['380px', '90%'],
				  content: 'preview.html'
		}); 
	})
	
	
	$("[name='del']").click(function(){
		 var isDel = confirm("确认删除");
			if (isDel) 
			  {
				 alert("删除成功");
			  } 
			else
			  alert("再见啦！");
		
	})
	
	
	$(".table .all").click(function(){
		if(!this.checked)//取消全选
		{
			this.checked=false;
			$("tr th [name='event']").each(function(index){
				if(index&&this.checked)
				    this.checked=false;
			})
		}
		else
		{
			this.checked=true;
			$("tr th [name='event']").each(function(index){
				if(index&&!this.checked)
				    this.checked=true;
			})
		}
		
	})
	
	$("#delAll").click(function(){
 		var isDel = confirm("确认删除");
			if (isDel) 
			  {
			  	//循环删除所有
				 alert("删除成功");
				//刷新页面
			  } 
	
	})
	
	$("#addNew").click(function(){
		//获取token，跳转
		var UserId="wlm";
		var UserToken="www";

        $.ajax({
            type:"GET",
            url:"formToken/"+UserId+"/"+UserToken+"/",
            async:true,
            dataType:"json",
            success:function(data){
                 // if(data["res"]=="OK")
				 // {
				 	var formToken=data["formToken"];
				 	console.log(formToken);
                     window.open("customForm/"+formToken);
                 // }
				 // else
				 // {
					//  alert("新建表单失败！");
				 // }

            },
            error:function(msg){
                alert("与服务器连接断开...."+JSON.stringfy(msg));
            }
        })
	})
})

