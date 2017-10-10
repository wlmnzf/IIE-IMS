
<%--
  Created by IntelliJ IDEA.
  User: liuzhilei
  Date: 2017/10/10
  Time: 下午11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">
    <meta charset="utf-8"/>
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>公告详情</title>

    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/all-common.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/user-inform.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <!--<script type="text/javascript" src="js/index.js"></script>-->
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
                        <li><a href="<%=path%>/inManage">信息录入</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="admin-op-panel" style="margin-left: 5px">
        <div class="panel-content">
            <div class="customForm">
                <div class="leftForm">
                    <div>
                        <center>
                            <h3>${c_anc.title}</h3>
                        </center>
                    </div>
                    <div>
                        <p>${c_anc.text}</p>
                    </div>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
