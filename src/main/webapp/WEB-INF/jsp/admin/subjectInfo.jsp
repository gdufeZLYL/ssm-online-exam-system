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
    <link rel="stylesheet" href="/resources/static/css/admin/subjectInfo.css" />
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
                <li><a href="javascript:void(0);">首页</a></li>
                <li><a href="javascript:void(0);">考生信息管理</a></li>
                <li><a href="javascript:void(0);">考生成绩查询</a></li>
                <li><a href="javascript:void(0);">试卷管理</a></li>
                <li class="active"><a href="javascript:void(0);">题目管理</a></li>
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
            <h3>试题信息管理</h3>
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-3">
        </div>
        <div class="col-md-8 form-inline">
            <label for="txt_subjectName">考试题目：</label>
            <input type="text" class="form-control" id="txt_subjectName" placeholder="考试题目">
            <label for="txt_paperName">试卷名称：</label>
            <input type="text" class="form-control" id="txt_paperName" placeholder="试卷名称">
            <button type="button" class="btn btn-primary" onclick="querySubAction()">查询</button>
        </div>
        <div class="col-md-1">
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col-md-10" id="subPageHead">

        </div>
        <div class="col-md-2">
            <button type="button" class="btn btn-primary" onclick="addSubAction()">添加试题</button>
        </div>
    </div>
    <div class="row">
        <table class="table table-hover table-bordered table-striped" id="t_subjectList">
            <!--
            <thead>
                <tr>
                    <th>#</th>
                    <th style="min-width: 400px;max-width: 400px;">考试题目</th>
                    <th>题目类型</th>
                    <th style="min-width: 200px;max-width: 200px;">所属试卷</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>1</th>
                    <th style="min-width: 400px;max-width: 400px;">主机A向主机B连续发送了两个TCP报文段,其序号分包是70和100,如果A发送的第一个报文段丢失了,但第二个报文段达到了B,B在第二个报文段到达后向A发送确认,那么这个确认号是多少?</th>
                    <th>不定项选择题</th>
                    <th style="min-width: 200px;max-width: 200px;">广东财经大学概率论试题111111111111111111111111111111111111</th>
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
<!-- 添加试题模态框 -->
<div class="modal fade" id="addSubModel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加试题</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_add_title">题目</label>
                    <textarea name="txt_add_title" class="form-control" id="txt_add_title" placeholder="请输入题目" rows="4"></textarea>
                </div>
                <div class="form-group">
                    <label for="txt_add_optionA">选项A</label>
                    <input type="text" name="txt_add_optionA" class="form-control" id="txt_add_optionA" placeholder="请输入选项A">
                </div>
                <div class="form-group">
                    <label for="txt_add_optionB">选项B</label>
                    <input type="text" name="txt_add_optionB" class="form-control" id="txt_add_optionB" placeholder="请输入选项B">
                </div>
                <div class="form-group">
                    <label for="txt_add_optionC">选项C</label>
                    <input type="text" name="txt_add_optionC" class="form-control" id="txt_add_optionC" placeholder="请输入选项C">
                </div>
                <div class="form-group">
                    <label for="txt_add_optionD">选项D</label>
                    <input type="text" name="txt_add_optionD" class="form-control" id="txt_add_optionD" placeholder="请输入选项D">
                </div>
                <div class="form-group">
                    <label for="txt_add_answer">正确答案</label>
                    <input type="text" name="txt_add_answer" class="form-control" id="txt_add_answer" placeholder="请输入正确答案">
                </div>
                <div class="form-group">
                    <label for="txt_add_parse">答案解析</label>
                    <textarea name="txt_add_parse" class="form-control" id="txt_add_parse" placeholder="请输入答案解析" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="txt_add_titleType">题目类型</label>
                    <select class="form-control" id="txt_add_titleType" name="txt_add_titleType">
                        <option value="0">单项选择题</option>
                        <option value="1">不定项选择题</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt_add_paperId">所属试卷ID</label>
                    <input type="text" name="txt_add_paperId" class="form-control" id="txt_add_paperId" placeholder="请输入所属试卷ID">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                <button type="button" id="btn_addStu" class="btn btn-primary" onclick="addSubject()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确定添加</button>
            </div>
        </div>
    </div>
</div>
<!-- 编辑试题模态框 -->
<div class="modal fade" id="updateSubModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑试题信息</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" name="txt_update_subId" id="txt_update_subId">
                <div class="form-group">
                    <label for="txt_update_title">题目描述</label>
                    <textarea name="txt_update_title" class="form-control" id="txt_update_title" placeholder="请输入题目" rows="4"></textarea>
                </div>
                <div class="form-group">
                    <label for="txt_update_optionA">选项A</label>
                    <input type="text" name="txt_update_optionA" class="form-control" id="txt_update_optionA" placeholder="请输入选项A">
                </div>
                <div class="form-group">
                    <label for="txt_update_optionB">选项B</label>
                    <input type="text" name="txt_update_optionB" class="form-control" id="txt_update_optionB" placeholder="请输入选项B">
                </div>
                <div class="form-group">
                    <label for="txt_update_optionC">选项C</label>
                    <input type="text" name="txt_update_optionC" class="form-control" id="txt_update_optionC" placeholder="请输入选项C">
                </div>
                <div class="form-group">
                    <label for="txt_update_optionD">选项D</label>
                    <input type="text" name="txt_update_optionD" class="form-control" id="txt_update_optionD" placeholder="请输入选项D">
                </div>
                <div class="form-group">
                    <label for="txt_update_answer">正确答案</label>
                    <input type="text" name="txt_update_answer" class="form-control" id="txt_update_answer" placeholder="请输入正确答案">
                </div>
                <div class="form-group">
                    <label for="txt_update_parse">答案解析</label>
                    <textarea name="txt_update_parse" class="form-control" id="txt_update_parse" placeholder="请输入答案解析" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="txt_update_titleType">题目类型</label>
                    <select class="form-control" id="txt_update_titleType" name="txt_update_titleType">
                        <option value="0">单项选择题</option>
                        <option value="1">不定项选择题</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="txt_update_paperId">所属试卷ID</label>
                    <input type="text" name="txt_update_paperId" class="form-control" id="txt_update_paperId" placeholder="请输入所属试卷ID">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消</button>
                <button type="button" id="btn_updateStu" class="btn btn-primary" onclick="updateSubject()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 删除试题模态框 -->
<div class='modal fade' id='delSubModal' >
    <input type="hidden" id="txt_del_subId">
    <div class='modal-backdrop in' style='opacity:0; '></div>
    <div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>
        <div class='modal-content'>
            <div class='modal-header' style='font-size:16px; '>
                <span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息<button type='button' class='close' data-dismiss='modal'>
                <span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button>
            </div>
            <div class='modal-body text-center' style='font-size:18px; '>
                确定要删除这道题目吗？o(╥﹏╥)o
            </div>
            <div class='modal-footer ' style=''>
                <button class='btn btn-success' id='delSubConfirm' onclick="delSubject()">确定</button>
                <button class='btn btn-danger' data-dismiss='modal'>取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<script src="/resources/static/js/admin/subjectInfo.js"></script>
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
    var subjects = [];    //当前数据
    var allSize = 0;    //总共数据量
    var perPage = 5; //5页为分页

    $(document).ready(function () {
        var sname = ${sname};
        var pname = ${pname};
        currPage = ${page};
        $('#txt_subjectName').val(sname);
        $('#txt_paperName').val(pname);
        //初始化数据
        getSubjectPageInfo();
    });


</script>
</body>
</html>