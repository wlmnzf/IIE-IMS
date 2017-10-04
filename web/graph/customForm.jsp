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
    <script type="text/javascript"   src="<%=path%>/graph/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
   
	<link rel="stylesheet" href="<%=path%>/graph/css/customForm_h5.css"/>
    <link rel="stylesheet" href="<%=path%>/graph/css/datetimepicker/bootstrap-datetimepicker.min.css"/>
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
                    <li><a href="<%=path%>/announceMagement">公告管理</a></li>
                    <li><a href="<%=path%>/announceEditor">公告编辑</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                <ul class="menu-ul">
                    <li class="current"><a href="<%=path%>/formManage">页面定制</a></li>
                    <li><a href="<%=path%>/formResult">结果管理</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-user"></em>人员</h4>
                <ul class="menu-ul">
                    <li><a href="<%=path%>/graph/personalManage.jsp">人员管理</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-align-left"></em>分组</h4>
                <ul class="menu-ul">
                    <li><a href="graph/groupManage.jsp">分组管理</a></li>
                </ul>
            </li>
        </ul>
    </div>
    
    
    
    <div class="admin-op-panel">
        <div class="panel-content">
        	<div class="customForm">
	   	   <div class="leftForm">

               <%--<div class="operate">--%>
                   <%--<div class="block">--%>
                       <%--<div class="name noselect">类型</div>--%>
                       <%--<div class="circle lightblue"></div>--%>
                   <%--</div>--%>
                   <%--<div class="button lightblue noselect">--%>
                       <%--<div class="center noselect">--%>
                           <%--<button type="button"  id="preview" class="btn btn-default noselect" >预览</button>--%>
                           <%--<button type="button"  id="submit" class="btn btn-primary noselect" >提交</button>--%>
                       <%--</div>--%>
                   <%--</div>--%>
               <%--</div>--%>

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

                   <div class="option_button">
                       <div class="block">
                                <div class="name noselect">选项</div>
                                <div class="circle midblue"></div>
                       </div>
                       <div class="button_group midblue noselect">

                           <div class="center noselect">
                               <div class="btn-group btn-group-lg">
                                   <button type="button" class="btn btn-primary" data-type="1">录入</button>
                                   <button type="button" class="btn btn-default"  data-type="2">投票</button>
                               </div>
                           </div>

                           <div class="expire noselect">
                               <div class="dateOption" >
                                   <div class="check"><input type="checkbox" id="cp" checked="true"/><label for="cp">不设置任何期限</label></div>
                                   <div class="input-group date form_datetime col-md-5" data-date="" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                                       <input id="deadline" class="form-control" size="16" type="text" value="" readonly>
                                       <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                                       <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                   </div>
                               </div>
                          </div>

                       </div>
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
<input type="hidden" id="base_path" value="<%=path%>"/>
<input type="hidden" id="formToken" value="${formToken}"/>
<footer>
    <p class="copyright text-center noselect">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>
</body>
</html>