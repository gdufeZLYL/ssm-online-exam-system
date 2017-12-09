//正在提交试卷
function submiting() {
    $("#waitSubmit").modal("show");
    statAnswer();
    setTimeout(function () {
        $("#waitSubmit").modal("hide");
        window.location.href="/exam/student/papers/1";
    }, 5000);
}
//获取单选题试题列表
function getSgPapers(paperId) {
    //获取单选题列表
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/student/api/getPaper",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{paperId:paperId, titleType:0},
        success:function(data){
            sgSubjects = data.data.subjects;
            sgSubjectSize = data.data.sbSize;
        }
    });
}

//获取多选题试题列表
function getDbPapers(paperId) {
    //获取多选题列表
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/student/api/getPaper",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{paperId:paperId, titleType:1},
        success:function(data){
            dbSubjects = data.data.subjects;
            dbSubjectSize = data.data.sbSize;
        }
    });
}

//计时
/*
var mintime = 0; //一个小时，按秒计算，自己调整!
function CountUp() {
    minutes = Math.floor(mintime / 60);
    seconds = Math.floor(mintime % 60);
    msg = "计时: 00:" + formatNumber(minutes) + ":" + formatNumber(seconds);
    document.all["currtimer"].innerHTML = msg;
    ++mintime;
}
currtimer = setInterval("CountUp()", 1000);
*/

//填充试题
function fillSubject(sgSubjects, sgSubjectSize, dbSubjects, dbSubjectSize) {
    var sgTbStr = '';   //单选题试题信息
    var dbTbStr = '';   //多选题试题信息
    for (var i = 0; i < sgSubjectSize; i++) {
        sgTbStr += '<tr><td><p style="font-size: 16px;font-weight: bold;">'+(i+1)+'. '+sgSubjects[i].title+'</p></td></tr>';
        sgTbStr += '<tr><td><input class="magic-radio" type="radio" name="'+sgSubjects[i].id+'" value="A" id="'+sgSubjects[i].id+'A" checked/><label for="'+sgSubjects[i].id+'A" style="font-size: 16px;">A.&nbsp;'+sgSubjects[i].optionA+'</label></td></tr>';
        sgTbStr += '<tr><td><input class="magic-radio" type="radio" name="'+sgSubjects[i].id+'" value="B" id="'+sgSubjects[i].id+'B"/><label for="'+sgSubjects[i].id+'B" style="font-size: 16px;">B.&nbsp;'+sgSubjects[i].optionB+'</label></td></tr>';
        sgTbStr += '<tr><td><input class="magic-radio" type="radio" name="'+sgSubjects[i].id+'" value="C" id="'+sgSubjects[i].id+'C"/><label for="'+sgSubjects[i].id+'C" style="font-size: 16px;">C.&nbsp;'+sgSubjects[i].optionC+'</label></td></tr>';
        sgTbStr += '<tr><td><input class="magic-radio" type="radio" name="'+sgSubjects[i].id+'" value="D" id="'+sgSubjects[i].id+'D"/><label for="'+sgSubjects[i].id+'D" style="font-size: 16px;">D.&nbsp;'+sgSubjects[i].optionD+'</label></td></tr>';
    }
    $('#exam_single_subject_table').html(sgTbStr);
    for (var i = 0; i < dbSubjectSize; i++) {
        dbTbStr += '<tr><td><p style="font-size: 16px;font-weight: bold;">'+(i+1)+'. '+dbSubjects[i].title+'</p></td></tr>';
        dbTbStr += '<tr><td><input class="magic-checkbox" type="checkbox" name="'+dbSubjects[i].id+'" value="A" id="'+dbSubjects[i].id+'A" checked/><label for="'+dbSubjects[i].id+'A" style="font-size: 16px;">A.&nbsp;'+dbSubjects[i].optionA+'</label></td></tr>';
        dbTbStr += '<tr><td><input class="magic-checkbox" type="checkbox" name="'+dbSubjects[i].id+'" value="B" id="'+dbSubjects[i].id+'B"/><label for="'+dbSubjects[i].id+'B" style="font-size: 16px;">B.&nbsp;'+dbSubjects[i].optionB+'</label></td></tr>';
        dbTbStr += '<tr><td><input class="magic-checkbox" type="checkbox" name="'+dbSubjects[i].id+'" value="C" id="'+dbSubjects[i].id+'C"/><label for="'+dbSubjects[i].id+'C" style="font-size: 16px;">C.&nbsp;'+dbSubjects[i].optionC+'</label></td></tr>';
        dbTbStr += '<tr><td><input class="magic-checkbox" type="checkbox" name="'+dbSubjects[i].id+'" value="D" id="'+dbSubjects[i].id+'D"/><label for="'+dbSubjects[i].id+'D" style="font-size: 16px;">D.&nbsp;'+dbSubjects[i].optionD+'</label></td></tr>';
    }
    $('#exam_double_subject_table').html(dbTbStr);
    $('#studentName').html('考&nbsp;&nbsp;生:&nbsp;'+student.studentName);
}

//提交试卷
function submitPaper() {
    // if (confirm("确定要提交试卷？")) {
    //     statAnswer();
    // }
    $("#submitConfirm").modal("show");
    //确认提交试卷
    $("#confirmOk").on("click", function() {
        $("#submitConfirm").modal("hide");
        submiting();
    });
}

//统计答案,ajax传值
function statAnswer() {
    var answer = '{';
    for (var i = 0; i < sgSubjectSize; i++) {
        var chooses = document.getElementsByName(sgSubjects[i].id);
        for (var j = 0; j < chooses.length; j++) {
            if(chooses[j].checked){
                answer+='"'+sgSubjects[i].id+'":"'+chooses[j].value+'",';
                break;
            }
        }
    }
    for (var i = 0; i < dbSubjectSize; i++) {
        var chooses = document.getElementsByName(dbSubjects[i].id);
        answer += '"'+dbSubjects[i].id+'":"';
        for (var j = 0; j < chooses.length; j++) {
            if(chooses[j].checked){
                answer+=chooses[j].value;
            }
            //console.log(chooses[j].value);
        }
        answer += '"';
        if (i < dbSubjectSize-1) answer += ',';
    }
    answer += '}';
    //answer = '"'+answer+'"';
    //alert('answer = ' + answer);
    $.ajax({
        type:"post",
        async:false,
        url:"/exam/student/api/submitPaper",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data:{paperId:paperId, answer:answer},
        success:function(data){
            console.log(data);
        }
    });
}
