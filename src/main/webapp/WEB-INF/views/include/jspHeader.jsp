<%@ page contentType="text/html;charset=UTF-8" %>

<!-- 引入jquery 和 jquery-validate -->
<script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
<%--<script src="${ctx}/static/js/jquery.js"></script>--%>
<script src="${ctx}/static/js/jquery.validate.js"></script>

<!-- 引入 bootstrap -->
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<!-- 引入ztree -->
<script src="${ctx}/static/ztree/ztree-js/jquery.ztree.all.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/static/ztree/ztree-style/zTreeStyle/zTreeStyle.css"/>

<%--<script src="${ctx}/static/js/tooltip.js"></script>--%>

<!-- 引入自己的css -->
<link type="text/css" rel="stylesheet" href="${ctx}/static/myCss.css" charset="UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/validate.css" charset="UTF-8"/>

<script type="text/javascript">
    jQuery.validator.messages = {
        required: "这是必填字段",
        remote: "请修正此字段",
        email: "请输入有效的电子邮件地址",
        url: "请输入有效的网址",
        date: "请输入有效的日期",
        dateISO: "请输入有效的日期 (YYYY-MM-DD)",
        number: "请输入有效的数字",
        digits: "只能输入数字",
        creditcard: "请输入有效的信用卡号码",
        equalTo: "你两次输入不一致",
        extension: "请输入有效的后缀",
        maxlength: $.validator.format("最多可以输入 {0} 个字符"),
        minlength: $.validator.format("最少要输入 {0} 个字符"),
        rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
        range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
        max: $.validator.format("请输入不大于 {0} 的数值"),
        min: $.validator.format("请输入不小于 {0} 的数值"),
        ignore: ""
    };

    function disableBtnInMills(btnSelector, mills, info) {
        var bt = $(btnSelector);
        var msg = (!!info) ? info : "提交中...";
        var to = (!!mills) ? mills : 3000;
        var text = bt.text();

        bt.prop("disabled", true).text(msg);
        setTimeout(function () {
            bt.prop("disabled", false).text(text);
        }, to);
    }

    function yjPost(url, data, fun) {
        if (!url) {
            alert("请求地址有误");
        } else {
            if (typeof fun !== 'function') {
                alert("返回参数不是一个函数");
            } else {
                $.ajax({
                    async: true,
                    url: url,
                    type: "post",
                    data: data,
                    dataType: "json",
                    contentType: "application/json",
                    success: fun
                })
            }
        }
    }

    function showTip(tip) {
        $("#tipContent").text(tip);
        $("#showTip").modal("show");
    }

    function deleteConfirm(title, func) {

        var flag = true;

        if(typeof func !== 'function'){
            alert("参数 func 不是一个 function，请联系开发人员。");
            return ;
        }

        $("#content_title_modal_p").text(title);
        $("#my_title_modal").modal('show');

        $("#modal_confirm_button").click(function () {

            if (flag) {
                func(true);
                flag = false;
            }

            $("#my_title_modal").modal('hide');
        });
        $("#modal_cancel_button").click(function () {

            if (flag) {
                func(false);
                flag = false;
            }

            $("#my_title_modal").modal('hide');
        });
    }
</script>
<%--提示modal--%>
<div class="modal fade bs-example-modal-sm" id="showTip" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;温馨提示</h4>
            </div>
            <div class="modal-body">
                <p id="tipContent"></p>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>
<%--删除modal--%>
<div class="modal fade" id="my_title_modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请注意！</h4>
            </div>
            <div class="modal-body">
                <p id="content_title_modal_p"></p>
            </div>
            <div class="modal-footer">
                <button type="button" id="modal_cancel_button" class="btn btn-sm btn-default">关闭</button>
                <button type="button" id="modal_confirm_button" class="btn btn-sm btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>