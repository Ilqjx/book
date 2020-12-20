<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript" src="static/script/bigdecimal.js"></script>
    <script type="text/javascript">
        $(function () {
            // 给 form 表单绑定提交事件，用于判断输入的价格是否合法
            $("#price_form").submit(function () {
                var minStr = $("#min").val();
                var maxStr = $("#max").val();

                // 判断是否是数字
                var pricePattern = /^-?\d*$/;
                if (!pricePattern.test(minStr) || !pricePattern.test(maxStr)) {
                    alert("输入数据非法，请重新输入！---");
                    return false;
                }

                // 将 minStr 和 maxStr 转换为 BigDecimal 对象
                // 如果转换 BigDecimal 对象出现错误直接提交到服务器
                // 建议用正则处理，判断是否是数字以及数字的范围
                var min = new BigDecimal(minStr);
                var max = new BigDecimal(maxStr);

                // 最小价格小于 0 或 最小价格大于最大价格禁止提交表单并给出提示
                if (min < 0 || min.compareTo(max) > 0) {
                    alert("输入数据非法，请重新输入！");
                    return false;
                }
                return true;
            });

            // 给加入购物车按钮绑定单击事件
            $("button.add_cart").click(function () {
                var bookId = $(this).attr("bookId");
                $.getJSON(
                    "${pageScope.basePath}cart",
                    "action=addItem&id=" + bookId,
                    function (data) {
                        $("span#total_count").text("您的购物车中有 " + data.totalCount + " 件商品");
                        $("div#last_name").html("您刚刚将<span style=\"color: red\"> " + data.lastName + " </span>加入到了购物车中");
                    }
                );
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="user?action=logout">注销</a>&nbsp;&nbsp;
            <a href="order?action=showMyOrders">我的订单</a>
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form id="price_form" action="client/book" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                价格：<input id="min" name="min" type="text" value="${param.min}"> 元 -
                <input id="max" name="max" type="text" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <c:if test="${empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="total_count"></span>
                <div id="last_name">
                    当前购物车为空！
                </div>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="total_count">您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
                <div id="last_name">
                    您刚刚将<span style="color: red"> ${sessionScope.lastName} </span>加入到了购物车中
                </div>
            </div>
        </c:if>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img alt="" class="book_img" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="add_cart" bookId="${book.id}">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%-- 静态包含分页条 --%>
    <%@ include file="/pages/common/page_nav.jsp" %>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>