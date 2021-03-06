var _CURRENT_SETTING_NODE
var _BASE_PATH="";
function isNull( str )
{
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

var	getPageLocation=function (event)
		{
		    var e = event||window.event;
	        return { 'x': e.clientX, 'y': e.clientY};
		}	
	
	
var getCurrentIndex=function(target)
	{
		var pageLocation=getPageLocation();
			if(target.dropArea.children.length>0)
		  		{
		  			for(var i=0;i<target.dropArea.children.length;i++)
		  			{
		  				var pos=target.dropArea.children[i].getBoundingClientRect();
		  				if(pageLocation.y>=pos.bottom)
		  					continue;
		  				return i;
		  			}
		  		}
		  		return -1;
	}

function mousePosition(e)  
{//获取鼠标所在位置的坐标，相对于整个页面  
  var x,y;   
  var e = e||window.event;   
  return {   
    x:e.clientX+document.body.scrollLeft + document.documentElement.scrollLeft,   
    y:e.clientY+document.body.scrollTop + document.documentElement.scrollTop   
  };   
} 

function elemPosition( oElement )  
{  
  var x2 = 0;  
  var y2 = 0;  
  var width = oElement.offsetWidth;  
  var height = oElement.offsetHeight;  
//alert(width + "=" + height);  
  if( typeof( oElement.offsetParent ) != 'undefined' )  
  {  
    for( var posX = 0, posY = 0; oElement; oElement = oElement.offsetParent )  
    {  
      posX += oElement.offsetLeft;  
      posY += oElement.offsetTop;       
    }  
    x2 = posX + width;  
    y2 = posY + height;  
    return { x1:posX, y1:posY ,x2:x2,y2: y2};  
     
    } else{  
      x2 = oElement.x + width;  
      y2 = oElement.y + height;  
      return { x1:oElement.x, y1:oElement.y, x2:x2, y2:y2};  
  }  
} 


var alterCurSetting=function()
{
//	console.log("alterCurSetting");
	var node=_CURRENT_SETTING_NODE
    var nodeType=$(node).data("type");
    if(nodeType=="t1")
    {
    	$.getJSON (_BASE_PATH+"/graph/js/config.json", function (data)
          {  
               var optionsSetting=data[nodeType];
               var jsonObj={};
               var index=0;
               
               var inputs=$(".popover-content").find("input");
                  
			   for (var cuKey in optionsSetting)
				{
					jsonObj[cuKey]=$(inputs[index]).val();
					if(cuKey=="label")
					{
						$(node).find("label").html($(inputs[index]).val());
					}
					else
					{
						$(node).find("input").attr(cuKey,$(inputs[index]).val());
					}
					index++;
				}
				
				var jsonText=JSON.stringify(jsonObj);
				$(node).attr("data-json",jsonText);
				console.log(jsonText);
          }); 
    	
    }
    else if(nodeType=="t2")
    {
    	$.getJSON (_BASE_PATH+"/graph/js/config.json", function (data)
          {  
               var optionsSetting=data[nodeType];
               var jsonObj={};
               var index=0;
               
               var inputs=$(".popover-content").find("input");
                  
			   for (var cuKey in optionsSetting)
				{
					jsonObj[cuKey]=$(inputs[index]).val();
					if(cuKey=="label")
					{
						$(node).find("label").html($(inputs[index]).val());
					}
					else if(cuKey=="value")
					{
						$(node).find("textarea").html($(inputs[index]).val());
					}
					else
					{
						$(node).find("textarea").attr(cuKey,$(inputs[index]).val());
					}
					index++;
				}
				
				var jsonText=JSON.stringify(jsonObj);
				$(node).attr("data-json",jsonText);
				console.log(jsonText);
          }); 
    	
    }
    else if(nodeType=="t3")
    {
    	var jsonObj={};
        var index=0;
        var nodeText="";
    	var inputs=$(".popover-content").find(".op");
    	jsonObj[0]=$(".popover-content > input").val();
    	jsonObj[1]={};
    	
    	$(node).html("");
    	nodeText="<label>"+jsonObj[0]+"</label>";
    	$(node).append(nodeText);
    	
    	for(;index<inputs.length;index++)
    	{
    		var opText=$( $(inputs[index]).find("input")[1] ).val();
    		if(opText==null||opText==undefined||isNull(opText))
    		    continue;
    		
    		jsonObj[1][opText]=$(inputs[index]).find("label input")[0].checked?"1":"0";
    		
    		var check  		
    		if($(inputs[index]).find("label input")[0].checked)
    		   check=" checked=\"true\" "
    		else
    		   check="";
    		   
    		//创建节点
            nodeText="<label><input type=\"checkbox\" "+check+">"+opText+"</label>";
    	    $(node).append(nodeText);
    	}
    	
    	jsonText=JSON.stringify(jsonObj);
		$(node).attr("data-json",jsonText);
		console.log(jsonText);
    }
    else if(nodeType=="t4")
    {
    	var jsonObj={};
        var index=0;
        var nodeText="";
    	var inputs=$(".popover-content").find(".op");
    	jsonObj[0]=$(".popover-content > input").val();
    	jsonObj[1]=[];
    	jsonObj[2]=-1;
    	
    	$(node).html("");
    	nodeText="<label>"+jsonObj[0]+"</label>";
    	$(node).append(nodeText);
    	
    	for(;index<inputs.length;index++)
    	{
    		var opText=$( $(inputs[index]).find("input")[1] ).val();
    		if(opText==null||opText==undefined||isNull(opText))
    		    continue;
    		
     		jsonObj[1][index]=opText;
    		
    		var check  		
    		if($(inputs[index]).find("label input")[0].checked)
    		{
    		   //这里应该进行设定，只允许一个选项被选中
//  		   if(jsonObj[2]==-1)
//  		   {
    		   		jsonObj[2]=index;
    		   		check=" checked=\"true\" ";
//  		   	}
//  		   	else
//  		   	{
////  		   	   $(inputs[index]).find("label input")[0].checked=false;
//					$(inputs).each(function(index,elem){
//						$(elem).find("label input")[0].checked=false;	
//					})
//					$(inputs[index]).find("label input")[0].checked=true;
//					jsonObj[2]=index;
//  		   		check=" checked=\"true\" ";
//  		   	   console.log("单选框最多只允许一个被选中");

//  		   	}
    		}
    		else
    		   check="";
    		   
    		//创建节点
            nodeText="<label><input type=\"radio\" "+check+">"+opText+"</label>";
    	    $(node).append(nodeText);
    	}
    	
    	jsonText=JSON.stringify(jsonObj);
		$(node).attr("data-json",jsonText);
		console.log(jsonText);
    }
}

var radioClick=function(obj)
{
	var inputs=$(".op");
	$(inputs).each(function(index,elem)
	{
	    $(elem).find("label input")[0].checked=false;	
	})
	
	obj.checked=true;
}

var addRadio=function(e)
{
	var textNode="<div class=\"op\"><label><input type=\"radio\"  onchange=\"radioClick(this)\"/></label><input type=\"text\" class=\"form-control\" value=\"新选项\" /></div>";
	$(e).before(textNode);
}

var addCheckBox=function(e)
{
	var textNode="<div class=\"op\"><label><input type=\"checkbox\" /></label><input type=\"text\" class=\"form-control\" value=\"新选项\" /></div>";
	$(e).before(textNode);
}

var applyJson2Setting=function(node,obj)
{
//	obj = $.parseJSON(json); 
	console.log(obj);
	nodeType=$(node).data("type");
	if(nodeType=="t1")
	{
		var optionsSetting=obj[nodeType];
		var textNode="";
//		var jsonObj=$(node).data("json");
var jsonObj=$(node).attr("data-json");
jsonObj=JSON.parse(jsonObj);

		var val="";
		
		for (var cuKey in optionsSetting)
		{
			if(jsonObj&&jsonObj[cuKey])
			   val=jsonObj[cuKey];
			else
			   val="";
			textNode="<label>"+optionsSetting[cuKey]+"</label>"
			textNode+="<input type=\"text\" class=\"form-control\" value=\""+val+"\"/>";
			$(textNode).find("input").data(cuKey);
			$(".popover-content").append(textNode);
		}
		textNode="<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterCurSetting()\">确定</button>";
		$(".popover-content").append(textNode);
		
	}
	else if(nodeType=="t2")
	{
		var optionsSetting=obj[nodeType];
		var textNode="";
//		var jsonObj=$(node).data("json");
var jsonObj=$(node).attr("data-json");
jsonObj=JSON.parse(jsonObj);

		var val="";
		
		for (var cuKey in optionsSetting)
		{
			if(jsonObj&&jsonObj[cuKey])
			   val=jsonObj[cuKey];
			else
			   val="";
			textNode="<label>"+optionsSetting[cuKey]+"</label>"
			textNode+="<input type=\"text\" class=\"form-control\" value=\""+val+"\"/>";
			$(textNode).find("input").data(cuKey);
			$(".popover-content").append(textNode);
		}
		textNode="<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterCurSetting()\">确定</button>";
		$(".popover-content").append(textNode);
		
	}
	else if(nodeType=="t3")  //多选
	{
		var optionsSetting=obj[nodeType];
//		var jsonObj=$(node).data("json");
var jsonObj=$(node).attr("data-json");
jsonObj=JSON.parse(jsonObj);
		var val="";
		
		if(jsonObj&&jsonObj[0])
			val=jsonObj[0];
		else
			val=optionsSetting[0];
		
	    var textNode="<label>标题</label>"
	    textNode+="<input type=\"text\" class=\"form-control\" value=\""+val+"\" />";
		$(".popover-content").append(textNode);
		textNode="";
		var checkInfo="";
		
		if(jsonObj&&jsonObj[1])
			optionsSetting[1]=jsonObj[1];

			
		for (var cuKey in optionsSetting[1])
		{	
			checkInfo="";

			if(optionsSetting[1][cuKey]==1||optionsSetting[1][cuKey]=="1")
			       checkInfo="checked=\"true\""
			textNode="<div class=\"op\"><label><input type=\"checkbox\" "+checkInfo+" /></label><input type=\"text\" class=\"form-control\" value=\" "+cuKey+" \" /></div>";
			$(".popover-content").append(textNode);
		}
		textNode="<button type=\"button\" class=\"btn btn-default\" onclick=\"addCheckBox(this)\">新增选项</button>";
		textNode+="<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterCurSetting()\">确定</button>";
		$(".popover-content").append(textNode);
		
		
	}
	else if(nodeType=="t4") //单选
	{
		var optionsSetting=obj[nodeType];
//		var jsonObj=$(node).data("json");
var jsonObj=$(node).attr("data-json");
jsonObj=JSON.parse(jsonObj);

		var val="";
		
		if(jsonObj&&jsonObj[0])
			val=jsonObj[0];
		else
			val=optionsSetting[0];
			
			
	    var textNode="<label>标题</label>"
	    textNode+="<input type=\"text\" class=\"form-control\" value=\""+val+"\" />";
		$(".popover-content").append(textNode);
		textNode="";
		
		if(jsonObj&&jsonObj[1])
		{
			optionsSetting[1]=jsonObj[1];
			optionsSetting[2]=jsonObj[2];
		}
		
		var checkInfo="";
		for (var cuKey in optionsSetting[1])
		{	
			checkInfo="";
			if(cuKey==optionsSetting[2])
			       checkInfo="checked=\"true\""
			textNode="<div class=\"op\"><label><input type=\"radio\" "+checkInfo+" onchange=\"radioClick(this)\" /></label><input type=\"text\" class=\"form-control\" value=\" "+optionsSetting[1][cuKey]+" \" /></div>";
			$(".popover-content").append(textNode);
		}
		textNode="<button type=\"button\" class=\"btn btn-default\" onclick=\"addRadio(this)\">新增选项</button>";
		textNode+="<button type=\"button\" class=\"btn btn-primary\" onclick=\"alterCurSetting()\">确定</button>";
		$(".popover-content").append(textNode);
	}
	else
	{
		console.log("typeError:can't find type");
	}
	
}

var initSetting=function(node)
{
//	if it's new page read config else open from db; 
//	if($(node).data("json")==""||$(node).data("json")==null||$(node).data("json")==undefined)
	   $.getJSON (_BASE_PATH+"/graph/js/config.json", function (data)
          {  
           applyJson2Setting(node,data);
          });  
//  else
//  {
//  	var json=$(node).data("json");
////  	var data=$.parseJSON(json);
////		json="{"+$(node).data("type")+":"+json+"}";
//      applyJson2Setting(node,json);
//  }
        
	
}


var setInfo=function(e)
	{
		if($(".popover").length>0)
		{
		   $(".popover-content").html("");
		   $("#showSetting button").trigger("click");
		   return;
		}
	  	var node=e.target;
	  	if(!$(node).hasClass("form-group"))
	  	    node=$(node).parent(".form-group")[0]
	  	_CURRENT_SETTING_NODE=node;
	  	var pos=elemPosition(node);
	  	$("#showSetting button").trigger("click");
        initSetting(node);
		$(".popover").css("top",pos.y1);
		$(".popover").css("left",pos.x2);
		$(".popover .arrow").css("top","26px");
	   
	    
	}

var getTotalJson=function()
{
	var totals=$("#dropArea .form-group");
	var totalJson=[];
    // var formToken=$("#formToken").val();

	if(totals.length>0)
	{
		totals.each(function(index,element){
			totalJson[index]={};
			totalJson[index]["type"]=$(element).attr("data-type")
			totalJson[index]["data"]=JSON.parse($(element).attr("data-json"));
		})
	}
    var json=  JSON.stringify(totalJson);
	localStorage.formJson=json;
	console.log(localStorage.formJson);
	return json;
}



$(document).ready(function(){

	  _BASE_PATH=$("#base_path").val();

	  $('[data-toggle="popover"]').popover();


	  $("#submit").click(function(){
          var title=prompt("请输入标题","新建表单")
		  if(title===null)
		  	  return;
          if(title=="") {
              alert("不能为空");
              return;
          }

	       var json=getTotalJson();
			var formToken=$("#formToken").val();
			var deadline=$("#deadline").val();
			var type=$(".btn-group .btn-primary").attr("data-type");
			if(!$(".dateOption  input")[0].checked)
			{
				if(deadline=="")
				{
					alert("截止时间不能为空");
					return;
				}
                deadline=Date.parse(deadline);
			}
			else
			{
				deadline=0;
			}
			// var d=new Date(deadline);
			// console.log( Date.parse(deadline));
			if(formToken)
			{
                var UserId="wlm";
                var UserToken="www";


                $.ajax({
                    type:"POST",
                    url:_BASE_PATH+"/saveForm",
                    async:true,
					data:{"formToken":formToken,"json":json,"type":type,"title":title,"deadline":deadline},
                    dataType:"json",
                    success:function(data){
                        if(data["res"]=="OK")
                        {
							alert("提交成功");
                        }
                        else
                        {
                          alert("新建表单失败！");
                        }

                    },
                    error:function(msg){
                        alert("与服务器连接断开...."+JSON.stringify(msg));
                    }
                })
			}
			else
			{
				alert("formToken不存在");
			}
	  });
	  
	   $("#preview").click(function(){
	   	  getTotalJson();
	      layer.open({
				  type: 2,
				  title: '预览页',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['380px', '90%'],
				  content: _BASE_PATH+'/preview/'
			}); 
	  });

    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
    });

    $('.form_datetime').hide();

    $(".dateOption  input").click(function(o){

			if(!this.checked)
			{
                // $(this).find("input")[0].checked=true;
                $(".option_button").css("height","150px");
                $('.form_datetime').show();
			}
			else
			{
                // $(this).find("input")[0].checked=false;
                $(".option_button").css("height","100px");
                $('.form_datetime').hide();
			}
        // o.stopPropagation();
		// return false;
	})

	$(".btn-group .btn").click(function(){
        $(".btn-group .btn").each(function(index,o){
        	$(o).removeClass("btn-primary");
            $(o).removeClass("btn-default");
            $(o).addClass("btn-default");
		})

        $(this).removeClass("btn-default");
		$(this).addClass("btn-primary")
	})
});