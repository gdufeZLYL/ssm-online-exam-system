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
    <!-- paper页面css -->
    <link rel="stylesheet" href="/resources/static/css/students/paper.css" />
    <!-- 公共页面css -->
    <link rel="stylesheet" href="/resources/static/css/common/magic-check.css" />
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body class="layui-layout-body" style="overflow: scroll;background-color: #F5F7F9">
    <div class="layui-container" style="background-color: #ffffff;width: 900px!important;">
        <div class="layui-row exam_time_head" style="border: 1px solid #FFFFFF">
            <div class="layui-col-md4" style="font-size: 27px;font-weight: bolder">
                考试时间:&nbsp;60分钟
            </div>
            <div class="layui-col-md4" style="font-size: 27px;font-weight: bolder" id="studentName">
                <!--<div id="currtimer" style="font-size: 27px;font-weight: bolder"></div>-->
                <%--考&nbsp;&nbsp;生:&nbsp;曾庆熙--%>
            </div>
            <div class="layui-col-md4">
                <!--剩余时间: 00:59:50-->
                <div id="timer" style="font-size: 27px;font-weight: bolder"></div>
            </div>
        </div>
        <div class="layui-row exam_title">
            <div class="layui-col-md12" style="border: 1px solid #FFFFFF">
                ${paperName}
            </div>
        </div>
        <div class="layui-row exam_title_remarks">
            <div class="layui-col-md12" style="border: 1px solid #FFFFFF">
                (满分100分&nbsp;单项选择题50分&nbsp;不定项选择题50分)
            </div>
        </div>
        <div class="layui-row exam_single_choice_head">
            <div class="layui-col-md12" style="border: 1px solid #FFFFFF">
                单项选择题(每小题5分,共10道)
            </div>
        </div>
        <div class="layui-row">
            <table lay-skin="nob" class="layui-table" id="exam_single_subject_table">
                <!--
                <tr><td><p style="font-size: 16px;font-weight: bold;">主机A向主机B连续发送了两个TCP报文段,其序号分包是70和100,如果A发送的第一个报文段丢失了,但第二个报文段达到了B,B在第二个报文段到达后向A发送确认,那么这个确认号是多少?</p></td></tr>
                <tr>
                    <td>
                        <input class="magic-radio" type="radio" name="sb1" value="A" id="A" checked/>
                        <label for="A" style="font-size: 16px;">A.&nbsp;100</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-radio" type="radio" name="sb1" id= "B" value="B"/>
                        <label for="B" style="font-size: 16px;">B.&nbsp;101</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-radio" type="radio" name="sb1" value="C" id="C"/>
                        <label for="C" style="font-size: 16px;">C.&nbsp;70</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-radio" type="radio" name="sb1" value="D" id="D"/>
                        <label for="D" style="font-size: 16px;">D.&nbsp;71</label>
                    </td>
                </tr>
                -->
            </table>
        </div>
        <div class="layui-row exam_double_choice_head">
            <div class="layui-col-md12" style="border: 1px solid #FFFFFF">
                不定项选择题(每小题10分,共5道)
            </div>
        </div>
        <div class="layui-row">
            <table lay-skin="nob" class="layui-table" id="exam_double_subject_table">
                <!--
                <tr><td><p style="font-size: 16px;font-weight: bold;">64位机上,一个结构体有三个成员,分别是char,int,short类型,三个成员位于结构体中不同位置时整个结构体的大小可能是下面哪些值?</p></td></tr>
                <tr>
                    <td>
                        <input class="magic-checkbox" type="checkbox" name="sb1" value="A" id="A2" checked/>
                        <label for="A2" style="font-size: 16px;">A.&nbsp;12</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-checkbox" type="checkbox" name="sb1" id= "B2" value="B"/>
                        <label for="B2" style="font-size: 16px;">B.&nbsp;7</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-checkbox" type="checkbox" name="sb1" value="C" id="C2"/>
                        <label for="C2" style="font-size: 16px;">C.&nbsp;8</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="magic-checkbox" type="checkbox" name="sb1" value="D" id="D2"/>
                        <label for="D2" style="font-size: 16px;">D.&nbsp;16</label>
                    </td>
                </tr>
                -->
            </table>
        </div>
        <div class="layui-row">
            <div class="layui-col-md12" style="border: 1px solid #FFFFFF;text-align: center;">
                <a style="border: 1px solid #FFFFFF;" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal" href="javascript:void(0);" onclick="submitPaper()">
                    我要交卷
                </a>
            </div>
        </div>
        <div class="modal fade " id="timeAlert" style="z-index:2000; ">
            <div class="modal-backdrop in" style="z-index:1900"></div>
            <div class="modal-dialog" style="z-index:1901; margin-top:10%; ">
                <div class="modal-content">
                    <div class="modal-header" style="font-size: 16px">
                        <span class='glyphicon glyphicon-info-sign'>&nbsp;</span>警告
                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    </div>
                    <div class="modal-body text-center" style="font-size: 20px;">
                        离考试时间结束还有<strong style="color: red;">15</strong>分钟!
                    </div>
                    <div class="modal-footer">
                        <a data-dismiss="modal" class="btn btn-success" href="#">确定</a>
                    </div>
                </div>
            </div>
        </div>
        <div class='modal fade' id='submitConfirm' >
            <div class='modal-backdrop in' style='opacity:0; '></div>
            <div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>
                <div class='modal-content'>
                    <div class='modal-header' style='font-size:16px; '>
                        <span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息<button type='button' class='close' data-dismiss='modal'>
                        <span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button>
                    </div>
                    <div class='modal-body text-center' style='font-size:18px; '>
                        确定要提交试卷？
                    </div>
                    <div class='modal-footer ' style=''>
                        <button class='btn btn-success ' id='confirmOk' >确定</button>
                        <button class='btn btn-danger ' data-dismiss='modal'>取消</button>
                    </div>
                </div>
            </div>
        </div>
        <div class='modal fade' id='waitSubmit' >
            <div class='modal-backdrop in' style='opacity:0; '></div>
            <div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>
                <div class='modal-content'>
                    <div class='modal-header' style='font-size:16px; '>
                        <span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息<button type='button' class='close' data-dismiss='modal'>
                        <%--<span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button>--%>
                    </div>
                    <div class='modal-body text-center' style='font-size:18px; '>
                        请耐心等候,正在为您提交答题卡......
                    </div>
                </div>
            </div>
        </div>
        <%--Paper, ${paperId}, ${paperName}--%>
    </div>
<!-- 引入JQuery -->
<script src="/resources/static/js/util.js"></script>
<script src="/resources/static/js/students/paper.js"></script>
<script src="/resources/static/js/jquery-3.2.1.min.js"></script>
<script src="/resources/layui/layui.js" charset="utf-8"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    <%
        // 获取请求的上下文
        String context = request.getContextPath();
    %>
    var sgSubjects = [];    //单选题
    var dbSubjects = [];    //多选题
    var paperId = ${paperId};   //试卷ID
    var sgSubjectSize = 0;  //单选题数量
    var dbSubjectSize = 0;  //多选题数量

    var studentJsonStr = ${student};
    var student = JSON.parse(studentJsonStr);

    $(document).ready(function () {
        //获取单选题列表
        getSgPapers(paperId);
        //获取多选题列表
        getDbPapers(paperId);
        //填充试题数据
        fillSubject(sgSubjects,sgSubjectSize,dbSubjects,dbSubjectSize);
    });

    //倒计时
    var maxtime = 60 * 60; //一个小时，按秒计算，自己调整!
    //var maxtime = 5; //一个小时，按秒计算，自己调整!
    function CountDown() {
        if (maxtime >= 0) {
            minutes = Math.floor(maxtime / 60);
            seconds = Math.floor(maxtime % 60);
            msg = "剩余时间: 00:" + formatNumber(minutes) + ":" + formatNumber(seconds);
            document.all["timer"].innerHTML = msg;
            if (maxtime == 15 * 60){
                //alert("还剩15分钟");
                $("#timeAlert").modal("show");

            }
            --maxtime;
        } else{
            clearInterval(timer);
            //alert("时间到，结束!");
            submiting();
        }
    }
    timer = setInterval("CountDown()", 1000);
</script>
<script src="/resources/static/js/particle.js"></script>
</body>
</html>