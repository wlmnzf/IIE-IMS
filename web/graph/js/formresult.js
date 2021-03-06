var _BASE_PATH="";
var _CNT=0;
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


var initInfo=function(info)
{
	var ths=$("table tbody tr");
    var cuKey=-1;
    ths.each(function(){
        $(this).find("th input").hide();
    })
    var index=0;
    // var opCnt=0;
    // var cnt=0;

    // opCnt+=3;

    for(cuKey in info )
    {
        $(ths[cuKey]).find("th input").show();
        $($(ths[cuKey]).find("td")[0]).html(index+1);
    	$(ths[cuKey]).attr("data-userid",	info[cuKey]["userid"]);
		var node="<td></td>";
        $(ths[cuKey]).append($(node).html(info[cuKey]["name"]))
        $(ths[cuKey]).append($(node).html(info[cuKey]["userid"]))
        $(ths[cuKey]).append($(node).html(timestamp2time(info[cuKey]["time"])))

        //这个是不确定的，需要把json先提取出来然后按顺序添加node

        var jsonText=info[cuKey]["json"];
        var jsonObj=$.parseJSON(jsonText);

        for(var opKey in jsonObj)
        {
            if(jsonObj[opKey]["type"]=="t1")
            {
                $(ths[cuKey]).append($(node).html(jsonObj[opKey]["data"]))
            }
            else if(jsonObj[opKey]["type"]=="t2")
            {
                $(ths[cuKey]).append($(node).html(jsonObj[opKey]["data"]))
            }
            else if(jsonObj[opKey]["type"]=="t3")
            {
                $(ths[cuKey]).append($(node).html(jsonObj[opKey]["data"]["content"]))
            }
            else if(jsonObj[opKey]["type"]=="t4")
            {
                $(ths[cuKey]).append($(node).html(jsonObj[opKey]["data"]["content"]))
            }
            // if(cuKey=="0")
            // {
            //     cnt++;
            // }
           index++;
        }
        //
        // $(ths[cuKey]).find(".time").html(timestamp2time(info[cuKey]["time"]));
        // for(var cuCode in types )
		// {
		// 	if(info[cuKey]["type"]==types[cuCode]["typeCode"])
		// 	{
         //       var typeNode=$(ths[cuKey]).find(".event-title").next();
         //       $(typeNode).addClass(types[cuCode]["typeClass"]);
         //       $(typeNode).html(types[cuCode]["typeName"]);
         //       $(ths[cuKey]).find(".op-choice").show();
		// 	}
		// }

    }

    // opCnt+=cnt;
    cuKey++;
    var node="";
    while(_CNT--)
    {
        node+="<td></td>";
    }

    while(cuKey<10)
    {
        $(ths[cuKey]).append(node);
        cuKey++;
    }
    ths.each(function(){
        $(this).css("height","39px");
    })
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

	var start=-1;
    var end =-1;
	 if(pageNum<=5)
	 {
	 	    start=1;
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

	 var formToken=$("#formToken").val();
    for(var i=start;i<=end;i++)
    {
        if(i==cur)
            $(pagination[i]).addClass("active");
        $(pagination[i]).attr("data-page",i);
        $(pagination[i]).find("a").html(i);
        $(pagination[i]).find("a").attr("href",_BASE_PATH+"/formResult/"+formToken+"/"+i+"/")
        $(pagination[i]).show();
    }

    if(cur!=1) {
        $(pagination[0]).show();
        $(pagination[0]).find("a").attr("href",_BASE_PATH+"/formResult/"+formToken+"/"+(cur-1)+"/")
    }
    else
    {
        $(pagination[0]).show();
        $(pagination[0]).addClass("disabled");
        $(pagination[0]).find("a").attr("href","javascript:void(0)");
    }

    if(cur!=end) {
        $(pagination[6]).show();
        $(pagination[6]).find("a").attr("href",_BASE_PATH+"/formResult/"+formToken+"/"+(cur+1)+"/")
    }
    else
    {
        $(pagination[6]).show();
        $(pagination[6]).addClass("disabled");
        $(pagination[6]).find("a").attr("href","javascript:void(0)");
    }
}

var initTitle=function()
{
    var title= $("#title").val();
    var type= $("#type").val();
    var types=$("#types").val();
    var json= $("#json").val();

    var cnt=0;

    console.log(json);
    console.log(types);
    console.log(title);
    console.log(type)
    types=$.parseJSON(types);
    json=$.parseJSON(json);


    $(".page-header h3").text(title);
    for(var cuKey in types["types"])
    {
        var typeNode=$(".page-header span");
        $(typeNode).addClass(types["types"][cuKey]["typeClass"]);
        $(typeNode).html(types["types"][cuKey]["typeName"]);
    }

    cnt+=3;
    var tName=["姓名","学号","时间"];
    for(var cuKey in tName)
    {
        $(".table thead tr").append("<th>"+tName[cuKey]+"</th>");
    }

    var name="";
    for(var cuKey in json)
    {
        cnt++;
        if(json[cuKey]["type"]=="t1"||json[cuKey]["type"]=="t2")
        {
          name= json[cuKey]["data"]["label"]
        }
        else
        {
            name= json[cuKey]["data"][0]
        }
        $(".table thead tr").append("<th>"+name+"</th>");
        $(".dropdown-menu").append("<li  data-sh='"+cuKey+"'><input type=\"checkbox\"/><a class='aname' href=\"javascript:void(0)\">"+name+"</a></li>")
    }

    $(".dropdown-menu").append('<li><button id="needCorrect" class="btn btn-default">确定</button></li>');
    var needCheck=$("#needCheck").val()==="1"?1:0;
    if(needCheck===1)
    {
        $(".dropdown>.btn").removeClass("btn-default");
        $(".dropdown>.btn").addClass("btn-info");
        var checkOption=$("#checkOption").val();
        var checkOptionObj=$.parseJSON(checkOption);
        for(var k in checkOptionObj )
        {
            var options=$(".op-buttons .dropdown-menu li");
            $(options[k]).find("input")[0].checked=true;
        }
    }

   _CNT=cnt;
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
	var FormToken=$("#formToken").val();
    $(".op-choice").hide();
    $(".pagination li").hide();

    initTitle();

    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/Result/"+FormToken+"/"+Page+"/",
        async:true,
        data:{"UserId":UserId,"UserToken":UserToken},
        dataType:"json",
        success:function(data){
        	  console.log(data);
        	  if(data["res"]=="OK") {
                  var info = data["info"];
                  var page = data["page"];


                  // var types=data["type"]
                  //
                  initInfo(info);
                  initPage(page);
                  layer.closeAll('loading');
              }

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            layer.closeAll('loading');
        }
    })



}



$(document).ready(function(){
	_BASE_PATH=$("#base_path").val();

	$(".dropdown-toggle").dropdown();

	init();

	$("#needCorrect").click(function(){
	    layer.load(2);
	    var options=$(".op-buttons .dropdown-menu li");
	    var optionObj={}

	    var flag=0;

	    options.each(function(index,o){
	        if($(o).find("input").length>0)
            {
                if($(o).find("input")[0].checked)
                {
                    flag++;
                    optionObj[index]=$(o).find(".aname").text();
                }
            }
        })

        var optionJson=JSON.stringify(optionObj);
	    console.log(optionObj);
	    var formToken=$("#formToken").val();

        $.ajax({
            type:"POST",
            url:_BASE_PATH+"/needCheck",
            async:true,
            data:{"formToken":formToken,"json":optionJson,"flag":flag>0?1:0},
            dataType:"json",
            success:function(data){
                console.log(data);
                layer.closeAll('loading');
                if(data["res"]=="OK")
                {
                    layer.closeAll('loading');
                    $(".dropdown>.btn").removeClass("btn-default");
                    $(".dropdown>.btn").removeClass("btn-info");
                    if(flag>0) {
                        $(".dropdown>.btn").addClass("btn-info");
                    }
                    else
                    {
                        $(".dropdown>.btn").addClass("btn-default");
                    }
                    alert("提交成功");
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



    })

    $("#export").click(function(){
        var formToken=$("#formToken").val();
        window.open(_BASE_PATH+"/export/"+formToken+"/");
    })

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
	
	
	// $("[name='del']").click(function(){
	// 	 var isDel = confirm("确认删除");
	// 		if (isDel)
	// 		  {
	// 			 alert("删除成功");
	// 		  }
	// 		else
	// 		  alert("再见啦！");
	//
	// })
    $("[name='del']").click(function(){
        var isDel = confirm("确认删除");
        if (isDel)
        {
            var formToken=$(this).parents("tr").attr("data-sh");
            $.ajax({
                type:"POST",
                url:_BASE_PATH+"/delRes/",
                async:true,
                data:{"formToken":formToken,"userId":userId},
                dataType:"json",
                success:function(data){
                    console.log(data);
                    layer.closeAll('loading');
                    if(data["res"]=="OK")
                    {
                        layer.closeAll('loading');
                        alert("删除成功");
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


            // alert("删除成功");
        }
        // else
        //     alert("再见啦！");

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
            var data={}
            var index=0;
            data["formToken"]=$("#formToken").val();
            data["res"]={};
            $(".table tbody tr").each(function(index,o){
                var token=$(o).attr("data-userid");
                if(token!=undefined&&$(o).find("th input")[0].checked)
                {
                    data["res"][index]=token;
                    index++;
                }

            })


            $.ajax({
                type:"POST",
                url:_BASE_PATH+"/delRes/m",
                async:true,
                data:{"data":JSON.stringify(data)},
                dataType:"json",
                success:function(data){
                    console.log(data);
                    layer.closeAll('loading');
                    if(data["res"]=="OK")
                    {
                        layer.closeAll('loading');
                        alert("删除成功");
                        location.reload(true);
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

            // alert("删除成功");
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

