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
    var formToken=$(o).attr("data-formtoken");
    var title=$(o).find("span").html();
    var block=$(o).parents(".active").attr("id");
    data = {
        "formToken":formToken,
        "flag":"c",
        "block":block,
    };
    if(block=="uncorrect")
    {
        localStorage.checkOption=localStorage["checkOption_"+formToken]=""
    }

    if(localStorage["preview_"+formToken]&&localStorage["result_"+formToken]&&localStorage["result_"+formToken]!=""&&localStorage["checkOption_"+formToken]!="")
    {
        localStorage.formJson=localStorage["preview_"+formToken];
        // localStorage.formJson=localStorage["preview_"+formToken]=data["json"];
        localStorage.result=localStorage["result_"+formToken];
        localStorage.needCheck=localStorage["needCheck_"+formToken];
        localStorage.checkOption=localStorage["checkOption_"+formToken];
        localStorage.isChecked=localStorage["isChecked_"+formToken];
        layer.closeAll('loading');
        layer.open({
            type: 2,
            title:title ,
            shadeClose: true,
            shade: 0.8,
            area: ['100%', '100%'],
            content:  _BASE_PATH+'/preview/'
        });
    }
    else
    {
        $.ajax({
            type:"POST",
            url:_BASE_PATH+"/getJson",
            async:true,
            data:{"formToken":formToken},
            dataType:"json",
            success:function(data){
                console.log(data);
                layer.closeAll('loading');
                if(data["res"]=="OK")
                {
                    localStorage.formJson=localStorage["preview_"+formToken]=data["json"];
                    localStorage.result=localStorage["result_"+formToken]=data["result"];
                    localStorage.needCheck=localStorage["needCheck_"+formToken]=data["needCheck"];
                    localStorage.checkOption=localStorage["checkOption_"+formToken]=data["checkOption"];
                    localStorage.isChecked=localStorage["isChecked_"+formToken]=data["isChecked"];
                    // localStorage.formJson=localStorage["preview_"+formToken]=data["json"];
                    layer.open({
                        type: 2,
                        title: title,
                        shadeClose: true,
                        shade: 0.8,
                        area: ['100%', '100%'],
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


}

var initInfo=function(info,block)
{
	var table=$("#"+block).find("table tbody");
	table.empty();
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

   if($(table).parent().find("thead").length<=0)
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


var changePage=function(o){
    var page=$(o).attr("data-page");
    var block=$(o).parents(".active").attr("id");

    layer.load(2);
    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/"+block+"/"+page+"/",
        async:true,
        dataType:"json",
        success:function(data){
            console.log(data);
            if(data["res"]=="OK") {
                var info = data["info"];
                var page = data["page"];


                // var types=data["type"]
                //
                initInfo(info,block);
                initPage(page,block);
                layer.closeAll('loading');
            }

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            layer.closeAll('loading');
        }
    })

}



var initPage=function(page,block)
{
	 var total=page["total"];
	 var pageNum=page["num"];
	 var cur=page["cur"];

	 var pagination=$("#"+block).find(".pagination li");


    $(pagination).each(function(){
    	$(this).removeClass("active");
        $(this).removeClass("disabled");
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
        $(pagination[i]).find("a").attr("data-page",i);
        $(pagination[i]).find("a").attr("href","javascript:void(0)");
        $(pagination[i]).find("a").attr("onclick","changePage(this)");
        $(pagination[i]).show();
    }

    if(cur!=1) {
        $(pagination[0]).show();
        $(pagination[0]).find("a").attr("data-page",cur-1);
        $(pagination[0]).find("a").attr("href","javascript:void(0)");
        $(pagination[0]).find("a").attr("onclick","changePage(this)");
        // $(pagination[0]).removeClass("disabled");
        // $(pagination[0]).find("a").attr("href",_BASE_PATH+"/formResult/"+formToken+"/"+(cur-1)+"/")
    }
    else
    {
        $(pagination[0]).show();
        $(pagination[0]).addClass("disabled");
        $(pagination[0]).find("a").attr("href","javascript:void(0)");
    }

    if(cur!=end) {
        $(pagination[6]).show();
        $(pagination[6]).find("a").attr("data-page",cur+1);
        $(pagination[6]).find("a").attr("href","javascript:void(0)");
        $(pagination[6]).find("a").attr("onclick","changePage(this)");
        // $(pagination[0]).removeClass("disabled");
        // $(pagination[6]).find("a").attr("href",_BASE_PATH+"/formResult/"+formToken+"/"+(cur+1)+"/")
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

