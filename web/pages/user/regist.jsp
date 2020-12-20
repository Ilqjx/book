<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                $("span.errorMsg").text("");

                // 验证用户名
                let usernameText = $("#username").val();
                let usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }

                // 验证密码
                let passwordText = $("#password").val();
                let passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                // 确认密码
                let repwdText = $("#repwd").val();
                if (repwdText != passwordText) {
                    $("span.errorMsg").text("确认密码和密码不一致");
                    return false;
                }

                // 邮箱验证
                let emailText = $("#email").val();
                // ^[a-z\d]+                开头第一个字母为小写字母或数字(至少出现一次)
                // \.[a-z\d]+               匹配 .后面至少一个小写字母或数字
                // (\.[a-z\d]+)*            任意次
                // @                        匹配 @
                // (-[\da-z])?              匹配 -后跟数字或小写字母，0次或1次
                // ([\da-z](-[\da-z])?)+    数字或小写字母至少出现一次
                // (\.{1,2}[a-z]+)+         .出现1到2次，小写字母至少一次，整体至少出现一次
                // $                        结尾
                // ^、$                     保证邮箱前后不能有其他字符
                let emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不正确");
                    return false;
                }

                // 验证码
                let codeText = $("#code").val().trim();
                // $.trim() 去空格
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                }
            });

            // 给验证码图片绑定单击事件，用于切换验证码
            $("#code_img").click(function () {
                this.src = "${pageScope.basePath}kaptcha.jpg?d=" + new Date().getTime();
            });

            // 检查用户名是否可用
            $("input#username").blur(function () {
                var username = $(this).val();
                $.getJSON(
                    "${pageScope.basePath}user",
                    "action=checkUsernameExists&username=" + username,
                    function (data) {
                        $("span.errorMsg").text(data.msg);
                    }
                );
            });
        });
    </script>

    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img alt="" class="logo_img" src="static/img/logo.gif">
</div>

<div class="login_banner">
    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${requestScope.errorMsg}
                    </span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input autocomplete="off" class="itxt" id="username"
                               name="username" placeholder="请输入用户名" tabindex="1" type="text"
                               value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input autocomplete="off" class="itxt" id="password"
                               name="password" placeholder="请输入密码" tabindex="1" type="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input autocomplete="off" class="itxt" id="repwd"
                               name="repwd" placeholder="确认密码" tabindex="1" type="password"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input autocomplete="off" class="itxt" id="email"
                               name="email" placeholder="请输入邮箱地址" tabindex="1" type="text"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" id="code" name="code" style="width: 150px;" type="text"/>
                        <img id="code_img" src="kaptcha.jpg"
                             style="width: 80px; height: 40px; float: right; margin-right: 40px">
                        <br/>
                        <br/>
                        <input id="sub_btn" type="submit" value="注册"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>