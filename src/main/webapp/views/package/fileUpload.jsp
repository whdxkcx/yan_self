<%--
  Created by IntelliJ IDEA.
  User: kcx
  Date: 2018/6/19
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_index.jsp" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>文件上传</title>
</head>
<body>
    <div id="main">
        <div id="toolbar">
            <a class="waves-effect btn btn-info btn-sm" href="javascript:actionType('add');"><i class="zmdi zmdi-plus"></i>
                导入excel文件</a>
        </div>
        <table id="table"></table>
    </div>


      <!-- 导入模板 -->
      <div id="addDialog" class="modal fade" aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="false">&times;
                    </button>
                    <h3 class="modal-title" id="myModalLabel">导入标准模板</h3>
                </div>
                <%--<form action="${pageContext.request.contextPath}/common/standard/fileUpLoadExcel"--%>
                <%--id="standardfileUpLoadExcel" method="post" enctype="multipart/form-data">--%>
                <form action="${pageContext.request.contextPath}/package/fileUpload" id="standardfileUpLoadExcel" method="post" enctype="multipart/form-data">
                    <div class="col-md-12" style="margin-top: 10px; margin-bottom: 10px;">
                        <div class="col-md-7"
                             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                            <label style="margin-top: 5px; font-size: 14px; color: grey;">导入表结构标准模板：</label>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <input type="file" id="StandardDbAndStandardTable" name="StandardDbAndStandardTable"
                                       style="display:block;" accept=".xls,.xlsx"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-md-12">
                            <button  class="btn btn-primary" id="wahaha"
                                     onclick="Excel()">保存
                            </button>
                            <button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

<script  type="text/javascript">
    var $table = $('#table');
    var treeObj;
    var userId;

    $(function() {
        $table.bootstrapTable({
            url : '${pageContext.request.contextPath}/common/standard/listStandardDb',
            method : 'post',
            dataType : "json",
            contentType : "application/x-www-form-urlencoded",
            singleSelect : true,
            pageSize : 15,// 每页的记录行数（*）
            sortable : true, //是否启用排序
            toolbar : '#toolbar',
            clickToSelect : true,// 点击行时选中
            detailView : true,// 显示详细页面模式
            detailFormatter : 'detailFormatter',// 详细页格式化
            pagination : true,// 显示分页条
            sidePagination : 'server',// 可选值为 client 或者 server
            search : true,
            queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
            classes : 'table table-hover',// table table-hover table-no-bordered
            paginationLoop : false,// 启用分页条无限循环的功能
            minimumCountColumns : 2,// 最小显示列数
            silentSort : false,// 点击分页按钮时，自动记住排序项
            smartDisplay : true,// 是否自动隐藏分页条
            escape : true,// 转义HTML字符串
            searchOnEnterKey : true,// 按回车触发搜索方法
            striped : true,// 隔行变色效果
            showRefresh : true,//是否显示 刷新按钮
            showColumns : true,//是否显示 内容列下拉框
            queryParams : queryParams,
            columns: [
                {field: 'state', checkbox: true},
                {field: 'dbName', title: '数据库名(中文)', align: 'center'},
                {field: 'tableName', title: '表名(中文)', align: 'center'},
                {field: 'updateType', title: '上传类型', align: 'center'},
                {field: 'mapNum', title: 'Map数量', align: 'center'},
                {field: 'splitColname', title: '字段名', align: 'center'},
                {field: 'enDbName', title: '数据库名(英文)', align: 'center'},
                {field: 'enTableName', title: '表名(英文)', align: 'center'},
                {field: 'ruleDate', title: '创建时间', align: 'center'},
                {
                    field: 'ifVaild', title: '是否有效', align: 'center', formatter: function (value, row, index) {
                        if (value) {
                            return '<span class="label label-info">正常</span>';
                        } else {
                            return '<span class="label label-danger">失效</span>';
                        }
                    }
                }

            ]
        });
        $('.form-control').attr('placeholder', '数据库名(中文)');
    });


    function queryParams(params) {
        var search = $(".form-control").val();
        return {
            offset : params.offset, //页码
            limit : params.limit, //页面大小pageSize
            search : search, //搜索
            order : params.order, //排序
            ordername : 'desc', //排序
        };
    }

    function refreshTable() {
        // $("#table").bootstrapTable('refresh', queryParams);
    }


    function Excel() {

        $('#addDialog').modal('hide');
        var x=document.getElementById("standardfileUpLoadExcel");
        x.submit();
        $.alert("上传文件成功！导入详细信息请查看下载文件！");
        x.target="_self";
        // refreshTable();
    }

    // 添加或者修改
    function actionType(type) {
            $('#StandardDbAndStandardTable').val(null);
            $('#typeAction').val(type);
            $('#addDialog').modal('show');
    }


</script>
</html>
