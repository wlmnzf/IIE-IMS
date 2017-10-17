<%--
  Created by IntelliJ IDEA.
  User: liuzhilei
  Date: 2017/9/7
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8"/>
    <title>公告编辑</title>

    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css">
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap-theme.min.css" />

    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/submit.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/customForm_h5.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/jquery.sticky.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/layer/layer.js"></script>

    <script type="text/javascript" src="<%=path%>/resources/jquery-form.js"></script>

    <link rel="stylesheet" href="<%=path%>/graph/css/customForm_h5.css"/>


    <%
        request.setCharacterEncoding("UTF-8");
        String htmlData = request.getParameter("content1") != null ? request.getParameter("text") : "";
    %>
    <meta charset="utf-8" />
    <title>KindEditor JSP</title>
    <link rel="stylesheet" href="<%=path%>/kindEditor/themes/default/default.css" />
    <link rel="stylesheet" href="<%=path%>/kindEditor/plugins/code/prettify.css" />
    <script charset="utf-8" src="<%=path%>/kindEditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="<%=path%>/kindEditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="<%=path%>/kindEditor/plugins/code/prettify.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="text"]', {
                cssPath : '../plugins/code/prettify.css',
                uploadJson : '../jsp/upload_json.jsp',
                fileManagerJson : '../jsp/file_manager_json.jsp',
                allowFileManager : true,
                afterCreate : function() {
                    var self = this;
                    K.ctrl(document, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();
        });
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
                    <li  class="current"><a href="<%=path%>/announceEditor">公告编辑</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                <ul class="menu-ul">
                    <li ><a href="<%=path%>/formManage">页面定制</a></li>
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



        <div class="admin-op-panel" style="margin-left: 5px">
            <div class="panel-content">
                <div class="customForm">
                    <div class="leftForm">


                        <div>
                         <%--<form id="anc_form" method="post" action="<%=path%>/addAnc">--%>
                             <form id="anc_form">

                                <div class="form-group">
                                    <label for="anc_title" class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <input style="width: 700px" type="text" class="form-control" id="anc_title" name="title" placeholder="请输入标题">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="anc_text" class="col-sm-2 control-label" style="padding-top: 10px">正文</label>
                                    <div class="col-sm-10">
                                        <textarea id="anc_text" class="form-control" style="width: 700px;height: 500px"  name="text"><%=htmlspecialchars(htmlData)%></textarea>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4">
                                        <div style="margin-left: 15px" >
                                            <div class="checkbox form-group" id="anc_level" name="level" aria-checked="true">
                                                <label style="color: red">
                                                     <p style="color: #00bcd4">程度</p>
                                                </label>
                                                <label style="color: red">
                                                    <input type="radio" name="checkbox" value="紧急"> 紧急
                                                </label>
                                                <label style="color: cornflowerblue">
                                                    <input type="radio" name="checkbox" value="普通" checked="true"> 普通
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div style="margin-left: 15px" >
                                            <div class="checkbox form-group" id="anc_type" name="type" aria-checked="true">
                                                <label style="color: red">
                                                    <p style="color: #00bcd4">类型</p>
                                                </label>
                                                <label style="color: red">
                                                    <input type="radio" name="checkbox1" value="活动"> 活动
                                                </label>
                                                <label style="color: cornflowerblue">
                                                    <input type="radio" name="checkbox1" value="通知" checked="true"> 通知
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4" style="padding-top: 10px">
                                        <center>
                                        <select class="selectpicker form-control" id="anc_access" name="groupid">
                                            <option id="op1" value="0">公开</option>
                                            <option id="op2" value="1">第一组可见</option>
                                            <option id="op3" value="2">第二组可见</option>
                                            <option id="op4" value="3">第三组可见</option>
                                            <option id="op4" value="3">第三组可见</option>
                                        </select>
                                        </center>
                                    </div>

                                </div>


                                <div style="margin-top: 20px;padding-left: 15px">
                                    <%--<button style="width: 80px" type="submit" class="btn btn-info" onclick="ancSubmit()">发布</button>--%>
                                    <button style="width: 80px" class="btn btn-info" onclick="ancSubmit()">发布</button>
                                </div>
                            </form>

                             <script>
                                 function ancSubmit(){
                                     alert("come in");
                                     var options = {
                                         url:"<%=path%>/addAnc",
                                         type:"POST",
                                         success:function (data) {
                                             alert(data);
                                             $("#modal").modal();
                                         },
                                         error:function () {
                                             alert("wrong");
                                         }
                                     };
                                     $("#anc_form").submit(function () {
                                         $(this).ajaxSubmit(options);
                                         return false;

                                     });
                                 }
                             </script>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提交成功啦！</h4>
            </div>
            <div class="modal-body">
                <center><img style="width: 100px;height: 150px;padding-top: 10px" src="/graph/img/rocket.png"></center>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="refresh()">Close</button>
                <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
            </div>
            <script>
                function refresh() {
                    window.location.reload();
                }
            </script>
        </div>
    </div>
</div>

<script>

</script>

</body>
</html>

<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>
