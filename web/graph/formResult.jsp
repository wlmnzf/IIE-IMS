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
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/FormResult.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<%=path%>/graph/js/formresult.js"></script>
    <script type="text/javascript" src="<%=path%>/graph/js/layer/layer.js"></script>
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
</header>



<div class="admin-content clearfix">
    <div class="admin-op-list">
        <ul class="menu">
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-tags"></em>公告</h4>
                <ul class="menu-ul">
                    <li><a href="<%=path%>/announceManagement">公告管理</a></li>
                    <li><a href="<%=path%>/announceEditor">公告编辑</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                <ul class="menu-ul">
                    <li ><a href="<%=path%>/formManage">页面定制</a></li>
                    <li class="current"><a href="javascript:void(0)">结果管理</a></li>
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
                    <li><a href="<%=path%>/graph/groupManage.jsp">分组管理</a></li>
                </ul>
            </li>
        </ul>
    </div>


    <div class="admin-op-panel">
        <div class="panel-content">
            <div class="page-header">
                <h3>今天中午要吃什么 </h3><span class="label"></span>
            </div>
            <div class="op-buttons">
                <div class="dropdown">
                    <a class="btn btn-default dropdown-toggle"  data-toggle="dropdown" id="correct">校对<b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">


                    </ul>
                </div>

                <button class="btn btn-default" id="export">导出</button>
                <button class="btn btn-default" id="delAll">删除</button>
            </div>
            <div class="op-content">
                <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><input class="all" name="event" type="checkbox" value="" /></th>
                        <th>序号</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr  data-sh="1">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="2">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>
                    </tr>
                    <tr data-sh="3">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="4">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="5">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="6">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="7">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="8">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="9">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

                    </tr>
                    <tr data-sh="10">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td></td>

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
                        <li class="active"><a href="">1</a></li>
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
        </div>
    </div>
</div>
<footer>
    <p class="copyright text-center">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>
<input type="hidden" id="formToken" value="${formToken}"/>
<input type="hidden" id="cur" value="${curPage}"/>
<input type="hidden" id="base_path" value="<%=path%>"/>
<input type="hidden" id="type" value="${type}"/>
<input type="hidden" id="types" value='${types}'/>
<input type="hidden" id="json" value='${json}'/>
<input type="hidden" id="title" value="${title}"/>
<input type="hidden" id="needCheck" value="${needCheck}"/>
<input type="hidden" id="checkOption" value='${checkOption}'/>
</body>
</html>