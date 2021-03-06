<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>在线考试系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/resources/static/images/online_exam.png" type="image/x-icon" />
    <!-- 引入LayUi -->
    <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all"/>
    <!-- paperList页面css -->
    <link rel="stylesheet" href="/resources/static/css/students/papers.css" />
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!-- 引入toastr -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body class="layui-layout-body" style="overflow: scroll;">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-weight: bold;font-family: 幼圆;font-size: 30px;">在线考试系统</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="javascript:void(0);" onclick="indexAction()">首页</a></li>
            <li class="layui-nav-item layui-this"><a href="javascript:void(0);">在线考试</a></li>
            <li class="layui-nav-item"><a href="javascript:void(0);" onclick="queryScoreAction()">成绩查询</a></li>
            <li class="layui-nav-item"><a href="javascript:void(0);" onclick="updatePwdAction()">修改密码</a></li>
            <li class="layui-nav-item"><a href="javascript:void(0);" onclick="logoutAction()">退出系统</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" id="studentInfo">
                <!--<img src="http://cn.gravatar.com/avatar/7a57a5a743894a0e?s=8&d=identicon&r=PG" class="layui-nav-img">曾庆熙-->
            </li>
        </ul>
    </div>
    <div class="layui-container exam_main_body">
        <!-- 内容主体区域 -->
        <div class="layui-row exam_breadcrumb_navbar">
            <a href="/exam/student/index" style="color: #4183C4"><i class="layui-icon">&#xe68e;</i>首页</a>
            <i class="layui-icon">&#xe602;</i>
            在线考试
        </div>
        <div class="layui-row">
            <div class="layui-col-md12">
                <table lay-even lay-skin="line" class="layui-table" id="t_paperList">

                </table>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-md12" id="subPage">

            </div>
        </div>
    </div>
</div>
<!-- 修改密码模态框 -->
<div class="modal fade" id="updatePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_oldPassword">当前密码</label>
                    <input type="password" name="txt_oldPassword" class="form-control" id="txt_oldPassword" placeholder="请输入当前的密码">
                </div>
                <div class="form-group">
                    <label for="txt_newPassword">新密码</label>
                    <input type="password" name="txt_newPassword" class="form-control" id="txt_newPassword" placeholder="请输入密码">
                </div>
                <div class="form-group">
                    <label for="txt_rePassword">确认密码</label>
                    <input type="password" name="txt_rePassword" class="form-control" id="txt_rePassword" placeholder="请再次输入密码">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                <button type="button" id="btn_updatePwd" class="btn btn-primary" onclick="updatePassword()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确定修改</button>
            </div>
        </div>
    </div>
</div>
<!-- 退出系统模态框 -->
<div class='modal fade' id='logoutModal' >
    <div class='modal-backdrop in' style='opacity:0; '></div>
    <div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>
        <div class='modal-content'>
            <div class='modal-header' style='font-size:16px; '>
                <span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息<button type='button' class='close' data-dismiss='modal'>
                <span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button>
            </div>
            <div class='modal-body text-center' style='font-size:18px; '>
                确定要退出考试系统？
            </div>
            <div class='modal-footer ' style=''>
                <button class='btn btn-success' id='logoutConfirm' >确定</button>
                <button class='btn btn-danger' data-dismiss='modal'>取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/students/papers.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<script src="/resources/layui/layui.js" charset="utf-8"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 引入toastr -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
    //分页参数设置 这些全局变量关系到分页的功能
    var startPage = 1;//开始页数
    var limit = 12;//每页显示数据条数
    var currPage = 1;//当前页数
    var pageNum = 0;    //总共页数
    var papers = [];    //当前数据
    var allSize = 0;    //总共数据量
    var perPage = 5; //5页为分页

    var studentJsonStr = ${student};
    var student = JSON.parse(studentJsonStr);

    toastr.options.positionClass = 'toast-top-center'

    <%
        // 获取请求的上下文
        String context = request.getContextPath();
    %>

    $(document).ready(function () {
        //currPage = <%=request.getParameter("page") %>;
        currPage = ${page};
        var studentId = student.studentId;
        var studentName = student.studentName;
        var stuIdMd5 = MD5(studentId);
        //个人信息初始化
        fillStudentInfo(stuIdMd5, studentName);
        //初始化数据
        getPaperPageInfo();
    });

    //导航栏效果
    layui.use(['element'], function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             