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

//在线考试触发
function onlineExamAction() {
    window.location.href="/exam/student/papers/1";
}

//成绩查询触发
function queryScoreAction() {
    window.location.href="/exam/student/scores/1";
}

//填充个人信息
function fillStudentInfo(stuIdMd5, studentName) {
    //<img src="http://cn.gravatar.com/avatar/7a57a5a743894a0e?s=8&d=identicon&r=PG" class="layui-nav-img">曾庆熙
    var str = '<img src="http://cn.gravatar.com/avatar/'+stuIdMd5+'?s=8&d=identicon&r=PG" class="layui-nav-img">'+studentName;
    $("#studentInfo").html(str);
}

//填充公告信息
function fillPostInfo(posts, postSize) {
    var postStr = '';
    for (var i = 0; i < postSize; i++) {
        if (i != postSize-1 && i%2 == 0) {
            postStr += '<div class="cd-timeline-block">\n' +
                '                <div class="cd-timeline-img cd-picture">\n' +
                '                    <img src="/resources/static/images/cd-icon-picture.svg" alt="Picture">\n' +
                '                </div>\n' +
                '                <div class="cd-timeline-content">\n' +
                '                    <p style="font-size: 20px;font-weight: bold;">'+posts[i].title+'</p>\n' +
                '                    <p style="font-size: 15px">'+posts[i].content+'</p>\n' +
                '                    <span class="cd-date">'+posts[i].author+' 发表于 '+formatTime(posts[i].createTime,'Y-M-D h:m:s')+'</span>\n' +
                '                </div>\n' +
                '            </div>';
        } else if(i != postSize-1 && i%2 == 1) {
            postStr += '<div class="cd-timeline-block">\n' +
                '                <div class="cd-timeline-img cd-movie">\n' +
                '                    <img src="/resources/static/images/cd-icon-movie.svg" alt="Movie">\n' +
                '                </div>\n' +
                '\n' +
                '                <div class="cd-timeline-content">\n' +
                '                    <p style="font-size: 20px;font-weight: bold;">'+posts[i].title+'</p>\n' +
                '                    <p style="font-size: 15px">'+posts[i].content+'</p>\n' +
                '                    <span class="cd-date">'+posts[i].author+' 发表于 '+formatTime(posts[i].createTime,'Y-M-D h:m:s')+'</span>\n' +
                '                </div>\n' +
                '            </div>';
        } else if(i == postSize-1) {
            postStr += '<div class="cd-timeline-block">\n' +
                '                <div class="cd-timeline-img cd-movie">\n' +
                '                    <img src="/resources/static/images/cd-icon-location.svg" alt="Location">\n' +
                '                </div>\n' +
                '                <div class="cd-timeline-content">\n' +
                '                    <p style="font-size: 20px;font-weight: bold;">'+posts[i].title+'</p>\n' +
                '                    <p style="font-size: 15px">'+posts[i].content+'</p>\n' +
                '                    <span class="cd-date">'+posts[i].author+' 发表于 '+formatTime(posts[i].createTime,'Y-M-D h:m:s')+'</span>\n' +
                '                </div>\n' +
                '            </div>';
        }
        $('#cd-timeline').html(postStr);
    }
}
