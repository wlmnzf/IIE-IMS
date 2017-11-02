<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 2017/9/21
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">
    <meta charset="utf-8"/>
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>信息录入页面</title>
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/all-common.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/user-vote.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <script src="<%=path%>/graph/js/clientInfo.js"></script>
    <script src="<%=path%>/graph/js/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/fileinput/fileinput.min.css" />
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/clientinfo.css" />
    <script type="text/javascript" src="<%=path%>/graph/js/fileinput/fileinput.min.js"></script>
    <script type="text/javascript" src="<%=path%>/graph/js/fileinput/locales/zh.js"></script>

</head>
<body>
<header>
    <div class="container-fluid">
        <div class="navbar-header">
            <img class="brand" src="<%=path%>/img/logo.png" alt="">
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right login-ul">
                <li>
                    <div class="login-info">
                        <div class="media-left media-top">
                            <a href="#">
                                <img class="media-object" src="${_HEAD_URL}" alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading" data-type="${_TYPE}"><span class="label label-primary">${_TYPE_TEXT}</span></h4>
                            <p class="login-name" data-token="${_TOKEN}" data-username="${_USER_NAME}">${_NAME}</p>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="options">
                        <%--<a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>--%>
                        <a class="option" id="exit" href="<%=path%>/exit">退出</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>



    <div class="user-collapse">
        <div class="container-fluid">
            <div class="inform">
                <div class="user-welcome">
                    <strong>welcome,</strong><span   data-token="${_TOKEN}" data-username="${_USER_NAME}">${_NAME}</span>
                </div>
                <div class="options">
                    <a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>
                    <a class="option" href="<%=path%>/exit">退出</a>
                </div>
            </div>
        </div>
    </div>
</header>



<div class="admin-content clearfix">
    <div class="admin-op-list">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
                <i class="glyphicon glyphicon-th-list"></i>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="main-menu" aria-expanded="false">
            <ul class="menu">
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-tags"></em>公告</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/announceShow">公告</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>信息录入</h4>
                    <ul class="menu-ul">
                        <li><a href=""  >信息录入</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-user"></em>个人信息</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/clientInfo"  class="active">个人信息</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="admin-op-panel">
        <div class="panel-content">
            <h3 class="form-title">个人信息</h3>
            <div class="inform-form">
                <form action="<%=path%>/alterInfo">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="stuHead">头像</label>
                            <%--<input type="text" class="form-control"  name="stuHead"  id="stuHead" placeholder="路径" value="${stuHead}">--%>
                            <input type="file" name="stuHead" class="file-3"  id="stuHead" value="${stuHead}"/>
                        </div>
                        <div class="form-group">
                            <label for="stuName">姓名</label>
                            <input type="text" class="form-control" name="stuName" id="stuName" placeholder="姓名" disabled value="${stuName}">
                        </div>
                        <div class="form-group">
                            <label for="stuNumber">学号</label>
                            <input type="text" class="form-control" name="stuNumber" id="stuNumber" placeholder="学号" disabled value="${stuNumber}">
                        </div>
                        <div  id ="password" class="form-group">
                            <label for="stuPassword">密码修改</label>
                            <input type="password" class="form-control" name="stuPassword" id="stuPassword" placeholder="请输入原始密码">
                            <input type="password" class="form-control" name="stuNewPassword" id="stuNewPassword" placeholder="请输入新密码">
                            <input type="password" class="form-control" name="stuReNewPassword" id="stuReNewPassword" placeholder="请重新输入新密码">
                        </div>

                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <p class="copyright text-center">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>
<input type="hidden" id="basepath"  value="<%=path%>"/>

</body>
</html>