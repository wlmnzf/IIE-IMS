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
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
    <script src="<%=path%>/resources/jquery-1.9.1.min.js"></script>
    <script src="<%=path%>/graph/js/jstreee.js"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(
            function () {
                $("#jstree_div") .jstree({
                        "core" : {
                            // so that create works
                            "check_callback" : true,
                            "animation" : 0,
                            "themes" : { "dots": true },
                            'data' :   function (obj, callback) {
                                var jsonstr="[]";
                                var jsonarray = eval('('+jsonstr+')');
                                $.ajax({
                                    type: "get",
                                    url:"<%=path%>/group/list.do",
                                    dataType:"json",
                                    async: false,
                                    success:function(datas) {
                                        for(var i=0 ; i<datas.length; i++){
                                            var arr = {
                                                "id":datas[i].id,
                                                "parent":datas[i].parent=="root"?"#":datas[i].parent,
                                                "text":datas[i].text
                                            };
                                            jsonarray.push(arr);
                                        }
                                    }

                                });
                                callback.call(this, jsonarray);
                            }
                        },
                        "types" : {
                            "#" : {
                                "max_depth" : 4
                            }
                        },
                        "state" : { "key" : "demo3" },
                        "plugins" : [ "contextmenu", "state", "types", "dnd"]
                    })
                .on('move_node.jstree', function(e,data){
                    moveNode(data);
                })
                    .on("load_node.jstree",function(e,d){
                    var nodes=d.node.children_d;
                    for(var i in nodes){
                        var node=d.instance.get_node(nodes[i]);
                        if(node.id.split("_")[0]=="3")
                            node.icon="jstree-file";
                    }
                });
            }
        );

        function moveNode(data) {
            var parentTable=data.parent.split("_")[0];
            if(parentTable!=data.old_parent.split("_")[0]){
                $("#jstree_div").jstree().refresh(true);
                return;
            }
            var parentId = data.parent.split("_")[1];
            var tempId=data.node.id;
            var id=tempId.split("_")[1];
            var table=tempId.split("_")[0];
            var params = {"id":id, "table":table, "parentId":parentId, "parentTable":parentTable};
            $.ajax({
                url:"<%=path%>/group/moveGroup",
                type:"get",
                dataType:'json',
                data:params,
                success : function(result) {
                    $(data.node).attr("id",result.id);
                    $("#jstree_div").jstree().refresh(true);
                }
            })
        }

    </script>
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
                    <li ><a href="javascript:void(0)">结果管理</a></li>
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
                    <li class="current"><a href="<%=path%>/graph/groupManage.jsp">分组管理</a></li>
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
