<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给【确认签收】按钮绑定单击事件，用于确认签收操作
            $("button.confirm_receive").click(function () {
                if ( confirm("是否确认签收？") ) {
                    // 确认签收
                    var orderId = $(this).attr("orderId");
                    location.href = "${pageScope.basePath}order?action=receive&orderId=" + orderId;
                }
            });
        });
    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>

        <c:forEach items="${requestScope.myOrders}" var="order">
            <tr>
                <td>
                    <fmt:formatDate value="${order.createTime}" pattern="yyyy.MM.dd"/>
                </td>
                <td>￥${order.price}</td>
                <td>
                    <c:if test="${order.status == 0}">
                        未发货
                    </c:if>

                    <c:if test="${order.status == 1}">
                        <button class="confirm_receive" orderId="${order.orderId}">确认签收</button>
                    </c:if>

                    <c:if test="${order.status == 2}">
                        已签收
                    </c:if>
                </td>
                <td><a href="order?action=showOrderDetails&orderId=${order.orderId}">查看详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>