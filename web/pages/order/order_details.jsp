<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript" src="static/script/bigdecimal.js"></script>
    <script type="text/javascript">
        $(function () {
            var totalCount = 0;
            var totalPrice = new BigDecimal("0");
            <c:forEach items="${requestScope.orderItems}" var="orderItem">
                // 计算商品总数量
                var count = ${orderItem.count};
                totalCount += count;

                // 计算商品总价格
                var price = new BigDecimal("${orderItem.totalPrice}");
                totalPrice = totalPrice.add(price);
            </c:forEach>

            $("span#count").text(totalCount);
            $("span#price").text(totalPrice);
        });
    </script>
</head>
<body>

<div id="header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
    <span class="wel_word">订单详情</span>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>

        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>￥${orderItem.price}</td>
                <td>￥${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="cart_info">
        <span class="cart_span">共有<span id="count" class="b_count"></span>件商品</span>
        <span class="cart_span">总金额<span id="price" class="b_price"></span>元</span>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>