//添加公告模态框触发
function addPostAction() {
    //清空输入框
    $('#txt_add_title').val('');
    $('#txt_add_content').val('');
    $('#addPostModel').modal("show");
}

//添加公告
function addPost() {
    var title = $('#txt_add_title').val();
    var content = $('#txt_add_content').val();
    console.log('title = ' + title);
    console.log('content = ' + content);

    if (title == '') {
        toastr.error('标题不能为空!');
    } else if (content == '') {
        toastr.error('内容不能为空!');
    } else {
        $.ajax({
            url : "/exam/admin/api/addPost",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                title: title,
                content: content,
                author: admin.teacherName
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                if (result.success == true) {
                    $('#addPostModel').modal("hide");
                    toastr.success('添加成功!');
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

//填充公告信息
function fillPostInfo(posts, postSize) {
    var postStr = '';
    for (var i = 0; i < postSize; i++) {
        postStr += '<div class="row" style="margin-bottom: 10px;">\n' +
            '                <div class="col-md-12">\n' +
            '                    <div class="panel panel-info">\n' +
            '                        <div class="panel-heading" style="font-size: 20px;font-weight: bolder;">'+posts[i].title+'</div>\n' +
            '                        <div class="panel-body" style="font-size: 15px;">'+posts[i].content+'</div>\n' +
            '                        <div class="panel-footer" style="font-size: 12px;">'+posts[i].author+' 发表于 '+formatTime(posts[i].createTime,'Y-M-D h:m:s')+'</div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>';
    }
    $('#t_postList').html(postStr);
}