<%-- 页面消息回显 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/tag.jsp" %>
<%@ attribute name="message" type="java.lang.String" required="true" description="提示信息"%>
<c:if test="${not empty message}">
    <div class="alert alert-warning" data-dismiss="alert" role="alert">${message}</div>
</c:if>