<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>信息录入系统</title>
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.css" >
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/all-common.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/index.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.js" ></script>
    <script src="<%=path%>/graph/js/index.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/jquery.md5.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/layer/layer.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/jquery.cookie.js" type="text/javascript"></script>
    <!--<script type="text/javascript" src="js/index.js"></script>-->
</head>
<body>
<div class="alert alert-warning alert-dismissible" role="alert" style="    margin-bottom: 10px;">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>注意:</strong> 学生端： stu    123456      管理员端：  admin   123456
</div>
<header>
    <div class="container-fluid">
        <div class="navbar-header">
            <img class="brand" src="<%=path%>/img/logo.png" alt="">
        </div>
    </div>
</header>
<div class="banner-content clearfix">
    <div class="container banner-container">
        <div class="col-md-10">

        </div>
        <div class="login-box col-sm-6 col-md-3">
            <h3>登录</h3>
            <form  method="post" action="<%=path%>/Login">
                <div class="form-group">
                    <div class="help-block with-errors"></div>
                    <div class="input-group">
                        <label class="input-group-addon" for="login_name">
                            <span class="glyphicon glyphicon-user"></span>
                        </label>
                        <input type="text" id="account" class="form-control" data-login-name=1 required id="login_name" name="login_name" placeholder="请输入账号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="help-block with-errors"></div>
                    <div class="input-group">
                        <label class="input-group-addon" for="password">
                            <span class="glyphicon glyphicon-lock"></span>
                        </label>
                        <input type="password" id="password" class="form-control" data-password=1 required id="password" name="password" placeholder="请输入密码">
                    </div>
                </div>
                <p>
                    <button type="submit"  data-loading-text="登录中..." class="btn  btn-inline btn-right btn-primary j-submit" id="submit" name="submit">登 录</button>
                </p>
            </form>
        </div>
    </div>
</div>
<div class="inform-content">
    <div class="container">
        <ul class="nav nav-tabs cate-tabs" role="tablist">
            <li role="presentation"><a href="#informs" aria-controls="home" role="tab" data-toggle="tab">紧急</a></li>
            <li role="presentation" class="active"><a href="#activities" aria-controls="profile" role="tab" data-toggle="tab">时间</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="informs">
                <div class="inform-block inform-left col-sm-12 col-md-6">
                    <h3 class="title">通知</h3>
                    <ul>
                        <li>
                            <p class="clearfix">
                                <span class="label label-warning inform-label">置顶</span>
                                <span class="inform-name"><a href="#">计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                        <li>
                            <p class="clearfix">
                                <span class="label label-warning inform-label">置顶</span>
                                <span class="inform-name"><a href="#">计算机系统结构需</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                        <li>
                            <p class="clearfix">
                                <span class="label label-warning inform-label">置顶</span>
                                <span class="inform-name"><a href="#">计算机系统结构需要一些人计算机系</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                    </ul>
                </div>
                <div class="inform-block inform-right col-sm-12 col-md-6">
                    <h3 class="title">活动</h3>
                    <ul>
                        <li>
                            <p class="clearfix">
                                <span class="label label-warning inform-label">置顶</span>
                                <span class="inform-name"><a href="#">计算机系统结构一些人</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                        <li>
                            <p class="clearfix">
                                <span class="label label-danger inform-label">置顶</span>
                                <span class="inform-name"><a href="#">结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                    </ul>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="activities">
                <div class="inform-block inform-left col-sm-12 col-md-6">
                    <h3 class="title">通知</h3>
                    <ul>
                        <li>
                            <p class="clearfix">
                                <span class="label label-danger inform-label">置顶</span>
                                <span class="inform-name"><a href="#">计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                    </ul>
                </div>
                <div class="inform-block inform-right col-sm-12 col-md-6">
                    <h3 class="title">活动</h3>
                    <ul>
                        <li>
                            <p class="clearfix">
                                <span class="label label-danger inform-label">置顶</span>
                                <span class="inform-name"><a href="#">结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人计算机系统结构需要一些人</a></span>
                                <span class="inform-time">2017-9-8</span>
                            </p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>


        <!--<div class="btn-tab btn-group" role="group" aria-label="...">-->
        <!--<button type="button" class="btn btn-default">时间</button>-->
        <!--<button type="button" class="btn btn-default">紧急</button>-->
        <!--</div>-->
    </div>
</div>
<footer>
    <p class="copyright text-center">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>

<input type="hidden" id="basepath" value="<%=path%>"/>
</body>
<script>
    $('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
</script>
</html>

<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge" />--%>
    <%--<meta charset="utf-8"/>--%>
    <%--&lt;%&ndash;<link rel="Shortcut Icon" href="<%=path%>/graph/images/logo.jpg" type="image/x-icon">&ndash;%&gt;--%>
    <%--<title>信息录入系统</title>--%>

<%--</head>--%>


<%--<body>--%>
<%--</body>--%>
<%--<a href="<%=path%>/formManage">表单管理页面</a> </br>--%>
<%--<a href="<%=path%>/graph/personalManage.jsp">人员管理页面</a> </br>--%>
<%--<a href="<%=path%>/graph/groupManage.jsp">分组管理页面</a>--%>
<%--</html>--%>
