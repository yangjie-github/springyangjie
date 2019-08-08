<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>videos</title>
    <%@ include file="/WEB-INF/views/include/jspHeader.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <script type="text/javascript">
        $(function () {
            $("#deleteForm").validate({
                submitHandler: function (form) {
                    disableBtnInMills($(form).find("button[type='submit']"));
                    yjPost("${ctx}/video/delete?id=" + $("#deleteVideoId").val(), null, function (resp) {
                        showTip(resp.msg);
                        window.location.reload(true);
                    })
                }
            })
        });

        function uploadVideo() {
            $("#uploadVideoModal").modal('show');
        }

        function uploadFiles() {
            disabled();
            if (!$("#videoName").val()) {
                able();
                alert("请填写视频名称");
                return;
            }
            if (!$("#files").val()) {
                able();
                alert("请选择文件");
                return;
            }
            var formData = new FormData();
            var file = $("#files")[0].files[0];
            if (!/.(rm|rmvb|mpeg1-4|mov|mtv|dat|wmv|avi|3gp|amv|dmv|flv|mp4|mkv|mog)$/.test(file.name)) {
                able();
                alert("视频类型必须是rm|rmvb|mpeg1-4|mov|mtv|dat|wmv|avi|3gp|amv|dmv|flv|mp4|mkv|mog中的一种;");
                return;
            }
            formData.append("files", file);
            formData.append("name", $("#videoName").val());
            $.ajax({
                url: '${ctx}/video/upload',
                type: 'post',
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                async: true,
                dataType: 'json',
                success: function (resp) {
                    if (resp.code === 100) {
                        alert("上传成功");
                    } else {
                        alert("上传失败");
                    }
                    window.location.href = "${ctx}/video/list";
                }
            })
        }

        function disabled() {
            var upload = $("#upload");
            upload.prop("disabled", true);
            upload.text("正在上传");
        }

        function able() {
            var upload = $("#upload");
            upload.prop("disabled", false);
            upload.text("上传");
        }

        function showVideo(href) {
            $("#videoId").prop("src", href);
            $("#videoModal").modal("show");
        }

        function deleteVideo(param) {
            $("#deleteVideoId").val(param);
            $("#deleteModal").modal('show');
        }
    </script>
    <style type="text/css">
        video {
            object-fit:fill;
            width:100%;
            height:100%;
        }
    </style>
</head>
<body>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <form id="deleteForm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">删除视频</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" id="deleteVideoId">
                    <p>此视频将被删除</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal fade bs-example-modal-lg" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="videoModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="videoModalLabel">视频</h4>
                </div>
                <div class="modal-body">
                    <video id="videoId" controls="controls"></video>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="uploadVideoModal" tabindex="-1" role="dialog" aria-labelledby="uploadVideoModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="uploadForm" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h5 class="modal-title" id="myModalLabel">上传视频</h5>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input style="margin-bottom: 5px;" type="text" required class="form-control" id="videoName" placeholder="请输入视频名称">
                        <input class="form-control" required type="file" name="files" id="files">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="upload" onclick="uploadFiles()" class="btn btn-primary">上传</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">JiaJieLove&nbsp;&nbsp;</a>
                <c:if test="${empty username}">
                    <a class="navbar-brand" href="${ctx}/user/loginForm">未登录&nbsp;点击登录</a>
                </c:if>
                <c:if test="${not empty username}">
                    <a class="navbar-brand" href="#">当前用户:&nbsp;${username}</a>
                </c:if>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${ctx}/photo/list">图片</a></li>
                    <li><a href="${ctx}/music/list">音乐<span class="sr-only">(current)</span></a></li>
                    <li class="active"><a href="${ctx}/video/list">视频</a></li>
                    <li><a href="#">文档</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${ctx}/user/index">首页</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container-fluid">
    <c:if test="${not empty username}">
        <button style="margin-bottom: 10px;" onclick="uploadVideo()" class="btn btn-sm btn-primary btn-block">上传视频</button>
    </c:if>
    <div class="row">
        <c:forEach items="${videoList}" var="i">
            <div class="col-sm-3 col-md-3 col-xs-6" style="float: left;">
                <div class="thumbnail">
                    <img onclick="showVideo('${ctx}/video/readVideo?id=${i.id}')" class="img-responsive img-thumbnail" src="${ctx}/video/readVideoImg?id=${i.id}" alt="暂无"/>
                    <div class="caption">
                        <h6><fmt:formatDate value="${i.createDate}" pattern="yyyy.MM.dd"/></h6>
                        <h6>视频名称：${i.name}</h6>
                            <%--<p>...</p>--%>
                        <shiro:hasPermission name="photo:delete">
                            <p><a href="javascript:" onclick="deleteVideo('${i.id}')" class="btn btn-sm btn-danger" role="button">删除</a></p>
                        </shiro:hasPermission>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
