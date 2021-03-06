<%--
  Created by IntelliJ IDEA.
  User: liuzhilei
  Date: 2017/9/10
  Time: 下午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8"/>
    <title>编辑成功</title>

    <link rel="stylesheet" href="/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="/graph/css/admin.css">
    <link rel="stylesheet" href="/graph/css/bootstrap/bootstrap-theme.min.css" />

    <script src="/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="/graph/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript"   src="/graph/js/submit.js"></script>
    <script type="text/javascript"   src="/graph/js/customForm_h5.js"></script>
    <script type="text/javascript"   src="/graph/s/jquery.sticky.js"></script>
    <script type="text/javascript"   src="/graph/js/layer/layer.js"></script>

    <link rel="stylesheet" href="/graph/css/customForm_h5.css"/>
</head>
<body>
    <header>
        <div class="container-fluid">
            <div class="navbar-header">
                <img class="brand" src="/graph/img/logo.png" alt="">
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right login-ul">
                    <li>
                        <div class="login-info">
                            <div class="media-left media-top">
                                <a href="#">
                                    <img class="media-object" src="/graph/img/head.jpg" alt="...">
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
                        <li><a href="announceMagement">公告管理</a></li>
                        <li><a href="announceEditor">公告编辑</a></li>
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

        <div class="admin-op-panel" style="margin-left: 5px">
            <div class="panel-content">
                <div class="customForm">
                    <div class="leftForm">
                        <div style="margin-left: 100px">
                            <center style="margin-top: 150px"><img style="width: 100px;height: 150px;" src="/graph/img/rocket.png"></center>
                            <center><h3>Edit Success!</h3></center>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


</body>
</html>
