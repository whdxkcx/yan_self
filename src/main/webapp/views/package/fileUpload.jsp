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
    <div id="main" >
        <div  style="margin-top: 5px">
            <a class="waves-effect btn btn-info btn-sm" href="javascript:actionType('add');" style="margin-top: 0px"><i class="zmdi zmdi-plus"></i>
                导入excel文件</a>
        </div>
    </div>




      <!-- 导入模板 -->
      <div id="addDialog" class="modal fade" aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="false">&times;
                    </button>
                    <h3 class="modal-title" id="myModalLabel">导入Excel文件</h3>
                </div>
                <%--<form action="${pageContext.request.contextPath}/common/standard/fileUpLoadExcel"--%>
                <%--id="standardfileUpLoadExcel" method="post" enctype="multipart/form-data">--%>
                <form action="${pageContext.request.contextPath}/package/fileUpload" id="standardfileUpLoadExcel" method="post" enctype="multipart/form-data">
                    <div class="col-md-12" style="margin-top: 10px; margin-bottom: 10px;">
                        <div class="col-md-7"
                             style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                            <label style="margin-top: 5px; font-size: 14px; color: grey;">导入Excel文件：</label>
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



  //删除表格
    function  deleteExcel() {
        $('#deleteDialog').modal('hide');
        var x=document.getElementById("deletestandardfileUpLoadExcel");
        x.submit();
        $.alert("删除文件成功！");
        x.target="_self"
    }


    function Excel() {

        $('#addDialog').modal('hide');

        var x=document.getElementById("standardfileUpLoadExcel");
        x.submit();
        $.alert("上传文件成功！");
        x.target="_self";
    }

    // 添加或者修改
    function actionType(type) {
            $('#StandardDbAndStandardTable').val(null);
            $('#typeAction').val(type);
            $('#addDialog').modal('show');
    }


</script>
</html>
