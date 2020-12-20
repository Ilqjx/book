<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        // 给确定按钮绑定单击事件，用于跳转到指定的页码
        $("#search_btn").click(function () {
            var pageNo = $("#pn_input").val();
            var pageTotal = ${requestScope.page.pageTotal};

            // 边界处理
            if (pageNo < 1 || pageNo > pageTotal) {
                alert("输入数据非法，请重新输入！");
                return false;
            }

            location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
        });
    });
</script>

<div id="page_nav">
    <%-- 第一页不显示首页和上一页 --%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <c:choose>
        <%-- 1、总页码小于等于 5 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>

        <%-- 2、总页码大于 5 --%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%-- 2.1、前三页 --%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%-- 2.2、最后三页 --%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal - 2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%-- 2.3、剩余的中间页 --%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%-- 遍历输出页码 --%>
    <c:forEach begin="${pageScope.begin}" end="${pageScope.end}" var="pageNo">
        <c:if test="${pageNo == requestScope.page.pageNo}">
            【${pageNo}】
        </c:if>
        <c:if test="${pageNo != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${pageNo}">${pageNo}</a>
        </c:if>
    </c:forEach>

    <%-- 最后一页不显示末页和下一页 --%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input id="pn_input" name="pn" value="${requestScope.page.pageNo}"/>页
    <input id="search_btn" type="button" value="确定">
</div>