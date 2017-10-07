
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
    <title>信息录入系统</title>

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
                        <li><a href="<%=path%>/announceShow"  class="active">公告</a></li>
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

    <div class="admin-op-panel">
        <div class="panel-content">
            <ul class="nav nav-tabs cate-tabs" role="tablist">
                <li role="presentation"><a href="#urgent" aria-controls="profile" role="tab" data-toggle="tab">紧急</a></li>
                <li role="presentation" class="active"><a href="#time" aria-controls="home" role="tab" data-toggle="tab">时间</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="time">
                    <ul class="nav nav-tabs underline-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#informs-1" aria-controls="home" role="tab" data-toggle="tab">通知</a></li>
                        <li role="presentation"><a href="#activities-1" aria-controls="profile" role="tab" data-toggle="tab">活动</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="informs-1">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td><span class="label label-warning">置顶</span></td>
                                        <td>
                                            <p><a href="user-input.html"><span class="event-title">个人信息统计</span></a></p>
                                        </td>
                                        <td>张慧慧</td>
                                        <td>2017-08-01</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="activities-1">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td><span class="label label-danger">置顶</span></td>
                                        <td>
                                            <p><a href=""><span class="event-title">计算机系统结构需要1</span></a></p>
                                        </td>
                                        <td>张慧慧</td>
                                        <td>2017-08-01</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="urgent">
                    <ul class="nav nav-tabs underline-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#informs-2" aria-controls="home" role="tab" data-toggle="tab">通知</a></li>
                        <li role="presentation"><a href="#activities-2" aria-controls="profile" role="tab" data-toggle="tab">活动</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="informs-2">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td><span class="label label-warning">置顶</span></td>
                                        <td>
                                            <p><a href=""><span class="event-title">计算机系统结构需要2</span></a></p>
                                        </td>
                                        <td>张慧慧</td>
                                        <td>2017-08-01</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="activities-2">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td><span class="label label-danger">置顶</span></td>
                                        <td>
                                            <p><a href=""><span class="event-title">计算机系统结构需要2</span></a></p>
                                        </td>
                                        <td>张慧慧</td>
                                        <td>2017-08-01</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

            <!--<div class="op-content">-->
            <!--<table class="table table-striped">-->
            <!--<tbody>-->
            <!--<tr>-->
            <!--<td><span class="label label-warning">置顶</span></td>-->
            <!--<td>-->
            <!--<p><span class="event-title">计算机系统结构需要</span></p>-->
            <!--</td>-->
            <!--<td>张慧慧</td>-->
            <!--<td>2017-08-01</td>-->
            <!--</tr>-->
            <!--<tr>-->
            <!--<td><span class="label label-warning">置顶</span></td>-->
            <!--<td>-->
            <!--<p><span class="event-title">计算机系统结构需要</span></p>-->
            <!--</td>-->
            <!--<td>张慧慧</td>-->
            <!--<td>2017-08-01</td>-->
            <!--</tr>-->
            <!--</tbody>-->
            <!--</table>-->
            <!--<nav aria-label="Page navigation">-->
            <!--<ul class="pagination">-->
            <!--<li>-->
            <!--<a href="#" aria-label="Previous">-->
            <!--<span aria-hidden="true">&laquo;</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li class="active"><a href="#">1</a></li>-->
            <!--<li><a href="#">2</a></li>-->
            <!--<li><a href="#">3</a></li>-->
            <!--<li><a href="#">4</a></li>-->
            <!--<li><a href="#">5</a></li>-->
            <!--<li>-->
            <!--<a href="#" aria-label="Next">-->
            <!--<span aria-hidden="true">&raquo;</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--</ul>-->
            <!--</nav>-->
            <!--</div>-->
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
