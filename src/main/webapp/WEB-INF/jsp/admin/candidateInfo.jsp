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
    <link rel="stylesheet" href="/resources/static/css/admin/candidateInfo.css" />
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
                    <li class="active"><a href="javascript:void(0);">考生信息管理</a></li>
                    <li><a href="javascript:void(0);">考生成绩查询</a></li>
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
                <h3>考生信息管理</h3>
            </div>
        </div>
        <div class="row" style="margin-bottom: 10px;">
            <div class="col-md-1">
            </div>
            <div class="col-md-10 form-inline">
                <label for="txt_studentId">学号：</label>
                <input type="text" class="form-control" id="txt_studentId" placeholder="考生学号">
                <label for="txt_studentName">姓名：</label>
                <input type="text" class="form-control" id="txt_studentName" placeholder="考生姓名">
                <label for="txt_className">班级：</label>
                <input type="text" class="form-control" id="txt_className" placeholder="班级名称">
                <button type="button" class="btn btn-primary" onclick="queryStuAction()">查询</button>
            </div>
            <div class="col-md-1">
            </div>
        </div>
        <div class="row" style="margin-bottom: 10px;">
            <div class="col-md-9" id="subPageHead">

            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-primary" onclick="addStuAction()">添加考生信息</button>
            </div>
        </div>
        <div class="row">
            <table class="table table-hover table-bordered" id="t_studentList">
                <!--
                <thead>
                    <tr>
                        <th>#</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>身份证</th>
                        <th>密码</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>1</th>
                        <th>14251104201</th>
                        <th>李一</th>
                        <th>男</th>
                        <th>440105199911117913</th>
                        <th>123</th>
                        <th>软件工程</th>
                        <th>14级软件工程2班</th>
                        <th>
                            <button type="button" class="btn btn-primary btn-xs">编辑</button>
                            <button type="button" class="btn btn-danger btn-xs">删除</button>
                        </th>
                    </tr>
                </tbody>
                -->
            </table>
        </div>
        <div class="row">
            <div class="col-md-12" id="subPageFoot">

            </div>
        </div>
    </div> <!-- /container -->
    <!-- 添加考生信息模态框 -->
    <div class="modal fade" id="addStuModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加考生信息</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="txt_add_sno">学号</label>
                        <input type="text" name="txt_add_sno" class="form-control" id="txt_add_sno" placeholder="请输入考生学号">
                    </div>
                    <div class="form-group">
                        <label for="txt_add_sname">姓名</label>
                        <input type="text" name="txt_add_sname" class="form-control" id="txt_add_sname" placeholder="请输入考生姓名">
                    </div>
                    <div class="form-group">
                        <label for="txt_add_gender">性别</label>
                        <div class="radio" id="txt_add_gender">
                            <label class="radio-inline">
                                <input type="radio" name="txt_add_gender" id="txt_add_gender_man" value="1" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="txt_add_gender" id="txt_add_gender_woman" value="2"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="txt_add_icard">身份证号码</label>
                        <input type="text" name="txt_add_icard" class="form-control" id="txt_add_icard" placeholder="请输入考生身份证号码">
                    </div>
                    <div class="form-group">
                        <label for="txt_add_pwd">密码</label>
                        <input type="password" name="txt_add_pwd" class="form-control" id="txt_add_pwd" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label for="txt_add_profess">专业</label>
                        <input type="text" name="txt_add_profess" class="form-control" id="txt_add_profess" placeholder="请输入专业">
                    </div>
                    <div class="form-group">
                        <label for="txt_add_cname">班级</label>
                        <input type="text" name="txt_add_cname" class="form-control" id="txt_add_cname" placeholder="请输入班级">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                    <button type="button" id="btn_addStu" class="btn btn-primary" onclick="addStudent()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确定添加</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 编辑考生信息模态框 -->
    <div class="modal fade" id="updateStuModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑考生信息</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="txt_update_id" id="txt_update_id">
                    <div class="form-group">
                        <label for="txt_update_sno">学号</label>
                        <input type="text" name="txt_update_sno" class="form-control" id="txt_update_sno" placeholder="请输入考生学号">
                    </div>
                    <div class="form-group">
                        <label for="txt_update_sname">姓名</label>
                        <input type="text" name="txt_update_sname" class="form-control" id="txt_update_sname" placeholder="请输入考生姓名">
                    </div>
                    <div class="form-group">
                        <label for="txt_update_gender">性别</label>
                        <div class="radio" id="txt_update_gender">
                            <label class="radio-inline">
                                <input type="radio" name="txt_update_gender" id="txt_update_gender_man" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="txt_update_gender" id="txt_update_gender_woman" value="2"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="txt_update_icard">身份证号码</label>
                        <input type="text" name="txt_update_icard" class="form-control" id="txt_update_icard" placeholder="请输入考生身份证号码">
                    </div>
                    <div class="form-group">
                        <label for="txt_update_pwd">密码</label>
                        <input type="password" name="txt_update_pwd" class="form-control" id="txt_update_pwd" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label for="txt_update_profess">专业</label>
                        <input type="text" name="txt_update_profess" class="form-control" id="txt_update_profess" placeholder="请输入专业">
                    </div>
                    <div class="form-group">
                        <label for="txt_update_cname">班级</label>
                        <input type="text" name="txt_update_cname" class="form-control" id="txt_update_cname" placeholder="请输入班级">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                    <button type="button" id="btn_updateStu" class="btn btn-primary" onclick="updateStudent()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 删除考生模态框 -->
    <div class='modal fade' id='delStuModal' >
        <input type="hidden" id="txt_del_id">
        <div class='modal-backdrop in' style='opacity:0; '></div>
        <div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>
            <div class='modal-content'>
                <div class='modal-header' style='font-size:16px; '>
                    <span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息<button type='button' class='close' data-dismiss='modal'>
                    <span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button>
                </div>
                <div class='modal-body text-center' style='font-size:18px; '>
                    确定要删除该考生吗？
                </div>
                <div class='modal-footer ' style=''>
                    <button class='btn btn-success' id='delStuConfirm' onclick="delStudent()">确定</button>
                    <button class='btn btn-danger' data-dismiss='modal'>取消</button>
                </div>
            </div>
        </div>
    </div>
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
    <script src="/resources/static/js/admin/candidateInfo.js"></script>
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
    var students = [];    //当前数据
    var allSize = 0;    //总共数据量
    var perPage = 5; //5页为分页

    $(document).ready(function () {
        var sno = ${sno};
        var name = ${name};
        var cname = ${cname};
        currPage = ${page};
        $('#txt_studentId').val(sno);
        $('#txt_studentName').val(name);
        $('#txt_className').val(cname);
        //初始化数据
        getStudentPageInfo();
    });

    //获取url参数值
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }

</script>
</body>
</html>