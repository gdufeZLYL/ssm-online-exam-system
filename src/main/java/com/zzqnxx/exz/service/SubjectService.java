package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {

    /**
     * 根据试卷ID和题目类型获取试题信息
     * @param pageId
     * @param titleType
     * @return
     */
    List<Subject> getSbjsByPageIdAndType(int pageId, int titleType);

    /**
     * 分页获取所有试题列表信息
     * @param title
     * @param paperName
     * @param page
     * @param num
     * @return
     */
    public Map<String, Object> getSbjsByTitleAndPaperName(String title, String paperName, int page, int num);

    /**
     * 增加试题
     * @param subject
     * @return
     */
    public int addSubject(Subject subject);

    /**
     * 更新试题
     * @param subject
     * @return
     */
    public int updateSubject(Subject subject);

    /**
     * 删除试题
     * @param id
     * @return
     */
    public int deleteSubject(int id);
}
