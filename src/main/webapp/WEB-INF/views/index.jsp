<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
<html>
<head>
    <title>jiaJieLove</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <script type="text/javascript">
        $(function () {
           $(".collapsed").click(function () {
               var cls = $(this).find("span");
               var icon = cls.attr("class");
               var right = "glyphicon glyphicon-chevron-right";
               var down = "glyphicon glyphicon-chevron-down";
               $(".panel-title").find("span").attr("class", right);
               if (icon === down) {
                    cls.attr("class", "glyphicon glyphicon-chevron-right")
               } else {
                   cls.attr("class", down)
               }
           })
        });

        function uploadPhoto() {
            if (!$("#userName").val()) {
                alert("请先登录/注册");
            } else {
                $("#uploadPhotoModal").modal('show');
            }
        }

        function uploadFiles() {
            if (!$("#files").val()) {
                alert("请选择文件");
                return;
            }
            if (!$("#photoName").val()) {
                alert("请填写照片名称");
                return;
            }
            var formData = new FormData();
            var files = $("#files")[0].files;
            for (var i = 0; i < files.length; i++) {
                if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(files[i].name)) {
                    alert("图片类型必须是.gif,jpeg,jpg,png中的一种;");
                    return;
                }
                formData.append("files", files[i]);
            }
            formData.append("photoName", $("#photoName").val());
            $.ajax({
                url: '${ctx}/photo/save',
                type: 'post',
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                async: false,
                dataType: 'json',
                success: function (resp) {
                    if (resp.code === 100) {
                        alert("上传成功，可点击照片查看查看");
                        window.location.href = "${ctx}/user/index";
                    } else {
                        alert("上传失败");
                    }
                }
            })
        }

        function showPhoto() {
            window.location.href = "${ctx}/photo/list";
        }

        function showMusic() {
            window.location.href = "${ctx}/music/list";
        }

        function showVideo() {
            window.location.href = "${ctx}/video/list";
        }

        function toIframe(href) {
            window.top.frames['mainBody'].location.href = href;
        }
    </script>
    <style type="text/css">

        .navbar-inverse {
            margin-bottom: 2px;
        }

        .panel-body {
            padding: 0;
        }

        .list-group {
            margin: 0;
        }
    </style>
</head>
<body>
<sys:htmlEcho message="${message}"/>
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
                <a class="navbar-brand" href="#">jiaJieLove</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li class="active"><a href="#">图片</a></li>--%>
                    <%--<li><a href="#">歌曲</a></li>--%>
                    <%--<li><a href="#">文档</a></li>--%>
                    <%--<li><a href="#">联系开发者</a></li>--%>
                <%--</ul>--%>
                <%--<form class="navbar-form navbar-left">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" placeholder="Search">--%>
                    <%--</div>--%>
                    <%--<button type="button" class="btn btn-default">查询</button>--%>
                <%--</form>--%>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="javascript:">${userName}, 欢迎您！</a></li>
                    <li><a href="${ctx}/user/loginOut">退出</a></li>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="#">Action</a></li>--%>
                            <%--<li><a href="#">Another action</a></li>--%>
                            <%--<li><a href="#">Something else here</a></li>--%>
                            <%--<li role="separator" class="divider"></li>--%>
                            <%--<li><a href="#">Separated link</a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="col-md-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading" role="tab" id="headingOne">--%>
                        <%--<h4 class="panel-title">--%>
                            <%--<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">--%>
                                <%--<span class="glyphicon glyphicon-chevron-right">&emsp;</span>用户管理--%>
                            <%--</a>--%>
                        <%--</h4>--%>
                    <%--</div>--%>
                    <%--<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">--%>
                        <%--<div class="panel-body">--%>
                            <%--<div class="list-group">--%>
                                <%--<a href="#" class="list-group-item">Cras justo odio</a>--%>
                                <%--<a href="#" class="list-group-item">Dapibus ac facilisis in</a>--%>
                                <%--<a href="#" class="list-group-item">Morbi leo risus</a>--%>
                                <%--<a href="#" class="list-group-item">Porta ac consectetur ac</a>--%>
                                <%--<a href="#" class="list-group-item">Vestibulum at eros</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <span class="glyphicon glyphicon-chevron-right">&emsp;</span>菜单管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:" onclick="toIframe('${ctx}/dictionary/list')" class="list-group-item">solr菜单管理</a>
                                <%--<a href="#" class="list-group-item">Dapibus ac facilisis in</a>--%>
                                <%--<a href="#" class="list-group-item">Morbi leo risus</a>--%>
                                <%--<a href="#" class="list-group-item">Porta ac consectetur ac</a>--%>
                                <%--<a href="#" class="list-group-item">Vestibulum at eros</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <span class="glyphicon glyphicon-chevron-right">&emsp;</span>文件管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:" onclick="toIframe('${ctx}/file/list')" class="list-group-item">查看文件</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10" style="margin: 0;">
            <div id="contain-frame">
                <!--allowtransparency是否透明-->
                <iframe frameborder="no" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" width="100%" height="90%" id="mainBody" name="mainBody"></iframe>
            </div>
        </div>
    </div>
</div>
</body>
</html>