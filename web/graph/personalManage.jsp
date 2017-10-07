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
    <title>信息录入系统persontest</title>
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" >
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css">
    <link href="<%=path%>/graph/css/bootstrap/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <script src="<%=path%>/resources/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap-table.min.js"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap-table-export.js"></script>
    <script src="<%=path%>/graph/js/bootstrap/tableExport.js"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap-table-zh-CN.js"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js" ></script>

    <script type="text/javascript">
        function initTable() {
            //先销毁表格
             $('#personTable').bootstrapTable('destroy');
            //初始化表格,动态从服务器加载数据
            $("#personTable").bootstrapTable({
                method: "get",  //使用get请求到服务器获取数据
                dataType: "json",
                url: '<%=path%>/personal/list.do', //获取数据的Servlet地址
                striped: true,  //表格显示条纹
                pagination: true, //启动分页
                pageSize: 10,  //每页显示的记录数
                pageNumber:1, //当前第几页
//                pageList: [10, 15, 20, 25],  //记录数可选列表
                search: false,  //是否启用查询
                showColumns: true,  //自定义列显示
                showRefresh: true,  //显示刷新按钮
                sidePagination: "server", //表示服务端请求
                showExport: true,                     //是否显示导出
                exportDataType: "all",
                exportOptions:{
                    ignoreColumn: [0, 4]  //忽略某一列的索引
                },
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType : "undefined",
                queryParams: function queryParams(params) {   //设置查询参数
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    };
                    return param;
                },
                columns: [
                    {
                        field: 'state',
                        checkbox: true,
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: 'id',
                        field: "id",
                        align: 'center',
                        valign: 'middle'
                    },{
                        title: '序号',
                        field: 'recordId',
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: '姓名',
                        field: 'name',
                        align: 'center',
                        valign: 'middle'
                    },{
                        title: '组别',
                        field: 'group',
                        align: 'center',
                        valign: 'middle'
                    },{
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            var e = '<a href="#" onclick="personEdit(\''+ row.id + '\')">编辑</a> ';
                            var d = '<a href="#" onclick="personDel(\''+ row.id +'\')">删除</a> ';
                            return e+d;
                        }
                    }],
                onLoadSuccess: function(){  //加载成功时执行
                    alert("加载成功");
                },
                onLoadError: function(){  //加载失败时执行
                    alert("加载失败");
                }
            });
            $('#personTable').bootstrapTable('hideColumn', "id");
        }



        function personEdit(id) {
            $('#update').modal('show');
            getPersonInfo(id)
        }

        function personDel(id) {
            var xmlHttp;
            if(window.XMLHttpRequest){
                xmlHttp=new XMLHttpRequest();
            }else{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4 && xmlHttp.status==200){
                    initTable();
                }
            };
            xmlHttp.open("get", "/personal/delPerson.do?id="+id, false);
            xmlHttp.send();
        }

        function delSelected() {
            var rows = $('#personTable').bootstrapTable('getSelections');    //返回所有选择的行，当没有选择的记录时，返回一个空数组
            /*封装的表单提示确认框*/
            var ids = new Array();
            //遍历所有选择的行数据，取每条数据对应的ID
            $.each(rows, function(i, row) {
                ids[i] = row.id;
            });
            $.ajax({
                type : "get",
                url : "<%=path%>/personal/batchDelPerson.do?ids="+ids,
                success : function(data) {
                    if (data.status == "success")
                        alert("删除成功");
                    else
                        alert( "删除失败");
                    initTable();
                }
            });
        }

        function getRoom() {
            room.options.length=1;
            var xmlHttp;
            if(window.XMLHttpRequest){
                xmlHttp=new XMLHttpRequest();
            }else{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4 && xmlHttp.status==200){
                    var rooms=eval("("+xmlHttp.responseText+")");
                    for(var i=0;i<rooms.length;i++){
                        var o=rooms[i];
                        room.options.add(new Option(o.name,o.id));
                    }
                }
            };
            xmlHttp.open("get", "/personal/getRoom.do", false);
            xmlHttp.send();
        }

        function getGroup(roomId) {
            group.options.length=1;
            var xmlHttp;
            if(window.XMLHttpRequest){
                xmlHttp=new XMLHttpRequest();
            }else{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4 && xmlHttp.status==200){
                    var groups=eval("("+xmlHttp.responseText+")");
                    for(var i=0;i<groups.length;i++){
                        var o=groups[i];
                        group.options.add(new Option(o.name,o.id));
                    }
                }
            };
            xmlHttp.open("get", "/personal/getGroup.do?roomId="+roomId, false);
            xmlHttp.send();
        }

        function getPersonInfo(id) {
            var xmlHttp;
            if(window.XMLHttpRequest){
                xmlHttp=new XMLHttpRequest();
            }else{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4 && xmlHttp.status==200){
                    var dataObj=eval("("+xmlHttp.responseText+")");
                    getRoom();
                    getGroup(dataObj.roomId);
                    var ops = group.options;
                    for(var i=0;i<ops.length; i++){
                        if(ops[i].value == dataObj.groupId){
                            ops[i].selected = true;
                        }
                    }
                    ops = room.options;
                    for(var i=0;i<ops.length; i++){
                        if(ops[i].value == dataObj.roomId){
                            ops[i].selected = true;
                        }
                    }
                    $("#name").val(dataObj.name);
                    $("#num").val(dataObj.num);
                    $("#id").val(dataObj.id);
                }
            };
            xmlHttp.open("get", "/personal/getPerson.do?id="+id, false);
            xmlHttp.send();
        }

        function editPerson() {
            var xmlHttp;
            var id=$("#id").val();
            var name=$("#name").val();
            var num=$("#num").val();
            var groupId=$("#group").val();
            var roomId=$("#room").val();
            var url="<%=path%>/personal/editPerson.do?num="+num+"&name="+name+"&groupId="+groupId;
            if($("#id").val()){
                url=url+"&id="+id;
            }
            if(window.XMLHttpRequest){
                xmlHttp=new XMLHttpRequest();
            }else{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4 && xmlHttp.status==200){
                    $('#update').modal('hide');
                    initTable();
                }
            };
            xmlHttp.open("get", url, false);
            xmlHttp.send();
        }

        $(document).ready(function () {
            //调用函数，初始化表格
            initTable();

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



    <!-- 模态框（Modal） -->
    <div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">修改信息</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="id">
                    室：<select name="room" id="room" onchange="getGroup(this.options[this.options.selectedIndex].value)"></select>
                    组：<select name="group" id="group"></select>
                    姓名：<input type="text" name="name" id="name" />
                    学号：<input type="text" name="num" id="num" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="editPerson()">提交更改</button>
                </div>

            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
    <!-- 模态框（Modal）end -->

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
                    <%--<li><a href="<%=path%>/formResult">结果管理</a></li>--%>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-user"></em>人员</h4>
                <ul class="menu-ul">
                    <li class="current"><a href="<%=path%>/graph/personalManage.jsp">人员管理</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-align-left"></em>分组</h4>
                <ul class="menu-ul">
                    <li ><a href="<%=path%>/graph/groupManage.jsp">分组管理</a></li>
                </ul>
            </li>
        </ul>
    </div>



    <div class="admin-op-panel">
        <div class="panel-content">
            <div class="op-content">
                <table class="table table-hover" id="personTable"
                       data-pagination="true"
                       data-show-refresh="true"
                       data-show-toggle="true"
                       data-showColumns="true"
                       data-toolbar="#toolbar">
                </table>
                <div id="toolbar">
                    <div class="btn-group">
                        <button class="btn btn-default" onclick="personEdit();">
                            <i class="glyphicon glyphicon-plus"></i>
                        </button>
                        <button class="btn btn-default" onclick="delSelected()">
                            <i class="glyphicon glyphicon-trash"></i>
                        </button>
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
