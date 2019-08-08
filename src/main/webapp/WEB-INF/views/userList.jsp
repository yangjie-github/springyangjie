<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <script type="text/javascript">
        // <!-- ajax直接发送PUT请求时，只会带上路径上的数据，
        //     tomcat ：put请求体中的数据不会被tomcat封装，,只会封装url上的参数，
        //              get会将请求的参数带在url上
        //              post会将请求体中的数据封装为map
        //     例如：$.ajax({
        //                 url:'',
        //                 type:"PUT",
        //                 data:"",
        //                 success:function(){
        //
        //                 }
        //
        //             })
        //     解决办法：1.将type改为post，再将url后面加上method=put。
        //             2.在web.xml中添加一个过滤器HttpPutFormContentFilter -->

        function test(){
            alert($("#contain").html());
            $.ajax({
                url:"/User/test",
                type:"post",
                traditional:true,
                success:function(data){
                    $("#contain").empty().append(data);
                }
            })
        }
    </script>
</head>
<body>
<div class="container">
    <div id="contain" style="width: 500px;height: 500px;background-color:#2aabd2;">
        dajiahao
    </div>
    <div>
        <a href="/sysUserLogin/loginOut" class="btn btn-primary">退出</a>
        <shiro:hasRole name="user">
            <h1><a href="javascript:" onclick="test()">有user角色</a></h1>
        </shiro:hasRole>
        <shiro:hasRole name="admin">
            <h1>有admin角色</h1>
        </shiro:hasRole>
        <shiro:hasPermission name="user:add">
            <h1>有user：add权限</h1>
        </shiro:hasPermission>
        <a href="/admin.jsp" class="btn btn-primary">admin</a>
        <a href="/user.jsp" class="btn btn-primary">user</a>
        <a href="/list.jsp" class="btn btn-primary">list</a>
    </div>
    <div style="text-align: center">
        <h3>SSM-CRUD
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </h3>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>id</th>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="page">
                    <tr>
                        <td>${page.id}</td>
                        <td>${page.name}</td>
                        <td>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-2">
            当前第${pageInfo.pageNum}页，总${pageInfo.pages}页，总${pageInfo.total}条记录
        </div>
        <div class="col-md-4 col-md-offset-2">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="/user/allUser?pn=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="/user/allUser?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="item">
                        <c:if test="${item == pageInfo.pageNum}">
                            <li class="active"><a href="#">${item}</a></li>
                        </c:if>
                        <c:if test="${item != pageInfo.pageNum}">
                            <li><a href="/user/allUser?pn=${item}">${item}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="/user/allUser?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <li><a href="/user/allUser?pn=${pageInfo.pages}">末页</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="row"></div>
</div>
</body>
</html>
