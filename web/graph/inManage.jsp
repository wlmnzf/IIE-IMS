<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 2017/9/21
  Time: 17:37
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
                                <img class="media-object" src="img/head.jpg" alt="...">
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
    <div class="user-collapse">
        <div class="container-fluid">
            <div class="inform">
                <div class="user-welcome">
                    <strong>welcome,</strong><span>会码代码的扫地王大爷</span>
                </div>
                <div class="options">
                    <a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>
                    <a class="option" href="#">退出</a>
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
                        <li><a href="">公告</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>信息录入</h4>
                    <ul class="menu-ul">
                        <li><a href="">信息录入</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="admin-op-panel">
        <div class="panel-content">
            <h3 class="form-title">个人信息统计</h3>
            <div class="inform-form">
                <form action="">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="stuName">姓名</label>
                            <input type="text" class="form-control" id="stuName" placeholder="姓名">
                        </div>
                        <div class="form-group">
                            <label for="stuNumber">学号</label>
                            <input type="text" class="form-control" id="stuNumber" placeholder="学号">
                        </div>
                        <div class="form-group">
                            <label for="stuTeacher">导师</label>
                            <input type="text" class="form-control" id="stuTeacher" placeholder="导师">
                        </div>
                        <div class="form-group">
                            <label for="stuTeacher">个人简介</label>
                            <textarea class="form-control" id="stuIntro" rows="3" placeholder="这个人比较懒哦~"></textarea>
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
</body>
</html>
