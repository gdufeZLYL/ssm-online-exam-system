//添加试题模态款触发
function addSubAction() {
    //清空输入框
    $('#txt_add_title').val('');
    $('#txt_add_optionA').val('');
    $('#txt_add_optionB').val('');
    $('#txt_add_optionC').val('');
    $('#txt_add_optionD').val('');
    $('#txt_add_answer').val('');
    $('#txt_add_paperId').val('');
    $('#addSubModel').modal("show");
}

//添加试题
function addSubject() {
    var title = $('#txt_add_title').val();
    var optionA = $('#txt_add_optionA').val();
    var optionB = $('#txt_add_optionB').val();
    var optionC = $('#txt_add_optionC').val();
    var optionD = $('#txt_add_optionD').val();
    var answer = $('#txt_add_answer').val();
    var parse = $('#txt_add_parse').val();
    var titleType = jQuery('#txt_update_titleType option:selected').val();
    console.log('titleType = ' + titleType);
    var paperId = $('#txt_add_paperId').val();

    if (title == '') {
        toastr.error('请输入题目描述!');
    } else if (optionA == '') {
        toastr.error('请输入选项A!');
    } else if (optionB == '') {
        toastr.error('请输入选项B!');
    } else if (optionC == '') {
        toastr.error('请输入选项C!');
    } else if (optionD == '') {
        toastr.error('请输入选项D!');
    } else if (answer == '') {
        toastr.error('请输入答案!');
    } else if (parse == '') {
        toastr.error('请输入答案解析!');
    } else if (titleType == '') {
        toastr.error('请选择题目类型!');
    } else if (paperId == '') {
        toastr.error('请输入所属试卷ID!');
    } else if (!isInteger(paperId)) {
        toastr.error('试卷ID必须为正整数!');
    } else {
        $.ajax({
            url : "/exam/admin/api/addSubject",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                title: title,
                optionA: optionA,
                optionB: optionB,
                optionC: optionC,
                optionD: optionD,
                answer: answer,
                parse: parse,
                titleType: titleType,
                paperId: paperId
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                if (result.success == true) {
                    $('#addSubModel').modal("hide");
                    toastr.success('添加成功!');
                    //添加成功刷新页面
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error:function(result){
                toastr.error('添加失败o(╥﹏╥)o!');
            }
        });
    }
}

//编辑考生信息模态款触发
function updateSubAction(index) {
    $('#txt_update_subId').val(subjects[index].id);
    $('#txt_update_title').val(subjects[index].title);
    $('#txt_update_optionA').val(subjects[index].optionA);
    $('#txt_update_optionB').val(subjects[index].optionB);
    $('#txt_update_optionC').val(subjects[index].optionC);
    $('#txt_update_optionD').val(subjects[index].optionD);
    $('#txt_update_answer').val(subjects[index].answer);
    $('#txt_update_parse').val(subjects[index].parse);
    $('#txt_update_paperId').val(subjects[index].paperId);
    $('#txt_update_titleType').val(subjects[index].titleType);
    $('#updateSubModel').modal("show");
}

//修改考生信息
function updateSubject() {
    var id = $('#txt_update_subId').val();
    var title = $('#txt_update_title').val();
    var optionA = $('#txt_update_optionA').val();
    var optionB = $('#txt_update_optionB').val();
    var optionC = $('#txt_update_optionC').val();
    var optionD = $('#txt_update_optionD').val();
    var answer = $('#txt_update_answer').val();
    var parse = $('#txt_update_parse').val();
    var titleType = jQuery('#txt_update_titleType option:selected').val();
    console.log('titleType = ' + titleType);
    var paperId = $('#txt_update_paperId').val();

    if (title == '') {
        toastr.error('请输入题目描述!');
    } else if (optionA == '') {
        toastr.error('请输入选项A!');
    } else if (optionB == '') {
        toastr.error('请输入选项B!');
    } else if (optionC == '') {
        toastr.error('请输入选项C!');
    } else if (optionD == '') {
        toastr.error('请输入选项D!');
    } else if (answer == '') {
        toastr.error('请输入答案!');
    } else if (parse == '') {
        toastr.error('请输入答案解析!');
    } else if (titleType == '') {
        toastr.error('请选择题目类型!');
    } else if (paperId == '') {
        toastr.error('请输入所属试卷ID!');
    } else if (!isInteger(paperId)) {
        toastr.error('试卷ID必须为正整数!');
    } else {
        $.ajax({
            url : "/exam/admin/api/updateSubject",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                id: id,
                title: title,
                optionA: optionA,
                optionB: optionB,
                optionC: optionC,
                optionD: optionD,
                answer: answer,
                parse: parse,
                titleType: titleType,
                paperId: paperId
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                if (result.success == true) {
                    $('#updateSubModel').modal("hide");
                    toastr.success('更新成功!');
                    //添加成功刷新页面
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error:function(result){
                toastr.error('更新失败o(╥﹏╥)o!');
            }
        });
    }
}

//删除考生信息模态框触发
function delSubAction(id) {
    $('#txt_del_subId').val(id);
    $('#delSubModal').modal("show");
}

//删除考生信息
function delSubject() {
    var id = $('#txt_del_subId').val();
    $.ajax({
        type:"DELETE",
        async:false,
        url:"/exam/admin/api/delSubject/"+id,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success:function(result) {
            <!-- 处理后端返回的数据 -->
            if (result.success == true) {
                $('#delStuModal').modal("hide");
                toastr.success('删除成功!');
                window.location.reload();
            } else {
                $('#delStuModal').modal("hide");
                toastr.error('删除失败o(╥﹏╥)o!');
            }
        },
        error:function(result){
            $('#delStuModal').modal("hide");
            toastr.error('删除失败o(╥﹏╥)o!');
        }
    });
}

//查询按钮事件触发
function querySubAction() {
    var subjectName = $('#txt_subjectName').val();
    var paperName = $('#txt_paperName').val();
    window.location.href = '/exam/admin/subjects?page=1&sname='+subjectName+'&pname='+paperName;
}

// 首页
function firstPage(){
    if(currPage == 1){
        toastr.error('已经是第一页数据!');
    }else{
        var subjectName = $('#txt_subjectName').val();
        var paperName = $('#txt_paperName').val();
        window.location.href = '/exam/admin/subjects?page=1&sname='+subjectName+'&pname='+paperName;
    }
}

//目标页面
function targetPage(i) {
    var subjectName = $('#txt_subjectName').val();
    var paperName = $('#txt_paperName').val();
    window.location.href = '/exam/admin/subjects?page='+i+'&sname='+subjectName+'&pname='+paperName;
}

// 尾页
function lastPage(){
    if(currPage == pageNum){
        //alert("已经是最后一页数据");
        toastr.error('已经是最后一页数据!');
    }else{
        var subjectName = $('#txt_subjectName').val();
        var paperName = $('#txt_paperName').val();
        window.location.href = '/exam/admin/candidates?page='+pageNum+'&sname='+subjectName+'&pname='+paperName;
    }
}

//ajax请求后台数据
function getSubjectPageInfo(){
    var subjectName = $('#txt_subjectName').val();
    var paperName = $('#txt_paperName').val();
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/admin/api/getSubjectList",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{subjectTitle:subjectName, paperName:paperName, page:currPage, num:limit},
        success:function(data){
            //console.log(data);
            subjects = data.data.subjects;
            pageNum = data.data.pageNum;
            currPage = data.data.currPage;
            limit = data.data.size;
            allSize = data.data.allSize;
            listSubjectsInfo(subjects);
            //console.log(papers);
        }
    });
}

//列出考生信息列表
function listSubjectsInfo(subjects) {
    var s = '<thead><tr><th>#</th><th style="min-width: 400px;max-width: 400px;">考试题目</th><th>题目类型</th><th style="min-width: 200px;max-width: 200px;">所属试卷</th><th>操作</th></tr></thead><tbody>';
    $.each(subjects,function(index,value){
        s+='<tr><th>'+value.id+'</th>';
        s+='<th style="min-width: 400px;max-width: 400px;">'+value.title+'</th>';
        if (value.titleType == 0) {
            s+='<th>单项选择题</th>';
        } else {
            s+='<th>不定项选择题</th>';
        }
        s+='<th style="min-width: 200px;max-width: 200px;">'+value.paper.paperName+'</th>';
        s+='<th><button type="button" class="btn btn-primary btn-xs" onclick="updateSubAction('+index+')">编辑</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delSubAction('+subjects[index].id+')">删除</button></th></tr>';
    });
    s+="</tbody>";
    if(subjects.length>0){
        $("#t_subjectList").html(s);
    }else{
        $("#t_subjectList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    if(subjects.length>0){
        var subPageStr = '<ul class="pagination">';
        subPageStr += '<li><a>'+allSize+'&nbsp;条记录&nbsp;'+currPage+'/'+pageNum+'&nbsp;页</a></li>';
        subPageStr += '<li><a href="#" onclick="firstPage();">首页</a></li>';
        var currGroup = Math.floor((currPage-1)/perPage);
        //console.log(currGroup);
        if (currGroup > 0) subPageStr += '<li><a href="#" onclick="targetPage('+((currGroup-1)*perPage+1)+');" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
        var startPage = Math.floor((currPage-1)/perPage)*perPage+1;
        for (var i = startPage; i < startPage+5 && i <= pageNum; i++) {
            subPageStr += '<li>';
            if (i == currPage) {
                subPageStr += '<a class="active" style="border:none !important;">';
                subPageStr += i + '</a>';
            } else {
                subPageStr += '<li><a href="#" onclick="targetPage('+i+');">'+i+'</a>';
            }
            subPageStr += '</li>';
        }
        if (currGroup < Math.floor((pageNum-1)/perPage)) subPageStr += '<li><a href="#" onclick="targetPage('+((currGroup+1)*perPage+1)+');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>';
        subPageStr += '<li><a href="#" onclick="lastPage();">尾页</a></li>';
        $("#subPageHead").html(subPageStr);
        $("#subPageFoot").html(subPageStr);
    }
}