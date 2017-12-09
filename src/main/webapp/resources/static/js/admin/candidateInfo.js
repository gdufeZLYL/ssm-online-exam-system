//添加考生信息模态款触发
function addStuAction() {
    //清空输入框
    $('#txt_add_sno').val('');
    $('#txt_add_sname').val('');
    $('#txt_add_icard').val('');
    $('#txt_add_pwd').val('');
    $('#txt_add_profess').val('');
    $('#txt_add_cname').val('');
    $('#addStuModel').modal("show");
}

//添加考生
function addStudent() {
    var studentId = $('#txt_add_sno').val();
    var studentName = $('#txt_add_sname').val();
    var gender = '';
    if ($('#txt_add_gender_man').is(":checked")) {
        gender = '男';
    } else {
        gender = '女';
    }
    var idCard = $('#txt_add_icard').val();
    var password = $('#txt_add_pwd').val();
    var profession = $('#txt_add_profess').val();
    var className = $('#txt_add_cname').val();

    if (studentId == '') {
        toastr.error('学号不能为空!');
    } else if (studentName == '') {
        toastr.error('姓名不能为空!');
    } else if (idCard == '') {
        toastr.error('身份证不能为空!');
    } else if (profession == '') {
        toastr.error('专业不能为空!');
    } else if (className == '') {
        toastr.error('班级名称不能为空!');
    } else if (password == '') {
        toastr.error('密码不能为空!');
    } else {
        $.ajax({
            url : "/exam/admin/api/addStudent",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                studentId : studentId,
                studentName: studentName,
                gender : gender,
                idCard : idCard,
                password : password,
                profession : profession,
                className : className
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                if (result.success == true) {
                    $('#addStuModel').modal("hide");
                    toastr.success('添加成功!');
                    //添加成功刷新页面
                    // var studentId = $('#txt_studentId').val();
                    // var studentName = $('#txt_studentName').val();
                    // var className = $('#txt_className').val();
                    // window.location.href = '/exam/admin/candidates?page='+currPage+'&sno='+studentId+'&name='+studentName+'&cname='+className;
                    window.location.reload();
                } else {
                    toastr.error('添加失败o(╥﹏╥)o!');
                }
            },
            error:function(result){
                toastr.error('添加失败o(╥﹏╥)o!');
            }
        });
    }
}

//编辑考生信息模态款触发
function updateStuAction(index) {
    $('#txt_update_id').val(students[index].id);
    $('#txt_update_sno').val(students[index].studentId);
    $('#txt_update_sname').val(students[index].studentName);
    $('#txt_update_icard').val(students[index].idCard);
    $('#txt_update_pwd').val(students[index].password);
    $('#txt_update_profess').val(students[index].profession);
    $('#txt_update_cname').val(students[index].className);
    if (students[index].gender == '男') {
        $('#txt_update_gender').html('<label class="radio-inline">\n' +
            '                                <input type="radio" name="txt_update_gender" id="txt_update_gender_man" value="1" checked> 男\n' +
            '                            </label>\n' +
            '                            <label class="radio-inline">\n' +
            '                                <input type="radio" name="txt_update_gender" id="txt_update_gender_woman" value="2"> 女\n' +
            '                            </label>')
    } else {
        $('#txt_update_gender').html('<label class="radio-inline">\n' +
            '                                <input type="radio" name="txt_update_gender" id="txt_update_gender_man" value="1"> 男\n' +
            '                            </label>\n' +
            '                            <label class="radio-inline">\n' +
            '                                <input type="radio" name="txt_update_gender" id="txt_update_gender_woman" value="2" checked> 女\n' +
            '                            </label>');
    }
    $('#updateStuModel').modal("show");
}

//修改考生信息
function updateStudent() {
    var id = $('#txt_update_id').val();
    console.log('id = ' + id);
    var studentId = $('#txt_update_sno').val();
    var studentName = $('#txt_update_sname').val();
    var gender = '';
    if ($('#txt_update_gender_man').is(":checked")) {
        gender = '男';
    } else {
        gender = '女';
    }
    var idCard = $('#txt_update_icard').val();
    var password = $('#txt_update_pwd').val();
    var profession = $('#txt_update_profess').val();
    var className = $('#txt_update_cname').val();

    if (studentId == '') {
        toastr.error('学号不能为空!');
    } else if (studentName == '') {
        toastr.error('姓名不能为空!');
    } else if (idCard == '') {
        toastr.error('身份证不能为空!');
    } else if (profession == '') {
        toastr.error('专业不能为空!');
    } else if (className == '') {
        toastr.error('班级名称不能为空!');
    } else if (password == '') {
        toastr.error('密码不能为空!');
    } else {
        $.ajax({
            url : "/exam/admin/api/updateStudent",
            type : "POST",
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            <!-- 向后端传输的数据 -->
            data : JSON.stringify({
                id : id,
                studentId : studentId,
                studentName: studentName,
                gender : gender,
                idCard : idCard,
                password : password,
                profession : profession,
                className : className
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                if (result.success == true) {
                    $('#addStuModel').modal("hide");
                    toastr.success('更改成功!');
                    //更改成功刷新页面
                    // var studentId = $('#txt_studentId').val();
                    // var studentName = $('#txt_studentName').val();
                    // var className = $('#txt_className').val();
                    // window.location.href = '/exam/admin/candidates?page='+currPage+'&sno='+studentId+'&name='+studentName+'&cname='+className;
                    window.location.reload();
                } else {
                    toastr.error('更改失败o(╥﹏╥)o!');
                }
            },
            error:function(result){
                toastr.error('更改失败o(╥﹏╥)o!');
            }
        });
    }
}

//删除考生信息模态框触发
function delStuAction(id) {
    $('#txt_del_id').val(id);
    $('#delStuModal').modal("show");
}

//删除考生信息
function delStudent() {
    var id = $('#txt_del_id').val();
    $.ajax({
        type:"DELETE",
        async:false,
        url:"/exam/admin/api/delStudent/"+id,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success:function(result) {
            <!-- 处理后端返回的数据 -->
            if (result.success == true) {
                $('#delStuModal').modal("hide");
                toastr.success('删除成功!');
                //删除成功刷新页面
                // var studentId = $('#txt_studentId').val();
                // var studentName = $('#txt_studentName').val();
                // var className = $('#txt_className').val();
                // window.location.href = '/exam/admin/candidates?page='+currPage+'&sno='+studentId+'&name='+studentName+'&cname='+className;
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
function queryStuAction() {
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    window.location.href = '/exam/admin/candidates?page=1&sno='+studentId+'&name='+studentName+'&cname='+className;
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
        window.location.href = '/exam/admin/candidates?page=1&sno='+studentId+'&name='+studentName+'&cname='+className;
    }
}

//目标页面
function targetPage(i) {
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    window.location.href = '/exam/admin/candidates?page='+i+'&sno='+studentId+'&name='+studentName+'&cname='+className;
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
        window.location.href = '/exam/admin/candidates?page='+pageNum+'&sno='+studentId+'&name='+studentName+'&cname='+className;
    }
}

//ajax请求后台数据
function getStudentPageInfo(){
    var studentId = $('#txt_studentId').val();
    var studentName = $('#txt_studentName').val();
    var className = $('#txt_className').val();
    console.log('studentId = ' + studentId);
    console.log('studentName = ' + studentName);
    console.log('className = ' + className);
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/admin/api/getStudentList",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{studentId:studentId, studentName:studentName, className:className, page:currPage, num:limit},
        success:function(data){
            //console.log(data);
            students = data.data.students;
            pageNum = data.data.pageNum;
            currPage = data.data.currPage;
            limit = data.data.size;
            allSize = data.data.allSize;
            listStudentsInfo(students);
            //console.log(papers);
        }
    });
}

//列出考生信息列表
function listStudentsInfo(students) {
    var s = '<thead><tr><th>#</th><th>学号</th><th>姓名</th><th>性别</th><th>身份证</th><th>密码</th><th>专业</th><th>班级</th><th>操作</th></tr></thead><tbody>';
    $.each(students,function(index,value){
        s+='<tr><th>'+value.id+'</th>';
        s+='<th>'+value.studentId+'</th>';
        s+='<th>'+value.studentName+'</th>';
        s+='<th>'+value.gender+'</th>';
        s+='<th>'+value.idCard+'</th>';
        s+='<th>'+value.password+'</th>';
        s+='<th>'+value.profession+'</th>';
        s+='<th>'+value.className+'</th>';
        s+='<th><button type="button" class="btn btn-primary btn-xs" onclick="updateStuAction('+index+')">编辑</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delStuAction('+students[index].id+')">删除</button></th></tr>';
    });
    s+="</tbody>";
    if(students.length>0){
        $("#t_studentList").html(s);
    }else{
        $("#t_studentList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
    }
    if(students.length>0){
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