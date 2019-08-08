<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>上传文件</title>
    <script type="text/javascript">
        function uploadFiles() {
            var formData = new FormData();
            var files = $("#files")[0].files;
            for (var i = 0; i < files.length; i++) {
                formData.append("files", files[i]);
            }
            $.ajax({
                url: '${ctx}/mylife/upload/img/uploadImg',
                type: 'post',
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                async: false,
                dataType: 'json',
                success: function (resp) {
                    if (resp.code === 100) {
                        alert("上传成功，可在我的照片中查看");
                        window.location.reload(true);
                    } else {
                        alert("上传失败");
                    }
                }
            })
        }
    </script>
</head>
<style type="text/css">
    #uploadForm {
        margin: 15px auto;
    }
    .form-control {
        padding: 0;
    }
</style>
<body>
<div class="container-fluid">
    <div style="text-align: center;">
        <form class="form-inline" id="uploadForm" action="${ctx}/mylife/upload/img/uploadImg" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="files">请选择照片：</label>
                <input class="form-control" type="file" name="files" id="files" multiple="multiple">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>
                <button type="button" onclick="uploadFiles()" class="btn btn-sm btn-primary">上传</button>
            </div>
        </form>
        <form action="/activiti/create" method="post">
            <button type="submit">工作流</button>
            <a href="/activiti/modeler/modelList">模型列表</a>
        </form>
    </div>
    <hr>
    <div class="row">
        <c:forEach items="${imgList}" var="i" varStatus="s">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <img src="${ctx}/mylife/download/img/downloadImg?imgId=${i.id}" alt="无法显示" style="height: 220px;" class="img-rounded">
                    <div class="caption">
                        <h4>第${s.count}张:美好的图片都是大家的回忆</h4>
                        <p>
                            <a href="${ctx}/mylife/download/img/downloadImg?imgId=${i.id}" class="btn btn-sm btn-primary" role="button">下载原图</a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
