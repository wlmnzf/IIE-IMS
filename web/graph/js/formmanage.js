var _BASE_PATH="";
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

var  timestamp2time=function (timestamp)
{
    var newDate = new Date();
    newDate.setTime(timestamp);
    return  newDate.Format("yyyy-MM-dd");
}

var initInfo=function(info,types)
{
	var ths=$("table tbody tr");
    for(var cuKey in info )
    {
    	$(ths[cuKey]).attr("data-sh",	info[cuKey]["formToken"]);
		$(ths[cuKey]).find(".event-title a").html(info[cuKey]["title"]);
        $(ths[cuKey]).find(".time").html(timestamp2time(info[cuKey]["time"]));
        for(var cuCode in types )
		{
			if(info[cuKey]["type"]==types[cuCode]["typeCode"])
			{
               var typeNode=$(ths[cuKey]).find(".event-title").next();
               $(typeNode).addClass(types[cuCode]["typeClass"]);
               $(typeNode).html(types[cuCode]["typeName"]);
               $(ths[cuKey]).find(".op-choice").show();
			}
		}

    }

}

var initPage=function(page)
{
	 var total=page["total"];
	 var pageNum=page["num"];
	 var cur=page["cur"];

	 var pagination=$(".pagination li");



    $(pagination).each(function(){
    	$(this).removeClass("active");
	})

	var strat=-1;
    var end =-1;
	 if(pageNum<=5)
	 {
	 	    strat=1;
	 	    end=pageNum;
	 }
	else
	 {

		 if(cur-2<1)
		 {
             start=1;
             end=start+4;
		 }
		 else if(cur+2>pageNum)
		 {
		 	 end=pageNum;
		 	 start=end-4;
		 }
		 else
		 {
		 	start=cur-2;
		 	end =cur+2;
		 }

	 }

    for(var i=start;i<=end;i++)
    {
        if(i==cur)
            $(pagination[i]).addClass("active");
        $(pagination[i]).attr("data-page",i);
        $(pagination[i]).find("a").html(i);
        $(pagination[i]).find("a").attr("href",_BASE_PATH+"/formManage/"+i+"/")
        $(pagination[i]).show();
    }

    if(cur!=1) {
        $(pagination[0]).show();
        $(pagination[0]).find("a").attr("href",_BASE_PATH+"/formManage/"+(cur-1)+"/")
    }

    if(cur!=end) {
        $(pagination[6]).show();
        $(pagination[6]).find("a").attr("href",_BASE_PATH+"/formManage/"+(cur+1)+"/")
    }
}

var init=function()
{
	var Page=$("#cur").val();
	if(!Page)
	{
		Page=1;
	}
    layer.load(2);
	var UserId="";
	var UserToken="";
    $(".op-choice").hide();
    $(".pagination li").hide();

    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/Form/"+Page+"/",
        async:true,
        data:{"UserId":UserId,"UserToken":UserToken},
        dataType:"json",
        success:function(data){
        	  console.log(data);
        	    var info=data["info"];
 				var page=data["page"];
 				var types=data["type"]

				initInfo(info,types);
				initPage(page);
                layer.closeAll('loading');

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            layer.closeAll('loading');
        }
    })



}



$(document).ready(function(){
	_BASE_PATH=$("#base_path").val();
	var userid="ww";
	var usertoken="wwww";

	init();

	$("[name='preview']").click(function(){
        layer.load(2);
		var formToken=$(this).parent().parent().attr("data-sh");
		if(localStorage["preview_"+formToken])
		{
           localStorage.formJson=localStorage["preview_"+formToken];
            layer.closeAll('loading');
            layer.open({
                type: 2,
                title: '预览页',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content:  _BASE_PATH+'/preview/'
            });
        }
		else
		{
            $.ajax({
                type:"POST",
                url:_BASE_PATH+"/getJson/"+userid+"/"+usertoken+"/",
                async:true,
                data:{"formToken":formToken},
                dataType:"json",
                success:function(data){
                    console.log(data);
                    layer.closeAll('loading');
					if(data["res"]=="OK")
					{
                        localStorage.formJson=localStorage["preview_"+formToken]=data["json"];
                        layer.open({
                            type: 2,
                            title: '预览页',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['380px', '90%'],
                            content:  _BASE_PATH+'/preview/'
                        });
                    }
					else
					{
                        layer.closeAll('loading');
						alert("连接失败");
					}

                },
                error:function(msg){
                    alert("与服务器连接断开...."+JSON.stringify(msg));
                }
            })

		}


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
            url:_BASE_PATH+"/formToken/"+UserId+"/"+UserToken+"/",
            async:true,
            dataType:"json",
            success:function(data){
                 // if(data["res"]=="OK")
				 // {
				 	var formToken=data["formToken"];
				 	console.log(formToken);
                     window.open(_BASE_PATH+"/customForm/"+formToken);
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

