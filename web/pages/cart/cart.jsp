<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给删除标签绑定单击事件，用于提示用户是否确认删除
            $("a.delete_item").click(function () {
                var name = $(this).parent().parent().find("td:first").text();
                return confirm("是否确认删除【" + name + "】？");
            });

            // 给清空购物车标签绑定单击事件，用于提示用户是否清空购物车
            $("a#clear_cart").click(function () {
                return confirm("是否确认清空购物车？");
            });

            // 给显示数量的输入框绑定 onchange 事件，用于数量改变时提交给服务器进行修改
            // onchange 事件：当内容改变并且失去焦点时才会触发
            // 【暂未考虑库存】
            $("input.update_count").change(function () {
                // 获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                // 获取商品数量
                var count = $(this).val();
                // 给用户提示
                if (confirm("是否确认修改【" + name + "】的数量为 " + count + " 吗？")) {
                    // 确认修改，提交服务器修改
                    // 获取商品编号
                    var id = $(this).attr("itemId");
                    location.href = "${pageScope.basePath}cart?action=updateItemCount&id=" + id + "&count=" + count;
                } else {
                    // 不修改，恢复为原数量
                    // defaultValue 设置或者返回文本框的初始内容
                    this.value = this.defaultValue;
                }
            });

            // 给【去结账】超链接绑定单击事件，用于用户未登录时提示用户是否现在登录
            $("a#pay").click(function () {
                // 用户未登录
                <c:if test="${empty sessionScope.user}">
                    if ( confirm("如需结账，还需登录。是否确认现在登录？") ) {
                        location.href = "${pageScope.basePath}pages/user/login.jsp";
                    }
                    // 取消提交
                    return false;
                </c:if>

                // 用户已登录
                <c:if test="${not empty sessionScope.user}">
                    return confirm("是否确认结账？");
                </c:if>
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <%-- 购物车商品为空时显示 --%>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5">
                    <a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a>
                </td>
            </tr>
        </c:if>

        <%-- 购物车展示 --%>
        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td>
                    <input class="update_count" itemId="${entry.value.id}" type="text" value="${entry.value.count}"
                           style="width: 80px; text-align: center;"/>
                </td>
                <td>￥${entry.value.price}</td>
                <td>￥${entry.value.totalPrice}</td>
                <td><a class="delete_item" href="cart?action=deleteItem&id=${entry.value.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>

    <%-- 购物车商品非空时显示 --%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clear_cart" href="cart?action=clear">清空购物车</a></span>
            <span class="cart_span"><a id="pay" href="order?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>