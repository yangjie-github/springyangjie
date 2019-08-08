<%--
  User: yangjie
  Date: 2019/1/29
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp" %>
<html>
<head>
    <title>字典列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <script type="text/javascript">
        $(function () {

        });

        function deleteDic(href) {
            $("#deleteModal").modal('show');
            $("#confirmDeleteButton").attr("href", href);
        }
    </script>
    <style type="text/css">
        table {
            margin-top: 15px;
            font-size: 14px;
            text-align: center;
        }

        table th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请注意！</h4>
            </div>
            <div class="modal-body">
                <p>该条数据将会被删除</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <a type="button" id="confirmDeleteButton" class="btn btn-primary">确定</a>
            </div>
        </div>
    </div>
</div>
<div>
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="${ctx}/dictionary/list">菜单列表</a></li>
            <li role="presentation"><a href="${ctx}/dictionary/form">添加菜单</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <table class="table table-condensed table-bordered table-hover">
                    <thead>
                    <tr>
                        <th width="80px;">序号</th>
                        <th>字典值</th>
                        <th>字典类型</th>
                        <th>类型描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty list}">
                        <tr>
                            <td colspan="5">暂无数据</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty list}">
                        <c:forEach items="${list}" var="i" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${i.value}</td>
                                <td>${i.type}</td>
                                <td>${i.dicDescribe}</td>
                                <td>
                                    <%--<a href="${ctx}/dictionary/delete"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</a>&emsp;--%>
                                    <a href="javascript:" onclick="deleteDic('${ctx}/dictionary/delete?id=${i.id}')"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

