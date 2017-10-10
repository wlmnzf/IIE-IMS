var _BASE_PATH="";
//提交答案
var tj=function(){

	if(parent.data.flag!="c")
		return;

   var nodes=$(".op");
   var jsonObj={};

   $(nodes).each(function(index,o){
	   jsonObj[index]={};
       jsonObj[index]["type"]=$(o).attr("data-type");
       if(  jsonObj[index]["type"]=="t1")
            jsonObj[index]["data"]=$(o).find("input").val();
       else if(jsonObj[index]["type"]=="t2")
	   {
           jsonObj[index]["data"]=$(o).find("textarea").val();
	   }
       else if(  jsonObj[index]["type"]=="t3")
	   {
           jsonObj[index]["data"]={};
           var checked="";
           var content="";
           $(o).find("input").each(function (index,c) {
			     if(c.checked)
				 {
				 	if(checked=="")
					{
						checked=""+index;
						content=""+$(c).val()
					}
					else
					{
						checked=checked+"|"+index;
                        content=content+"|"+$(c).val()
					}
				 }
           });
           jsonObj[index]["data"]["index"]=checked;
           jsonObj[index]["data"]["content"]=content;
	   }
	   else if(  jsonObj[index]["type"]=="t4")
	   {
           jsonObj[index]["data"]={};
           var checked="";
           var content="";
           $(o).find("input").each(function (index,c) {
               if(c.checked)
               {
                       checked=""+index;
                       content=""+$(c).val()
               }
           });
           jsonObj[index]["data"]["index"]=checked;
           jsonObj[index]["data"]["content"]=content;
	   }
	   else
	   {
	   	  alert("error");
	   }

	})

	console.log(jsonObj);
	var jsonText=JSON.stringify(jsonObj);


	// var UserId="11";
	// var UserToken="22";
	// var name="wlm";
	var formToken=parent.data.formToken;
    $.ajax({
        type:"POST",
        url:_BASE_PATH+"/saveClientForm",
        async:true,
        data:{"formToken":formToken,"Json":jsonText,"block":parent.data.block},
        dataType:"json",
        success:function(data){
            console.log(data);
            if(data["res"]=="OK") {
                alert("保存成功");
                localStorage.result=localStorage["result_"+formToken]="";
            }
            else
            	alert("保存失败");

        },
        error:function(msg){
            alert("与服务器连接断开...."+JSON.stringify(msg));
            // layer.closeAll('loading');
        }
    })

}

$(document).ready(function(){
	
	var jsonText=localStorage.formJson;;
    var result= localStorage.result;
    var needChecked=localStorage.needCheck;
    var checkOption=localStorage.checkOption;
    var isChecked=localStorage.isChecked;

	if(jsonText==null)
		return;

	var resFlag=false;
	jsonObj=JSON.parse(jsonText);

	if(result!="") {
        result = JSON.parse(result)
		resFlag=true;
    }

    var checkFlag=false;
	if(checkOption!="") {
        checkOption = JSON.parse(checkOption);
        // if(parent.data.block=="uncorrect") {
            checkFlag = true;

        // }
    }


	console.log(jsonObj);
    console.log(result);
    console.log(needChecked);
    console.log(checkOption);
    console.log(isChecked);

      _BASE_PATH=$("#basepath").val();
	
	for(var i=0;i<jsonObj.length;i++)
	{
        var nodeText="";
		if(jsonObj[i]["type"]=="t1")
		{
			    nodeText="<li data-type='"+jsonObj[i]["type"]+"'  class='op'>";
                nodeText+="<div class=\"item-content\">";
                nodeText+="<div class=\"item-inner\">";
                nodeText+="<div class=\"item-title label\">"+jsonObj[i]["data"]["label"]+"</div>";
          		nodeText+="<div class=\"item-input\">";
                nodeText+="<input type=\"text\"  placeholder=\""+jsonObj[i]["data"]["placeholder"]+"\"  maxlength=\""+jsonObj[i]["data"]["maxlength"]+"\"   value=\""+(resFlag?result[i]["data"]:jsonObj[i]["data"]["value"])+"\" >";
                nodeText+="</div></div></div></li>";
                
            // $(".list-block >ul").append(nodeText);
			
		}
		else if(jsonObj[i]["type"]=="t2")
		{
			    nodeText="<li data-type=\""+jsonObj[i]["type"]+"\"  class='op'>";
                nodeText+="<div class=\"item-content\">";
                nodeText+="<div class=\"item-inner\">";
                nodeText+="<div class=\"item-title label\">"+jsonObj[i]["data"]["label"]+"</div>";
          		nodeText+="<div class=\"item-input\">";
                nodeText+="<textarea   placeholder=\""+jsonObj[i]["data"]["placeholder"]+"\"  maxlength=\""+jsonObj[i]["data"]["maxlength"]+"\" >"+(resFlag?result[i]["data"]:jsonObj[i]["data"]["value"])+"</textarea>";
                nodeText+="</div></div></div></li>";
                
            // $(".list-block >ul").append(nodeText);
		}
		else if(jsonObj[i]["type"]=="t3")
		{
			    nodeText="<div data-type=\""+jsonObj[i]["type"]+"\" class=\"item-content op\">";
        		nodeText+="<div class=\"checkboxR item-inner\">";
    			nodeText+="<div class=\"item-title label\">"+jsonObj[i]["data"][0]+"</div>";
    			nodeText+="<ul>";

            var t3Flag=0;
  			for (var cuKey in jsonObj[i]["data"][1])
  			{
  				var check="";
  				if(resFlag)
				{
					var options=result[i].data.index;
					var optionsRes=options.split("|");
					console.log(optionsRes);
					for(var cuIndex in optionsRes)
					{
						if(parseInt(optionsRes[cuIndex])===t3Flag)
						{
                            check="checked=\"checked\"";
							break;
						}
					}
				}
  				else if((jsonObj[i]["data"][1][cuKey]==1||jsonObj[i]["data"][1][cuKey]=="1"))
					 check="checked=\"checked\"";
  				nodeText+="<li>";
      			nodeText+="<label class=\"label-checkbox item-content\">";
        		nodeText+="<input type=\"checkbox\" name=\"my-checkbox\" value=\""+cuKey+"\" "+check+">";
           		nodeText+="<div class=\"item-media\">";
          		nodeText+="<i class=\"icon icon-form-checkbox\"></i>";
        		nodeText+="</div>";
        		nodeText+="<div class=\"item-inner\">";
          		nodeText+="<div class=\"item-title\">"+cuKey+"</div>";
				nodeText+="</div></label></li>"

				t3Flag++;
  			}
  				nodeText+="</ul></div></div>";
  				// $(".list-block >ul").append(nodeText);
		}
		else if(jsonObj[i]["type"]=="t4")
		{
			    nodeText="<div data-type=\""+jsonObj[i]["type"]+"\" class=\"item-content op\">";
   				nodeText+="<div class=\"radioR item-inner\">";
    			nodeText+="<div class=\"item-title label\">"+jsonObj[i]["data"][0]+"</div>";
    			nodeText+="<ul>";
  

  			for (var cuKey in jsonObj[i]["data"][1])
  			{
  				var check="";
                if(resFlag)
                {
                    var option=result[i].data.index;
                    console.log(option);
					if(option===cuKey) {
                            check = "checked=\"checked\"";
                        }
                }
  				else if(jsonObj[i]["data"][2]==cuKey)
					 check="checked=\"checked\"";
  				nodeText+="<li>";
	      		nodeText+="<label class=\"label-radio item-content\">";
	            nodeText+="<input type=\"radio\" name=\"my-radio\" value=\""+jsonObj[i]["data"][1][cuKey]+"\" "+check+">";
	            nodeText+="<div class=\"item-media\">";
	            nodeText+="<i class=\"icon icon-form-radio\"></i>";
	            nodeText+="</div>";
	            nodeText+="<div class=\"item-inner\">";
	            nodeText+="<div class=\"item-title\">"+jsonObj[i]["data"][1][cuKey]+"</div>";
	        	nodeText+="</div></label></li>";
  			}
 				nodeText+="</ul></div></div>";

		}


        $(".list-block >ul").append(nodeText);
        if(checkFlag)
        {
            if(!checkOption[i])
                $($(".list-block >ul .op")[$(".list-block >ul .op").length-1]).attr("style","display:none");
        }

	}
	
	var nodeText="<li>";
        nodeText+="<div class=\"item-content\">";
        nodeText+="<div class=\"item-inner\">";
		nodeText+="<p class=\"buttons-row\"><a href=\"javascript:void(0)\" class=\"button button-fill button-raised\"   onclick=\"tj()\">提交</a></p></div></div>";
	$(".list-block >ul").append(nodeText);
	
	if(64+$(".list-block").height()+$(".toolbar").height()+10<$(".view").height())
	   {
	   	 $(".toolbar").css("position","absolute");
	   	 $(".toolbar").css("bottom","0");
	   }
})


