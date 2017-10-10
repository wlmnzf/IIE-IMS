<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 2017/9/21
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">
    <meta charset="utf-8"/>
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>投票结果</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/all-common.css">
    <link type="text/css" rel="stylesheet" href="css/user-vote.css">
    <script src="resources/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
            <h3 class="form-title">这周去哪里玩？</h3>
            <div class="inform-form">
                <div class="vote-result">
                    <div class="vote-choice">雁栖湖</div>
                    <div class="progress vote-progress">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">

                        </div>
                    </div>
                </div>
                <div class="vote-result">
                    <div class="vote-choice">密云水库</div>
                    <div class="progress vote-progress">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">

                        </div>
                    </div>
                </div>
                <div class="vote-result">
                    <div class="vote-choice">青龙峡</div>
                    <div class="progress vote-progress">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">

                        </div>
                    </div>
                </div>
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