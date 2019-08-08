<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp"%>
<%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>用户注册</title>
    <script type="text/javascript">
        $(function () {
            $("#registerForm").validate({
                rules: {
                    loginName: {loginNameExist: true},
                    loginRePassword: {equalTo: "#passWord"},
                    email: {checkEmailUnique: true}
                },
                submitHandler: function(form) {
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
                    flag = resp.length <= 0 ;
                }
            });
            return this.optional(element) || flag;
        }, "该用户名已经存在");

        jQuery.validator.addMethod("checkEmailUnique", function (value, element) {
            var flag = false;
            $.ajax({
                async: false,
                url: "${ctx}/user/checkEmailUnique?email=" + $("#email").val(),
                type: "POST",
                success: function(resp){
                    flag = resp.length <= 0 ;
                }
            });
            return this.optional(element) || flag;
        }, "此邮箱已经注册");

        function getRandomNumCode(param) {
            var emailReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
            var email = $("#email").val();
            if (!email) {
                showTip("请输入邮箱号");
            } else if (!emailReg.test(email)) {
                showTip("邮箱格式不正确");
            } else {
                yjPost("${ctx}/user/sendEmailCode?emailTo=" + email, null, function (resp) {
                    if (resp) {
                        setTime(param);
                    }
                })
            }
        }

        function setTime(param){
            var oBtn = $(param);
            var time = 60;
            var timer = null;
            oBtn.text(time + '秒后重新发送');
            oBtn.prop('disabled', true);
            timer = setInterval(function(){
                if(time === 1){
                    clearInterval(timer);
                    oBtn.text('获取邮箱验证码');
                    oBtn.prop('disabled', false);
                }else{
                    time--;
                    oBtn.text(time + '秒后重新发送');
                }
            }, 1000)
        }
    </script>
</head>
<style type="text/css">

</style>
<body style="background:black;">
<script color="255,255,255" opacity="0.8" count="50" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.js"
        type="text/javascript" charset="utf-8">
</script>
<div class="container">
    <div class="row" style="margin-top: 10%">
        <div class="col-md-4 col-md-offset-4">
            <h2>用户注册</h2>
            <form id="registerForm" action="${ctx}/user/register" method="post">
                <div class="form-group">
                    <label for="loginName">请输入登录名:</label>
                    <input class="form-control" value="${user.loginName}" required name="loginName" id="loginName" onblur="verifyLoginName(this)" placeholder="请输入登录名">
                </div>
                <div class="form-group">
                    <label for="passWord">请输入密码</label>
                    <input type="password" required class="form-control" name="passWord" id="passWord" placeholder="请输入登录密码">
                </div>
                <div class="form-group">
                    <label for="loginRePassword">请确认密码</label>
                    <input type="password" required class="form-control" name="loginRePassword" id="loginRePassword" placeholder="请再次输入登录密码">
                </div>
                <div class="form-group">
                    <label for="email">请输入邮箱号</label>
                    <input type="text" value="${user.email}" required onblur="verifyEmail(this)" class="form-control" name="email" id="email" placeholder="请输入邮箱号">
                    <button style="margin-top: 3px;" type="button" onclick="getRandomNumCode(this)" class="btn btn-xs btn-info">获取邮箱验证码</button>
                </div>
                <div class="form-group">
                    <label for="emailPassword">请输入邮箱验证码</label>
                    <input type="text" required class="form-control" name="emailPassword" id="emailPassword" placeholder="请输入邮箱验证码">
                </div>
                <div class="form-group">
                    <label for="phone">请输入手机号</label>
                    <input type="text" value="${user.phone}" class="form-control" name="phone" id="phone" placeholder="请输入手机号">
                </div>
                <div class="center-block">
                    <button type="submit" class="btn btn-primary btn-block">注册</button>
                    <a href="${ctx}/user/loginForm" class="btn btn-default btn-block">返回</a>
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
