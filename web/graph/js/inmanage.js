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
    var format=arguments[1] ? arguments[1] :"yyyy-MM-dd" ;
    var newDate = new Date();
    newDate.setTime(timestamp);
    return  newDate.Format(format);
}

var input=function(o)
{
    layer.open({
        type: 2,
        title: '预览页',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content: _BASE_PATH+'/preview/'
    });
}

var initInfo=function(info,block)
{
	var table=$("#"+block).find("table tbody");
    var types=$.parseJSON($("#types").val());
    console.log(types);
    // var cuKey=0;
    // var opCnt=0;
    // var cnt=0;
    //
    // opCnt+=3;

    var tableTitle=" <thead>" +
        " <tr>" +
        "<th>"+(block=="all"?"类型":"期限")+"</th>" +
        "<th>标题</th>" +
        "<th>发布人</th>" +
        "<th>发布时间</th>" +
        (block=="all"? "<th>截止时间</th>":"") +
        "</tr>" +
        "</thead>"

    $(table).before($(tableTitle));

    var nodePure=" <tr>" +
        " <td class='i-type'><span class=\"label \"></span></td>" +   //前两个tab是截止时间，第三个是类型
        " <td class='i-title'>" +
        "<p><a  data-formToken='' onclick='input(this)' href=\"javascript:void(0)\"><span class=\"event-title\"></span></a></p>" +
        "</td>" +
        "<td class='i-name'></td>" +
        "<td class='i-time'></td>" +
        (block=="all"? "<td class='i-deadline'></td>":"") +
        "</tr>";


    for(cuKey in info )
    {
        var node=$(nodePure).clone(true);
        if(block=="all") {
            for(var cuType in types["types"])
            {
                if(types["types"][cuType]["typeCode"]===info[cuKey]["type"]) {
                    $(node).find(".i-type span").html(types["types"][cuType]["typeName"]);
                    $(node).find(".i-type span").addClass(types["types"][cuType]["typeClass"])
                }
            }

            if(info[cuKey]["deadline"]!=undefined&&info[cuKey]["deadline"]!=""&&info[cuKey]["deadline"]!=null&&info[cuKey]["deadline"]!="0"&&info[cuKey]["deadline"]!=0)
                  $(node).find(".i-deadline").html(timestamp2time(info[cuKey]["deadline"],"yyyy-MM-dd hh:mm:ss"));
            else
                $(node).find(".i-deadline").html("");
        }
        else if(info[cuKey]["deadline"]!=undefined&&info[cuKey]["deadline"]!=""&&info[cuKey]["deadline"]!=null&&info[cuKey]["deadline"]!="0"&&info[cuKey]["deadline"]!=0)
        {
            $(node).find(".i-type span").html(timestamp2time(info[cuKey]["deadline"],"yyyy-MM-dd hh:mm:ss"));
            $(node).find(".i-type span").addClass("label-warning")
        }

        $(node).find(".i-title a").attr("data-formToken",info[cuKey]["formToken"]);
        $(node).find(".i-title span").html(info[cuKey]["title"]);


        $(node).find(".i-name").html(info[cuKey]["userId"]);

        $(node).find(".i-time").html(timestamp2time(info[cuKey]["time"]));

        $(table).append($(node));
    }

    // opCnt+=cnt;
    // cuKey++;
    // var node="";
    // while(opCnt--)
    // {
    //     node+="<td></td>";
    // }
    //
    // while(cuKey<10)
    // {
    //     $(ths[cuKey]).append(node);
    //     cuKey++;
    // }

}

var initPage=function(page,block)
{
	 var total=page["total"];
	 var pageNum=page["num"];
	 var cur=page["cur"];

	 var pagination=$("#"+block).find(".pagination li");


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


var init=function()
{
	var Page=$("#cur").val();
	if(!Page)
	{
		Page=1;
	}
    // layer.load(2);

    $(".op-choice").hide();
    $(".pagination li").hide();

    // initTitle();

    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/uninput/"+Page+"/",
        async:true,
        dataType:"json",
        success:function(data){
        	  console.log(data);
        	  if(data["res"]=="OK") {
                  var info = data["info"];
                  var page = data["page"];


                  // var types=data["type"]
                  //
                  initInfo(info,"uninput");
                  initPage(page,"uninput");
                  layer.closeAll('loading');
              }

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            layer.closeAll('loading');
        }
    })


    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/uncorrect/"+Page+"/",
        async:true,
        dataType:"json",
        success:function(data){
            console.log(data);
            if(data["res"]=="OK") {
                var info = data["info"];
                var page = data["page"];


                // var types=data["type"]
                //
                initInfo(info,"uncorrect");
                initPage(page,"uncorrect");
                layer.closeAll('loading');
            }

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            layer.closeAll('loading');
        }
    })


    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/all/"+Page+"/",
        async:true,
        dataType:"json",
        success:function(data){
            console.log(data);
            if(data["res"]=="OK") {
                var info = data["info"];
                var page = data["page"];


                // var types=data["type"]
                //
                initInfo(info,"all");
                initPage(page,"all");
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

	init();

})

