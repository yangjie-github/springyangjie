<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>用户首页</title>
    <%@ include file="/WEB-INF/views/include/jspHeader.jsp"%>
    <script type="text/javascript">
        $(function () {
            /*显示时间*/
            setInterval("currentTime()", 1000);

            var setting = {
                view: {
                    selectedMulti: false, //设置是否能够同时选中多个节点
                    showIcon: true,  //设置是否显示节点图标
                    showLine: true,  //设置是否显示节点与节点之间的连线
                    showTitle: true,  //设置是否显示节点的title提示信息
                    fontCss: {
                        height: "25px"
                    }
                    // addDiyDom: addDiyDom
                },
                data: {
                    simpleData: {
                        enable: false, //设置是否启用简单数据格式（zTree支持标准数据格式跟简单数据格式，上面例子中是标准数据格式）
                        idKey: "id",  //设置启用简单数据格式时id对应的属性名称
                        pidKey: "pId" //设置启用简单数据格式时parentId对应的属性名称,ztree根据id及pid层级关系构建树结构
                    }
                },
                check: {
                    enable: false   //设置是否显示checkbox复选框
                },
                callback: {
                    onClick: zTreeOnClick,                        //定义节点单击事件回调函数
                    onAsyncSuccess: onAsyncSuccess,               //ztree加载完成之后的回调

                    // onRightClick: OnRightClick, //定义节点右键单击事件回调函数
                    // beforeRename: beforeRename, //定义节点重新编辑成功前回调函数，一般用于节点编辑时判断输入的节点名称是否合法
                    // onDblClick: onDblClick,  //定义节点双击事件回调函数
                    // onCheck: onCheck    //定义节点复选框选中或取消选中事件的回调函数
                },
                async: {
                    enable: true,      //设置启用异步加载
                    type: "post",      //异步加载类型:post和get
                    contentType: "application/json", //定义ajax提交参数的参数类型，一般为json格式
                    url: "${ctx}/menuController/getMenu",    //定义数据请求路径
                    autoParam: ["id=id", "name=name"] //定义提交时参数的名称，=号前面标识节点属性，后面标识提交时json数据中参数的名称
                }
            };

            $.fn.zTree.init($("#ztree"), setting);
        });

        /*用于在节点上固定显示用户自定义控件*/
        function addDiyDom(treeId, treeNode) {
            var icoObj = $("#" + treeNode.tId + "_ico"); //拿到图标元素
            if (treeNode.icon) {
                icoObj.removeClass("button ico_open").addClass(treeNode.icon);
            }
        }

        function zTreeOnClick(event, treeId, treeNode) {
            if (!treeNode.isParent) {
                // console.log(treeNode)
                window.top.frames['mainBody'].location.href = "${ctx}" + treeNode.perUrl;
            }
        }

        function currentTime() {
            var date = new Date();
            var time = "现在时刻:" + date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日;" +
                date.getHours() + "时" + date.getMinutes() + "分" + date.getSeconds() + "秒";
            $("#time").html(time);
        }

        function onAsyncSuccess(event, treeId, treeNode, msg) {
            //ztree加载完成之后默认选中的节点
            <%--var treeObj = $.fn.zTree.getZTreeObj(treeId);       //ztree树的ID--%>
            <%--var node = treeObj.getNodeByTId("tree_2");          //根据ID找到该节点--%>
            <%--treeObj.selectNode(node);                           //根据该节点选中--%>
            <%--window.top.frames['mainBody'].location.href = "${ctx}" + node.perUrl;--%>
            window.top.frames['mainBody'].location.href = "${ctx}/sysUserLogin/indexImg";
        }
    </script>
</head>
<style>
    .ztree * {
        font-size: 13px;
    }
</style>
<body style="background-color: dimgray">
<div class="container-fluid">
    <div class="page-header">
        <h1>Welcome To Welding Class Three</h1>
    </div>
    <div style="background-color: grey">
        <span style="color: #bce8f1;margin-left: 20px;">欢迎你:${username}</span>
        <span style="margin-left: 20px;"><a href="/sysUserLogin/loginOut">退出</a></span>
        <span style="margin-left: 20px;color: darkgray;height: 40px;line-height: 40px;" id="time"></span>
    </div>
    <div class="row">
        <div class="col-md-2 col-xs-12">
            <div style="height: 80%; color: whitesmoke;">
                <ul class="ztree" id="ztree"></ul>
            </div>
        </div>
        <div class="col-md-8 col-xs-12">
            <div id="contain">
                <!--allowtransparency是否透明-->
                <iframe frameborder="no" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" width="100%" height="80%" id="mainBody" name="mainBody"></iframe>
            </div>
        </div>
    </div>
    <hr style="padding: 0; margin: 0;">
    <span class="pull-right">&copy;jiezideshijie</span>
</div>
</body>
</html>
