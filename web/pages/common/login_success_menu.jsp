<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script>
    $(function () {
        // 给我的订单超链接绑定单击事件，用于判断用户是否登录及处理
        $("a#my_order").click(function () {
            // 未登录
            <c:if test="${empty sessionScope.user}">
                if ( confirm("如需查看我的订单，还需登录。是否立即登录？") ) {
                    location.href = "${pageScope.basePath}pages/user/login.jsp";
                }
                // 取消跳转
                return false;
            </c:if>
            return true;
        });
    });
</script>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a id="my_order" href="order?action=showMyOrders">我的订单</a>
    <a href="user?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
