package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Subject;

import java.util.List;

public interface SubjectService {

    /**
     * 根据试卷ID和题目类型获取试题信息
     * @param pageId
     * @param titleType
     * @return
     */
    List<Subject> getSbjsByPageIdAndType(int pageId, int titleType);
}
