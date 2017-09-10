$(document).ready(function(){
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
	})
})

