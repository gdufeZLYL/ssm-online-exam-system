<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>在线考试系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/resources/static/images/online_exam.png" type="image/x-icon" />
    <!-- 考生信息页面css -->
    <link rel="stylesheet" href="/resources/static/css/accounts/loginAdmin.css" />
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!-- 引入toastr -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body style="background-color: #EEEEEE">
    <div class="container">
        <form class="form-signin">
            <h2 class="form-signin-heading">管理员登录</h2>
            <label for="inputUsername" class="sr-only">账号</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="请输入教工号" required autofocus>
            <label for="inputPassword" class="sr-only">密码</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="请输入密码" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" id="inputReb" value="remember-me"> 记住我
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="button" onclick="loginAction()">登录</button>
        </form>
    </div> <!-- /container -->
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 引入toastr -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
    toastr.options.positionClass = 'toast-top-center';

    $(document).ready(function () {
        //读取cookie
        var adminAccount = getCookie('adminAccount');
        console.log('adminAccount = ' + adminAccount);
        var elements = adminAccount.split('|');
        if (elements.length == 2) {
            var username = elements[0];
            var password = elements[1];
            $('#inputUsername').val(username);
            $('#inputPassword').val(password);
        }
    });

    //登录事件触发
    function loginAction() {
        var username = $('#inputUsername').val();
        var password = $('#inputPassword').val();
        if (username == '') {
            toastr.error('账号不能为空！');
        } else if (password == '') {
            toastr.error('密码不能为空！');
        } else {
            //调用后端接口
            $.ajax({
                type: "post",
                async: false,
                url: "/exam/accounts/api/checklogin",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: {username: username, password: password, identity: "2"},
                success: function (data) {
                    console.log(data);
                    if (data.success == true) {
                        //缓存cookie
                        console.log($('#inputReb').is(":checked"));
                        if ($('#inputReb').is(":checked")) {
                            console.log('remember me!')
                            setCookie("adminAccount", username+'|'+password, 24*60);
                        }
                        toastr.success('登录成功(*^▽^*)');
                        //todo 跳转到管理员首页
                        window.location.href = '/exam/admin/posts';
                    } else {
                        toastr.error(data.message);
                    }
                },
                error:function(data){
                    toastr.error('噢噢,您已经被外星人劫持了Σ(⊙▽⊙"a');
                }
            });
        }
    }
</script>
</body>
</html>