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

var initInfo=function(info,block)
{
	var table=$("table tbody");
    var cuKey=0;
    var opCnt=0;
    var cnt=0;

    opCnt+=3;

    for(cuKey in info )
    {
    	// $(ths[cuKey]).attr("data-userid",	info[cuKey]["userid"]);
		var node=" <tr>" +
            " <td class='i-type'><span class=\"label label-warning\"></span></td>" +
            " <td class='i-title'>" +
            "<p><a href=\"user-input.html\"><span class=\"event-title\"></span></a></p>" +
            "</td>" +
            "<td class='i-name'></td>" +
            "<td class='i-time'>2017-08-01</td>" +
            "</tr>";


		//标题也得加上，要不然deadline和时间分不清楚
        //从数据库里取出来把姓名和登录账号都提取出来




        // $(ths[cuKey]).append($(node).html(info[cuKey]["name"]))
        // $(ths[cuKey]).append($(node).html(info[cuKey]["userid"]))
        // $(ths[cuKey]).append($(node).html(timestamp2time(info[cuKey]["time"])))

        //这个是不确定的，需要把json先提取出来然后按顺序添加node

        var jsonText=info[cuKey]["json"];
        var jsonObj=$.parseJSON(jsonText);


    }

    opCnt+=cnt;
    cuKey++;
    var node="";
    while(opCnt--)
    {
        node+="<td></td>";
    }

    while(cuKey<10)
    {
        $(ths[cuKey]).append(node);
        cuKey++;
    }

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

var initTitle=function()
{
    var title= $("#title").val();
    var type= $("#type").val();
    var types=$("#types").val();
    var json= $("#json").val();

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

    var tName=["姓名","学号","时间"];
    for(var cuKey in tName)
    {
        $(".table thead tr").append("<th>"+tName[cuKey]+"</th>");
    }

    var name="";
    for(var cuKey in json)
    {

        if(json[cuKey]["type"]=="t1"||json[cuKey]["type"]=="t2")
        {
          name= json[cuKey]["data"]["label"]
        }
        else
        {
            name= json[cuKey]["data"][0]
        }
        $(".table thead tr").append("<th>"+name+"</th>");
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



}



$(document).ready(function(){
	_BASE_PATH=$("#base_path").val();
	var userid="ww";
	var usertoken="wwww";

	init();

})

