<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>
<div id="login_header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${empty requestScope.errorMsg ? "请输入用户名和密码" : requestScope.errorMsg}
                    </span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input autocomplete="off" class="itxt" name="username" placeholder="请输入用户名" tabindex="1"
                               type="text" value="${empty requestScope.username ? cookie.username.value : requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input autocomplete="off" class="itxt" name="password" placeholder="请输入密码" tabindex="1"
                               type="password"/>
                        <br/>
                        <br/>
                        <input id="sub_btn" type="submit" value="登录"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>