<%--
  User: yangjie
  Date: 2019/1/12
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp" %>
<html>
<head>
    <title>文件</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <script type="text/javascript">
        $(function () {

            $("#previousPage").click(function () {
                var page = $("#startPage").val();
                $("#startPage").val(-- page);
                $("#searchForm").submit();

            });

            $("#nextPage").click(function () {
                var page = $("#startPage").val();
                $("#startPage").val(++ page);
                $("#searchForm").submit();

            });

            $(".fileRead").hover(
                function () {
                    $(this).css("background-color","black");
                }, function () {
                    $(this).css("background-color", "");
            });

            $(".deleteSpan").click(function (event) {
                event.stopPropagation();
                deleteConfirm("您确定删除此项索引吗?", function (b) {
                    if (b) {
                        window.location.href = "${ctx}/SolrJ/deleteById?id=" + $(event.target).attr('index');
                    }
                });
            });
        });

        function uploadFiles() {
            disabled();
            if (!$("#files").val()) {
                able();
                alert("请选择文件");
                return;
            }
            var formData = new FormData();
            var files = $("#files")[0].files;
            for (var i = 0; i < files.length; i++) {
                if (!/.(pdf|txt|doc|docx)$/.test(files[i].name)) {
                    able();
                    alert("音乐类型必须是.pdf,txt,doc,docx;");
                    return;
                }
                formData.append("files", files[i]);
                formData.append("type", $("#type").val());
                formData.append("name", $("#name").val());
            }
            $.ajax({
                url: '${ctx}/file/add',
                type: 'post',
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                async: false,
                dataType: 'json',
                success: function (resp) {
                    if (resp.code === 100) {
                        alert("上传成功");
                    } else {
                        alert("上传失败");
                    }
                    window.location.href = "${ctx}/file/list";
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

        function readFile(href) {
            window.open(href);
        }

        function getFileName(id, obj) {
            yjPost("${ctx}/file/findNameById?id=" + id, null, function (resp) {
                $(obj).prop("title", resp.name);
            })
        }

        function selectByValue(value) {
            $("#searchContent").val(value);
            $("#searchForm").submit();
        }
    </script>
    <style type="text/css">
        #uploadSpan {
            cursor: pointer;
        }

        body {
            background-image: url('${ctx}/photo/readBackGroundImg');
            background-position: bottom;
            background-repeat: no-repeat;
            background-size: 100%;
        }

        .deleteSpan:hover {
            background-color: red;
        }
    </style>
</head>
<body>
<div class="modal fade" tabindex="-1" role="dialog" id="uploadModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">上传文件</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="type">请填写文件类型</label>
                        <select class="form-control" name="type" id="type">
                            <c:forEach items="${fileTypes}" var="i">
                                <option value="${i.value}">${i.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="name">请填写文件描述</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="请填写文件描述">
                    </div>
                    <div class="form-group">
                        <label for="files">请选择文件</label>
                        <input class="form-control" type="file" id="files">
                        <span style="color: red;">注意： 暂不支持上传中文文件名称！</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" onclick="uploadFiles()" class="btn btn-primary">上传</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2" style="margin-top: 3%;">
            <form id="searchForm" action="${ctx}/file/list">
                <input type="hidden" class="form-control" id="startPage" name="startPage" value="${startPage}">
                <div class="input-group">
                    <span id="uploadSpan" class="input-group-addon" data-toggle="modal" data-target="#uploadModal">分享文档</span>
                    <input type="text" id="searchContent" class="form-control" name="content" value="${content}" placeholder="Search for...">
                    <span class="input-group-btn">
                        <button id="searchButton" class="btn btn-default" type="submit">Go!</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
    <div class="col-md-8 col-md-offset-2">
        <span style="color: grey;">分类：</span>
        <c:forEach items="${fileTypes}" var="i">
            <span style="color: #00B83F;">[</span><a style="color: whitesmoke;" href="javascript:" onclick="selectByValue('${i.value}')">${i.value}</a><span style="color: #00B83F;">]</span>&emsp;
        </c:forEach>
    </div>
    </div>
    <hr>
    <sys:htmlEcho message="${message}"/>
    <div class="container">
        <c:forEach items="${fileList}" var="file">
            <c:forEach items="${file.value}" var="field">
                <a href="javascript:" onmouseover="getFileName('${file.key}', this)" onclick="readFile('${ctx}/file/readFile?id=${file.key}')">
                    <p class="fileRead">${fnc:substring(field.value, 0, 200)} <span class="deleteSpan" index="${file.key}" style="color: whitesmoke; float: right">删除</span></p>
                </a>
            </c:forEach>
        </c:forEach>
    </div>
    <c:if test="${not empty fileList}">
        <div class="container" style="text-align: center;">
            <nav aria-label="Page navigation">
                <ul class="pager">
                    <li <c:if test="${startPage == 1}">class="disabled"</c:if>>
                        <a href="javascript:" <c:if test="${startPage != 1}">id="previousPage"</c:if>>Previous</a>
                    </li>
                    <li <c:if test="${startPage == total}">class="disabled"</c:if>>
                        <a href="javascript:" <c:if test="${startPage != total}">id="nextPage"</c:if>>Next</a>
                    </li>
                    <li style="color: white;">当前页: ${startPage} , 共 ${total} 页。</li>
                </ul>
            </nav>
        </div>
    </c:if>
    <c:if test="${not empty content && empty fileList}">
        <div class="container">
            <h6 style="color: white;">无查询结果</h6>
        </div>
    </c:if>
</div>
</body>
</html>

