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
    <link rel="stylesheet" href="/resources/static/css/admin/gradeInfo.css" />
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
            <a class="navbar-brand" href="#">在线考试系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="javascript:void(0);">首页</a></li>
                <li><a href="javascript:void(0);">考生信息管理</a></li>
                <li class="active"><a href="javascript:void(0);">考生成绩查询</a></li>
                <li><a href="javascript:void(0);">试卷管理</a></li>
                <li><a href="javascript:void(0);">题目管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <%--Dropdown <span class="caret"></span>--%>
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
            <h3>考生成绩查询</h3>
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-12 form-inline">
            <label for="txt_studentId">学号：</label>
            <input type="text" class="form-control" id="txt_studentId" placeholder="考生学号">
            <label for="txt_studentName">姓名：</label>
            <input type="text" class="form-control" id="txt_studentName" placeholder="考生姓名">
            <label for="txt_className">班级：</label>
            <input type="text" class="form-control" id="txt_className" placeholder="班级名称">
            <%--<label for="txt_professName">专业：</label>--%>
            <%--<input type="text" class="form-control" id="txt_professName" placeholder="专业名称">--%>
            <label for="txt_paperName">试卷：</label>
            <input type="text" class="form-control" id="txt_paperName" placeholder="考试试卷名称">
            <button type="button" class="btn btn-primary">查询</button>
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-9" id="subPageHead">

        </div>
    </div>
    <div class="row">
        <table class="table table-hover table-bordered table-striped" id="t_gradeList">
            <thead>
                <tr>
                    <th>#</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>班级</th>
                    <th>专业</th>
                    <th>试卷</th>
                    <th>得分</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>14251104201</th>
                    <th>李一</th>
                    <th>14级软件工程2班</th>
                    <th>软件工程</th>
                    <th>广东财经大学概率论试题1</th>
                    <th>80</th>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-md-12" id="subPageFoot">

        </div>
    </div>
</div> <!-- /container -->
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<script src="/resources/static/js/admin/gradeInfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 引入toastr -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
    toastr.options.positionClass = 'toast-top-center';
    //分页参数设置 这些全局变量关系到分页的功能
    var startPage = 1;//开始页数
    var limit = 20;//每页显示数据条数
    var currPage = 1;//当前页数
    var pageNum = 0;    //总共页数
    var grades = [];    //当前数据
    var allSize = 0;    //总共数据量
    var perPage = 5; //5页为分页

    $(document).ready(function () {
        var sno = ${sno};
        var name = ${name};
        var cname = ${cname};
        var ename = ${ename};
        currPage = ${page};
        $('#txt_studentId').val(sno);
        $('#txt_studentName').val(name);
        $('#txt_className').val(cname);
        $('#txt_paperName').val(ename);
        //todo 初始化数据
        //getStudentPageInfo();
    });


</script>
</body>
</html>