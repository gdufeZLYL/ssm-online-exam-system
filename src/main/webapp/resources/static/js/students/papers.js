// 首页
function firstPage(){
    if(currPage == 1){
        alert("已经是第一页数据");
    }else{
        window.location.href = "/exam/student/papers/1";
    }
}

//目标页面
function targetPage(i) {
    window.location.href = "/exam/student/papers/"+i;
}

// 尾页
function lastPage(){
    if(currPage == pageNum){
        alert("已经是最后一页数据");
    }else{
        window.location.href = "/exam/student/papers/"+pageNum;
    }
}

//进入考试
function enterExam(paperId, paperName) {
    window.location.href = "/exam/student/paper/"+paperId+"/"+paperName;
}

//修改密码模态框触发
function updatePwdAction() {
    $('#updatePwd').modal("show");
}
//修改密码触发
function updatePassword() {
    var oldPassword = $('#txt_oldPassword').val();
    var newPassword = $('#txt_newPassword').val();
    var rePassword = $('#txt_rePassword').val();
    if(oldPassword == "") {
        //alert("原密码不可为空!");
        toastr.error('原密码不可为空!');
    } else if(newPassword == ""){
        //alert("新密码不可为空!");
        toastr.error('新密码不可为空!');
    } else if(rePassword == "0") {
        //alert("确认密码不能为空!");
        toastr.error('确认密码不能为空!');
    } else if(rePassword != newPassword) {
        //alert("新密码与确认密码不一样!");
        toastr.error('新密码与确认密码不一样!');
    } else {
        $.ajax({
            type: "post",
            async: false,
            url: "/exam/accounts/api/updatePassword",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: {id: student.id, oldPassword: oldPassword, newPassword: newPassword, identity: "1"},
            success: function (data) {
                console.log(data);
                if (data.data == true) {
                    $('#updatePwd').modal('hide');
                    toastr.success('修改成功!');
                } else {
                    toastr.error('修改失败!');
                }
            }
        });
    }
}
//退出系统触发
function logoutAction() {
    $('#logoutModal').modal('show');
    //确认提交试卷
    $("#logoutConfirm").on("click", function() {
        $("#logoutModal").modal("hide");
        $.ajax({
            type: "post",
            async: false,
            url: "/exam/accounts/api/logout",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success: function (data) {
                console.log(data);
                if (data.data == true) {
                    window.location.href="/exam/accounts/login";
                } else {
                    toastr.error('退出系统失败!');
                }
            }
        });
    });
}
//首页触发
function indexAction() {
    window.location.href="/exam/student/index";
}
//成绩查询触发
function queryScoreAction() {
    window.location.href = "/exam/student/scores/1";
}

//ajax请求后台数据
function getPaperPageInfo(){
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/student/api/getPaperList",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{page:currPage, num:limit},
        success:function(data){
            //console.log(data);
            papers = data.data.papers;
            pageNum = data.data.pageNum;
            currPage = data.data.currPage;
            limit = data.data.size;
            allSize = data.data.allSize;
            listPapersInfo(papers);
            //console.log(papers);
        }
    });
}

//表格数据填充
function listPapersInfo(papers){
    var s = '<thead><tr class="exam_table_title"><th style="font-weight: bolder">#</th><th style="font-weight: bolder">Contest Name</th><th style="font-weight: bolder">Duration</th><th style="font-weight: bolder">Status</th></tr></thead><tbody>';
    $.each(papers,function(index,value){
        s+='<tr><td>'+value.id+'</td>';
        s+='<td  style="color: #1C86EE;font-weight: bolder;font-size: 16px;font-family: 微软雅黑">'+value.paperName+'</td>';
        s+='<td>'+formatTime(value.createTime,'Y-M-D h:m:s')+'</td>';
        s+='<td><a href="#" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" onclick="enterExam('+value.id+',\''+value.paperName+'\');">'+'进入考试'+'</a></td></tr>';
    });
    s+="</tbody>";
    if(papers.length>0){
        $("#t_paperList").html(s);
    }else{
        $("#t_paperList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    if(papers.length>0){
        var subPageStr = '<ul class="pagination">';
        subPageStr += '<li><a>'+allSize+'&nbsp;条记录&nbsp;'+currPage+'/'+pageNum+'&nbsp;页</a></li>';
        subPageStr += '<li><a href="#" onclick="firstPage();">首页</a></li>';
        var currGroup = Math.floor((currPage-1)/perPage);
        //console.log(currGroup);
        if (currGroup > 0) subPageStr += '<li><a href="#" onclick="targetPage('+((currGroup-1)*perPage+1)+');"><i class="layui-icon">&#xe65a;</i></a></li>';
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
        if (currGroup < Math.floor((pageNum-1)/perPage)) subPageStr += "<li><a href=\"#\" onclick=\"targetPage("+((currGroup+1)*perPage+1)+");\"><i class=\"layui-icon\">&#xe65b;</i></a></li>";
        subPageStr += '<li><a href="#" onclick="lastPage();">尾页</a></li>';
        $("#subPage").html(subPageStr);
    }
}

//填充个人信息
function fillStudentInfo(stuIdMd5, studentName) {
    //<img src="http://cn.gravatar.com/avatar/7a57a5a743894a0e?s=8&d=identicon&r=PG" class="layui-nav-img">曾庆熙
    var str = '<img src="http://cn.gravatar.com/avatar/'+stuIdMd5+'?s=8&d=identicon&r=PG" class="layui-nav-img">'+studentName;
    $("#studentInfo").html(str);
}