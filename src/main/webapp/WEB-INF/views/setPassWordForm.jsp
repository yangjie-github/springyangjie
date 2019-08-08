<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp"%>
<html>
<head>
    <title>jiajielove</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
    <script type="text/javascript">
        $(function() {
            $("#setPassWord").validate({
                rules: {
                    loginRePassword: {equalTo: "#passWord"}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            })
        })
    </script>
    <style type="text/css">

    </style>
</head>
<body style="background:black;">
<script color="255,255,255" opacity="0.8" count="50" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.js"
        type="text/javascript" charset="utf-8">
</script>
<div class="container">
    <div class="row" style="margin-top: 20%">
        <div class="col-md-4 col-md-offset-4">
            <h2>重置密码</h2>
            <form id="setPassWord" action="/user/setPassWord" method="post">
                <div class="form-group">
                    <label for="passWord">请输入新密码</label>
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="password" required class="form-control" name="passWord" id="passWord" placeholder="请输入登录密码">
                </div>
                <div class="form-group">
                    <label for="loginRePassword">请再次输入新密码</label>
                    <input type="password" required class="form-control" name="loginRePassword" id="loginRePassword" placeholder="请再次输入登录密码">
                </div>
                <div class="center-block">
                    <button type="submit" class="btn btn-primary btn-block">保存</button>
                    <a href="${ctx}/user/index" class="btn btn-primary btn-block">首页</a>
                </div>
                <div>
                    <c:if test="${error != null}">
                        <h6 style="color: red;">${error}</h6>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
