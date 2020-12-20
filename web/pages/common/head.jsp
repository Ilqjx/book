<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String scheme = request.getScheme();
    String ip = request.getServerName();
    int port = request.getServerPort();
    String contextPath = request.getContextPath();

    // 工程路径前面有 /，所以 port 后面就不用加 / 了，最后的 / 不能省略
    String basePath = scheme + "://" + ip + ":" + port + contextPath + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<%-- 设置当前页面相对路径的参照地址 --%>
<base href="${pageScope.basePath}"/>
<link href="static/css/style.css" rel="stylesheet" type="text/css">
<script src="static/script/jquery-1.7.2.js" type="text/javascript"></script>