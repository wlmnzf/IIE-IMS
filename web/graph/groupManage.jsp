<%--
  Created by IntelliJ IDEA.
  User: qiuye
  Date: 2017/9/5
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>信息录入系统</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="/graph/css/admin.css">
    <script src="/resources/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/graph/js/bootstrap/jstree.min.js"></script>
    <script src="/graph/js/bootstrap/jstree.style.min.css"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#jstree_div").jstree({
                "core" : {
                    "themes" : {
                        "responsive": false
                    },
                    // so that create works
                    "check_callback" : true,
                    'data' :   function (obj, callback) {
                        var jsonstr="[]";
                        var jsonarray = eval('('+jsonstr+')');
                        $.ajax({
                            type: "get",
                            url:"/group/list.do",
                            dataType:"json",
                            async: false,
                            success:function(datas) {
                                for(var i=0 ; i<datas.length; i++){
                                    var arr = {
                                        "id":datas[i].id,
                                        "parent":datas[i].parent=="root"?"#":datas[i].parent,
                                        "text":datas[i].text
                                    }
                                    jsonarray.push(arr);
                                }
                            }

                        });
                        callback.call(this, jsonarray);
                    }
                },
                "types" : {
                    "default" : {
                        "icon" : "fa fa-folder icon-state-warning icon-lg"
                    },
                    "file" : {
                        "icon" : "fa fa-file icon-state-warning icon-lg"
                    }
                },
                "state" : { "key" : "demo3" },
                "plugins" : [ "dnd", "state", "types" ]
            });
        });
    </script>
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
                    <li><a href="">公告管理</a></li>
                    <li><a href="">公告编辑</a></li>
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
    <div class="admin-op-panel">
        <div class="panel-content">
            <div id="jstree_div"></div>
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
