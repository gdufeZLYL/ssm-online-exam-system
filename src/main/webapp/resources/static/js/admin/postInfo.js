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