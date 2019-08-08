<%--
  User: yangjie
  Date: 2019/1/29
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <script type="text/javascript">
        $(function () {
            $("#saveForm").validate({
                submitHandler: function (form) {
                    form.submit();
                }
            })
        });
    </script>
    <style type="text/css">
        #saveForm {
            padding: 40px;
            margin-top: 15px;
            margin-left: auto;
            margin-right: auto;
            width: 600px;
            box-shadow: 10px 10px 5px black;
            border: 3px solid whitesmoke;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div style="text-align: center;">
        <h3>字典值添加</h3>
    </div>
    <form action="${ctx}/dictionary/add" id="saveForm">
        <div class="form-group">
            <label for="value">字典值</label>
            <input type="text" name="value" required class="form-control" id="value" placeholder="请输入字典值">
        </div>
        <div class="form-group">
            <label for="type">字典类型</label>
            <input type="text" name="type" required class="form-control" id="type" placeholder="请输入字典类型">
        </div>
        <div class="form-group">
            <label for="dicDescribe">字典描述</label>
            <input type="text" name="dicDescribe" required class="form-control" id="dicDescribe" placeholder="请输入字典描述">
        </div>
        <div style="text-align: center;">
            <button type="submit" class="btn btn-sm btn-primary">保存</button>
            <button type="button" class="btn btn-sm btn-default" onclick="window.history.go(-1)">返回</button>
        </div>
    </form>
</div>
</body>
</html>

