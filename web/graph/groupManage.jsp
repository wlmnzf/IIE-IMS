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
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>信息录入系统</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
    <script src="<%=path%>/resources/jquery-1.9.1.min.js"></script>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>--%>
    <script src="<%=path%>/graph/js/jstree.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#jstree_div").jstree({
                "core" : {
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
                "state" : { "key" : "demo3" },
                "plugins" : [ "contextmenu", "state"],
            }).bind("create_node.jstree",function(event,data){
                addNode(event,data);
            }) .bind("rename_node.jstree",function(event,data){
                renameNode(event,data);
            }).bind("delete_node.jstree",function(event,data){
                removeNode(event,data);
            });


        });



        function addNode(event,data){
            var parentId = data.parent.replace(/[a-z]/g,"");
            var parentTable=data.parent.replace(/[0-9]/g,"");
            var name = data.node.text;
            var params = {"parentId":parentId,"name":name,"parentTable":parentTable};
            $.ajax({
                url:"/group/addGroup",
                type:"get",
                dataType:'json',
                data:params,
                success : function(result) {
                    $(data.node).attr("id",result.id);
                    $("#jstree_div").jstree().refresh(true);
                }
            })
        }

        function renameNode(event,data) {
            var tempId = data.node.id;
            var id = tempId.replace(/[a-z]/g,"");
            var table=tempId.replace(/[0-9]/g,"");
            var name = data.text;
            var params = {"id":id,"name":name, "table":table};
            $.ajax({
                'url':'/group/editGroup',
                'type':'get',
                'dataType':'json',
                'data':params,
                success : function() {
                    $(data.node).attr("text",name);
                }
            })
        }

        function removeNode(event,data) {
            var tempId = data.node.id;
            var id = tempId.replace(/[a-z]/g,"");
            var table=tempId.replace(/[0-9]/g,"");
            var params = {"id":id, "table":table};
            $.ajax({
                'url':"/group/delGroup",
                'dataType':"json",
                'data':params,
                'timeout':1000*10
            }).done(function(result){
                if(result.ok==false) {
                    alert("不能删除");
                }
            })

        }


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
