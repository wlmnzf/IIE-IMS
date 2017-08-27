/**
 * 互联网向业务专网请求数据封装
 * 请求步骤如下：
 * 1.浏览器端通过ajax向互联网服务器端发送获取数据请求;
 * 2.互联网服务器端接收到获取数据请求并通过mq转发给业务专网;
 * 3.业务专网接处理完请求后通过mq将结果数据发送给互联网Listener；
 * 4.互联网Listener将结果数据保存到数据库中;
 * 5.与此同时,浏览器端通过ajax(暂定每0.5秒轮询一次)向互联网服务器询问是否拿到数据;
 * 6.互联网服务器从数据库拿到数据并返回给浏览器,轮询结束。
 */


/**
 * 发送轮询请求
 * @param requestUrl 请求地址
 * @param requestData 请求参数
 * @param pollingUrl 轮询地址
 * @param callBack  回调函数
 */
function PollingData(requestUrl,requestData,pollingUrl,callBack){
	this._requestUrl = requestUrl;
	this._requestData = requestData;
	this._pollingUrl = pollingUrl;
	this._callBack = callBack;
	this._tryTimes = 0;
	this._TRYCNT = 20;
	this._interval = null;
	this._send();
}
/**
 * 发送获取数据请求
 */
PollingData.prototype._send = function(){
	var _self = this;
	$.ajax({
		type : 'POST',
		async: false,
		contentType : 'application/json;charset=UTF-8',
		url: this._requestUrl,
		dataType:"json",
		data:JSON.stringify(this._requestData),
		success:function(q){
			if('ok' == q.res){
				if(q.data)
					_self._requestData = q.data;
				_self._interval = window.setInterval(function(){_self._doquery(_self);} ,500);
			} else {
				if(_self._callBack){
					_self._callBack(q);
				}
			}
		},
		error:function(){
			layer.alert('向服务器请求数据失败，请联系管理员!',function(){
				layer.closeAll();
				if(_self._callBack){
					_self._callBack();
				}
			});
		}
	});
};

/**
 * 轮询获取数据
 */
PollingData.prototype._doquery = function(self){
	if(self._tryTimes >= self._TRYCNT){
		clearInterval(self._interval);
		layer.alert('向服务器请求数据失败，请联系管理员!',function(){
			layer.closeAll();
			if(self._callBack){
				self._callBack();
			}
		});
		return;
	}
	self._tryTimes++;
	$.ajax({
		type : 'POST',
		async: false,
		contentType : 'application/json;charset=UTF-8',
		url: self._pollingUrl,
		dataType:"json",
		data:JSON.stringify(self._requestData),
		success:function(q){
			if('ok' == q.res){
				clearInterval(self._interval);
				if(self._callBack){
					self._callBack(q);
				}
			}
			
		},
		error:function(a,b,c){
			clearInterval(self._interval);
			layer.alert('向服务器请求数据失败，请联系管理员!',function(){
				layer.closeAll();
				if(self._callBack){
					self._callBack();
				}
			});
		}
	});
};

/**
 * 关闭所有的弹出层
 */
function closeAllLayer(){
	layer.closeAll();
}


/**
 * ajax调用
 */
function doAjax(url, data, issync){
	$.ajax({
		type : 'POST',
		async: !issync,
		contentType : 'application/json;charset=UTF-8',
		url: url,
		dataType:"json",
		data:JSON.stringify(data),
		success:function(q){
			if('ok' == q.res){
				clearInterval(self._interval);
				if(self._callBack){
					self._callBack(q);
				}
			}
			
		},
		error:function(a,b,c){
			clearInterval(self._interval);
			layer.alert('向服务器请求数据失败，请联系管理员!',function(){
				layer.closeAll();
			});
		}
	});
}