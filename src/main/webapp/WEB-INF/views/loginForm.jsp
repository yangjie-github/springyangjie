<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp"%>
<html>
<head>
    <title>jiaJieLove</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
    <script type="text/javascript">
        $(function() {
            $("#loginForm").validate({
                rules: {
                    loginName: {loginNameExist: true}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            })
        });

        jQuery.validator.addMethod("loginNameExist", function (value, element) {
            var flag = false;
            $.ajax({
                async: false,
                url: "${ctx}/user/checkLoginNameUnique?loginName=" + $("#loginName").val(),
                type: "POST",
                success: function(resp){
                    flag = resp.length > 0 ;
                }
            });
            return this.optional(element) || flag;
        }, "该用户名不存在");
    </script>
    <style type="text/css">
        .jumbotron {
            height: 100%;
            margin-bottom: 0;
            position: relative;
        }

        #ICP {
            position: absolute;
            right: 40px;
            bottom: 10px;
        }
    </style>
</head>
<body style="background:black;" style="height: 100%;">
<sys:htmlEcho message="${message}"/>
<!--
    color=”255,0,0” 背景粒子线的颜色值
    opacity=”0.5” 背景粒子线的透明度，一般设置成0.5-1之间
    count=”99” 背景粒子线的密度，建议不要太大，否则页面容易卡死
-->
<script color="255,255,255" opacity="0.8" count="50" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.js"
        type="text/javascript" charset="utf-8">
</script>
<div class="container" style="height: 100%;">
    <div class="jumbotron">
        <h1>Hello, java!</h1>
        <div class="row" style="margin-top: 10%;">
            <div class="col-md-4 col-md-offset-4">
                <form id="loginForm" action="${ctx}/user/login" method="post">
                    <div class="form-group">
                        <label for="loginName">登录名:</label>
                        <input class="form-control" required name="loginName" id="loginName" value="${user.loginName}" placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">密码</label>
                        <input type="password" required class="form-control" name="loginPassword" id="loginPassword" placeholder="请输入登录密码">
                    </div>
                    <div class="center-block">
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                        <a href="${ctx}/user/registerForm" class="btn btn-default btn-block">还没有账号？注册</a>
                        <a href="${ctx}/user/resetPassWordAuthenForm" class="btn btn-default btn-block">忘记密码?</a>
                    </div>
                    <div>
                        <c:if test="${error != null}">
                            <h6 style="color: red;">${error}</h6>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
        <div>
            <div id="ICP" style="float: right;">
                <p><a href="http://www.miitbeian.gov.cn" style="font-size: 14px;">陕ICP备18018994号-1</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
