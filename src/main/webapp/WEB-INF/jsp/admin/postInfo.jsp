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
    <link rel="stylesheet" href="/resources/static/css/admin/postInfo.css" />
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!-- 引入toastr -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body>
<!-- Static navbar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">考试系统后台管理中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="javascript:void(0);">首页</a></li>
                <li><a href="javascript:void(0);">考生信息管理</a></li>
                <li><a href="javascript:void(0);">考生成绩查询</a></li>
                <li><a href="javascript:void(0);">试卷管理</a></li>
                <li><a href="javascript:void(0);">题目管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <img src="http://cn.gravatar.com/avatar/7a57a5a743894a0e?s=16&d=identicon&r=PG"/>&nbsp;&nbsp;曾庆熙
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:void(0);">修改密码</a></li>
                        <li><a href="javascript:void(0);">退出系统</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container">
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-12 candidate-title">
            <h2>公告栏</h2>
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-8">

        </div>
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" onclick="addPostAction()">添加公告</button>
        </div>
        <div class="col-md-3">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8" id="t_postList">
            <!--
            <div class="row" style="margin-bottom: 10px;">
                <div class="col-md-12">
                    <div class="panel panel-info">
                        <div class="panel-heading" style="font-size: 20px;font-weight: bolder;">同学们注意了</div>
                        <div class="panel-body" style="font-size: 15px;">10月21号（星期二）中午12:20~13：50在一教314进行全校14级新生的素拓考试，考试范围：学生手册218~228，开卷考。大家记得先吃饱饭，到时记得准时到达考场~</div>
                        <div class="panel-footer" style="font-size: 12px;">沈永珞 发表于 2017-12-02 09:11:03</div>
                    </div>
                </div>
            </div>
            -->
        </div>
        <div class="col-md-2">
        </div>
    </div>
</div> <!-- /container -->
<!-- 添加公告模态框 -->
<div class="modal fade" id="addPostModel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加公告</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_add_title">标题</label>
                    <input type="text" name="txt_add_title" class="form-control" id="txt_add_title" placeholder="请输入公告标题">
                </div>
                <div class="form-group">
                    <label for="txt_add_content">内容</label>
                    <textarea name="txt_add_content" class="form-control" id="txt_add_content" placeholder="请输入公告内容" rows="8"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                <button type="button" id="btn_addPost" class="btn btn-primary" onclick="addPost()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确定添加</button>
            </div>
        </div>
    </div>
</div>
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<script src="/resources/static/js/admin/postInfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 引入toastr -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
    toastr.options.positionClass = 'toast-top-center';

    var adminJsonStr = ${admin};
    var admin = JSON.parse(adminJsonStr);
    var posts = [];
    var postSize = 0;

    $(document).ready(function () {
        //获取数据
        $.ajax({
            type:"get",
            async:false,
            url:"/exam/admin/api/getPosts",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success:function(data){
                postSize = data.data.postSize;
                posts = data.data.posts;
            }
        });
        //初始化数据
        fillPostInfo(posts, postSize);
    });

</script>
</body>
</html>