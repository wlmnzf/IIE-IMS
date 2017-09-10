<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8"/>
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>信息录入系统</title>
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css">
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap-theme.min.css" />
    
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/submit.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/customForm_h5.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/jquery.sticky.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/layer/layer.js"></script>
   
	<link rel="stylesheet" href="<%=path%>/graph/css/customForm_h5.css"/>
	<script>
		window.onload=function()
        {
        	drag
        	(
        		{
	        		dragArea:"dragArea",
				    dropArea:"dropArea"
        	    }
        	)
        }
   $(window).load(function(){
      $("#dragArea").sticky({ topSpacing: 30 });
    });
	</script>
</head>
<body>
<header>
    <div class="container-fluid">
        <div class="navbar-header">
            <img class="brand" src="<%=path%>/graph/img/logo.png" alt="">
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right login-ul">
                <li>
                    <div class="login-info">
                        <div class="media-left media-top">
                            <a href="#">
                            <img class="media-object" src="<%=path%>/graph/img/head.jpg" alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading"><span class="label label-primary">管理员</span></h4>
                            <p class="login-name">会码代码的扫地王大爷</p>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="options">
                        <a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>
                        <a class="option" href="#">退出</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</header>
<div class="admin-content clearfix">
    <div class="admin-op-list">
        <ul class="menu">
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-tags"></em>公告</h4>
                <ul class="menu-ul">
                    <li><a href="">公告管理</a></li>
                    <li><a href="">公告编辑</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                <ul class="menu-ul">
                    <li><a href="">页面定制</a></li>
                    <li><a href="">结果管理</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-user"></em>人员</h4>
                <ul class="menu-ul">
                    <li><a href="">人员管理</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-align-left"></em>分组</h4>
                <ul class="menu-ul">
                    <li><a href="">分组管理</a></li>
                </ul>
            </li>
        </ul>
    </div>
    
    
    
    <div class="admin-op-panel">
        <div class="panel-content">
        	<div class="customForm">
	   	   <div class="leftForm">
	   	   		<div class="box clearfix">
	   	   			<div class="block">
	   	   	    	    <div class="name noselect">表单</div>
	   	   	    	    <div class="circle blue"></div>
	   	   	        </div>
	   	   			<form class="blue" id="dropArea">
	   	   				  <!--<div class="circle"></div>-->
						  <!--<div class="form-group">
						    <label for="exampleInputEmail1">Email address</label>
						    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
						  </div>-->
					</form>
	   	   		</div>
	   	   		<div class="operate">
	   	   			<div class="block">
	   	   	    	    <div class="name noselect">操作</div>
	   	   	    	    <div class="circle lightblue"></div>
	   	   	        </div>
	   	   	        <div class="button lightblue noselect">
	   	   	        	<div class="center noselect">
							<button type="button"  id="preview" class="btn btn-default noselect" >预览</button>
						    <button type="button"  id="submit" class="btn btn-primary noselect" >提交</button>
						</div>
					</div>
			    </div>
	   	   </div>
	   	   <div class="rightForm" >
	   	   		<form id="dragArea">
					  <div class="form-group"  data-type="t1"  >
					    <label class="noselect">单行文本</label>
					    <input  type="text" class="form-control noselect" disabled="disabled"   placeholder="单行文本">
					  </div>
					  
					  <div class="form-group"  data-type="t2">
					    <label class="noselect">文本域</label>
					    <textarea class="form-control noselect" rows="3" disabled="disabled"  placeholder="文本域"></textarea>
					  </div>
					 				  
					  <div class="form-group"  data-type="t3">
					  	<label class="noselect">多选</label>
					    <label>
					      <input type="checkbox" checked disabled="disabled" >选项1
					    </label>

					  </div>  
					  
					  <div class="form-group"  data-type="t4">
					  	  <label class="noselect">单选</label>
						  <label>
						    <input type="radio" checked disabled="disabled" >选项1
						  </label>

					 </div>
				</form>
	   	   </div>
	   </div>
	   
	   
        </div>
    </div>
</div>

<div id="showSetting"><button  type="button" class="btn btn-lg btn-danger" data-toggle="popover"  title="请设置信息" data-content=""></button></div>

<footer>
    <p class="copyright text-center noselect">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>
</body>
</html>