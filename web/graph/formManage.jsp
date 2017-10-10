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
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/FormManage.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<%=path%>/graph/js/formmanage.js"></script>
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
                    <li class="current"><a href="<%=path%>/formManage">页面定制</a></li>
                    <%--<li><a href="<%=path%>/formResult">结果管理</a></li>--%>
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
            <div class="op-buttons">
                <button class="btn btn-default" id="delAll">删除</button>
                <button class="btn btn-default" id="addNew">新增</button>
            </div>
            <div class="op-content">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><input class="all" name="event" type="checkbox" value="" /></th>
                        <th>序号</th>
                        <th>标题</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr  data-sh="1">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>1</td>
                        <td>
                            <p><span class="event-title"><a href="#"></a></span><span class="label"></span></p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="2">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>2</td>
                        <td>
                            <p><span class="event-title"><a href="#"></a></span><span class="label"></span></p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="3">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>3</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label"></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="4">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>4</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label"></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="5">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>5</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label "></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="6">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>6</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label "></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="7">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>7</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label "></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="8">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>8</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label "></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="9">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>9</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label"></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>
                    <tr data-sh="10">
                        <th scope="row"><input name="event" type="checkbox" value="" /></th>
                        <td>10</td>
                        <td>
                            <p>
                                <span class="event-title"><a href="#"></a></span>
                                <span class="label "></span>
                            </p>

                        </td>
                        <td class="time"></td>
                        <td>
                            <a class="op-choice" name="preview" href="javascript:;">预览</a>
                            <a class="op-choice" name="del" href="javascript:;">删除</a>
                        </td>
                    </tr>

                    </tbody>
                </table>
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
</body>
</html>