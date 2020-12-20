<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a#send").click(function () {
                return confirm("是否确认发货？");
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>
        </tr>

        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>
                    <fmt:formatDate value="${order.createTime}" pattern="yyyy.MM.dd"/>
                </td>
                <td>￥${order.price}</td>
                <td><a href="order?action=showOrderDetails&orderId=${order.orderId}">查看详情</a></td>
                <td>
                    <c:if test="${order.status == 0}">
                        <a id="send" href="order?action=send&orderId=${order.orderId}">点击发货</a>
                    </c:if>

                    <c:if test="${order.status == 1}">
                        已发货
                    </c:if>

                    <c:if test="${order.status == 2}">
                        已签收
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>