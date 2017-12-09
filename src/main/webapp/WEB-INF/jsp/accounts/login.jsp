<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>在线考试系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/resources/static/images/online_exam.png" type="image/x-icon" />
    <!-- 考生信息页面css -->
    <link rel="stylesheet" href="/resources/static/css/accounts/login.css" />
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!-- 引入toastr -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <div class="form row" style="margin-left: 32%">
            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form">
                <h3 class="form-title">登录考试系统</h3>
                <div class="col-sm-9 col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="请输入学号"
                               id="inputUsername" name="inputUsername" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="请输入密码"
                               id="inputPassword" name="inputPassword" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" id="inputReb" name="inputReb" value="1"/> 记住我
                        </label>
                    </div>
                    <div class="form-group">
                        <input type="button" class="btn btn-success pull-right" value="Login " onclick="loginAction()"/>
                    </div>
                </div>
            </form>
            <div class="col-sm-3 col-md-3">
            </div>
        </div>
    </div>
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
        var stuAccount = getCookie('stuAccount');
        console.log('stuAccount = ' + stuAccount);
        var elements = stuAccount.split('|');
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
                data: {username: username, password: password, identity: "1"},
                success: function (data) {
                    console.log(data);
                    if (data.success == true) {
                        //缓存cookie
                        console.log($('#inputReb').is(":checked"));
                        if ($('#inputReb').is(":checked")) {
                            console.log('remember me!')
                            setCookie("stuAccount", username+'|'+password, 12);
                        }
                        toastr.success('登录成功(*^▽^*)');
                        window.location.href = "/exam/student/index";
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