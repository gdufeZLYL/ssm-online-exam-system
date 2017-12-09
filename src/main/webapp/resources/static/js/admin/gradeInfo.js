//查询按钮事件触发
function queryGradeAction() {
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    var paperName = $('#txt_paperName').val();
    window.location.href = '/exam/admin/grades?page=1&sno='+studentId+'&name='+studentName+'&cname='+className+'&ename='+paperName;
}

// 首页
function firstPage(){
    if(currPage == 1){
        //alert("已经是第一页数据");
        toastr.error('已经是第一页数据!');
    }else{
        var studentId = $('#txt_studentId').val();
        var studentName = $('#txt_studentName').val();
        var className = $('#txt_className').val();
        var paperName = $('#txt_paperName').val();
        window.location.href = '/exam/admin/grades?page=1&sno='+studentId+'&name='+studentName+'&cname='+className+'&ename='+paperName;
    }
}

//目标页面
function targetPage(i) {
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    var paperName = $('#txt_paperName').val();
    window.location.href = '/exam/admin/grades?page='+i+'&sno='+studentId+'&name='+studentName+'&cname='+className+'&ename='+paperName;
}

// 尾页
function lastPage(){
    if(currPage == pageNum){
        //alert("已经是最后一页数据");
        toastr.error('已经是最后一页数据!');
    }else{
        var studentId = $('#txt_studentId').val();
        var studentName = $('#txt_studentName').val();
        var className = $('#txt_className').val();
        var paperName = $('#txt_paperName').val();
        window.location.href = '/exam/admin/candidates?page='+pageNum+'&sno='+studentId+'&name='+studentName+'&cname='+className+'&ename='+paperName;
    }
}

//ajax请求后台数据
function getAllGradePageInfo(){
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    var paperName = $('#txt_paperName').val();
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/admin/api/getGradeList",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{studentId:studentId, studentName:studentName, className:className,
            paperName:paperName, page:currPage, num:limit},
        success:function(data){
            //console.log(data);
            grades = data.data.grades;
            pageNum = data.data.pageNum;
            currPage = data.data.currPage;
            limit = data.data.size;
            allSize = data.data.allSize;
            listAllGradesInfo(grades);
            //console.log(papers);
        }
    });
}

//列出考生信息列表
function listAllGradesInfo(grades) {
    var s = '<thead><tr><th>#</th><th>学号</th><th>姓名</th><th>班级</th><th>专业</th><th>试卷</th><th>考试时间</th><th>得分</th></tr></thead>';
    $.each(grades,function(index,value){
        s+='<tr><th>'+value.id+'</th>';
        s+='<th>'+value.student.studentId+'</th>';
        s+='<th>'+value.student.studentName+'</th>';
        s+='<th>'+value.student.className+'</th>';
        s+='<th>'+value.student.profession+'</th>';
        s+='<th>'+value.paper.paperName+'</th>';
        s+='<th>'+formatTime(value.createTime,'Y-M-D h:m:s')+'</th>';
        s+='<th>'+value.score+'</th>';
        s+='</tr>';
    });
    s+="</tbody>";
    if(grades.length>0){
        $("#t_gradeList").html(s);
    }else{
        $("#t_gradeList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    if(grades.length>0){
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