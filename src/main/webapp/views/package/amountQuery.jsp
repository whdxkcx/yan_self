<%--
  Created by IntelliJ IDEA.
  User: kcx
  Date: 2018/6/21
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_index.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>金额查询</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/package/fileUpload" id="standardfileUpLoadExcel" method="post" enctype="multipart/form-data">
    <div class="col-md-12" style="margin-top: 10px; margin-bottom: 10px;">
        <div class="col-md-7"
             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
            <label style="margin-top: 5px; font-size: 14px; color: grey;">查询报酬：</label>
        </div>
        <br>
         <br>
        <form  calss="form-query">
        <select class="selectpicker" >
            <option>选择查询条件</option>
            <option>Ketchup</option>
            <option>Relish</option>
        </select>
        <select class="selectpicker" >
            <option>选择年份</option>
            <option>Ketchup</option>
            <option>Relish</option>
        </select>
            <select class="selectpicker" >
                <option>选择月份</option>
                <option>Ketchup</option>
                <option>Relish</option>
            </select>

        <label>  <input type="text" class="form-control" placeholder=""></label>
        </form>
    </div>
    <div class="modal-footer">
        <div class="col-md-12">
            <button  class="btn btn-primary" id="wahaha"
                     onclick="queryRecord()">查询
            </button>
            <button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
        </div>
    </div>
</form>
</body>

<script type="text/javascript">
    function queryRecord(){
         var data=$('')
    }

</script>
</html>
