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
    <script  src="${pageContext.request.contextPath}/resources/plugins/bootstrap-fileinput-js/fileinput.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/plugins/bootstrap-fileinput-css/fileinput.min.css" rel="stylesheet">
    <script  src="${pageContext.request.contextPath}/resources/plugins/bootstrap-fileinput-js/locales/zh.js"></script>
    <title>Title</title>
</head>
<body>
<form  action="/package/fileUpload">
   <input id="f_upload" type="file" />
</form>
</body>
</html>
