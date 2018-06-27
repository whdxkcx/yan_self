<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_common.jsp" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>金额查询</title>
</head>
<body>
<div id="main">

    <%--<div class="col-md-3"--%>
    <%--style="margin-top: 0px; margin-bottom: 0px;">--%>
    <%--<div class="col-md-5"--%>
    <%--style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">--%>
    <%--<label style="margin-top: 5px; font-size: 14px; color: grey;">查询类型：</label>--%>
    <%--</div>--%>
    <%--<div class="col-md-3">--%>
    <%--<div class="form-group">--%>
    <%--<select id="type" name="type" class="selectpicker" onchange="onChang()" style="width: 10px">--%>
    <%--<option value="name" selected="selected">姓名</option>--%>
    <%--<option value="yuliu" >预留</option>--%>
    <%--</select>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="col-md-3"
         style="margin-top: 0px; margin-bottom: 0px;">
        <div class="col-md-5"
             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
            <label style="margin-top: 5px; font-size: 14px; color: grey;">年份：</label>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <select id="year" name="year" class="selectpicker" onchange="onChang()">
                    <option value="all" selected="selected">全部</option>
                    <option value="2015" >2015</option>
                    <option value="2016" >2016</option>
                    <option value="2017" >2017</option>
                    <option value="2018" >2018</option>
                    <option value="2019" >2019</option>
                    <option value="2020" >2020</option>
                    <option value="2018" >2021</option>
                    <option value="2019" >2022</option>
                    <option value="2020" >2023</option>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-3"
         style="margin-top: 0px; margin-bottom: 0px;">
        <div class="col-md-5"
             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
            <label style="margin-top: 5px; font-size: 14px; color: grey;">月份：</label>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <select id="month" name="month" class="selectpicker" onchange="onChang()">
                    <option value="all" selected="selected">全部</option>
                    <option value="1" >1</option>
                    <option value="2" >2</option>
                    <option value="3" >3</option>
                    <option value="4" >4</option>
                    <option value="5" >5</option>
                    <option value="6" >6</option>
                    <option value="7" >7</option>
                    <option value="8" >8</option>
                    <option value="9" >9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>

        </div>
    </div>
    <div class="col-md-3"
         style="margin-top: 0px; margin-bottom: 0px;">
        <div class="col-md-5"
             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
            <label style="margin-top: 5px; font-size: 14px; color: grey;">姓名：</label>
        </div>
        <div class="col-md-7">
            <div class="form-group">
                <input type="text" id="name" name="name" class="form-control" placeholder="姓名(选填)"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <div class="col-md-12">
            <button type="button" class="btn btn-primary"
                    onclick="amountQuery()">查询</button>
        </div>
    </div>

    <table id="table"></table>
</div>

</body>

<script type="text/javascript">
    var $table = $('#table');
    var treeObj;
    var userId;

    $(function() {
        $table.bootstrapTable({
            url : '${pageContext.request.contextPath}/packageQuery/queryByCondition',
            method : 'post',
            dataType : "json",
            contentType : "application/x-www-form-urlencoded",
            singleSelect : true,
            pageSize : 15,// 每页的记录行数（*）
            sortable : true, //是否启用排序
            toolbar : '#toolbar',
            clickToSelect : true,// 点击行时选中
            detailView : false,// 显示详细页面模式
            detailFormatter : 'detailFormatter',// 详细页格式化
            pagination : true,// 显示分页条
            sidePagination : 'server',// 可选值为 client 或者 server
            search : false,
            queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
            classes : 'table table-hover',// table table-hover table-no-bordered
            paginationLoop : false,// 启用分页条无限循环的功能
            minimumCountColumns : 2,// 最小显示列数
            silentSort : false,// 点击分页按钮时，自动记住排序项
            smartDisplay : true,// 是否自动隐藏分页条
            escape : true,// 转义HTML字符串
            searchOnEnterKey : true,// 按回车触发搜索方法
            striped : true,// 隔行变色效果
            showRefresh : false,//是否显示 刷新按钮
            showColumns : false,//是否显示 内容列下拉框
            queryParams : queryParams,
            columns: [
                {
                    field: 'agentname',
                    title: '姓名',
                    align: 'center'
                }, {
                    field: 'amount',
                    title: '金额',
                    align: 'center'
                }, {
                    field: 'year',
                    title: '年份',
                    align: 'center'
                }, {
                    field: 'month',
                    title: '月份',
                    align: 'center'
                }]
        });
        $(".pull-right input").attr('placeholder', "姓名");
    });

    function queryParams(params) {
        var ids = new Array();
        var type = $('#type').val();
        var year = $('#year').val();
        var month = $('#month').val();
        var name = $('#name').val();
        ids.push(type);
        ids.push(year);
        ids.push(month);
        ids.push(name);
        return {
            offset : params.offset, //页码
            limit : params.limit, //页面大小pageSize
            search : ids.join("-"), //搜索
            order : params.order, //排序
            ordername : 'desc', //排序
        };
    }

    function refreshTable() {
        $("#table").bootstrapTable('refresh', queryParams);
    }
    function onChang(){
        refreshTable();
    }

    function amountQuery() {
        refreshTable();
    }

</script>

</html>