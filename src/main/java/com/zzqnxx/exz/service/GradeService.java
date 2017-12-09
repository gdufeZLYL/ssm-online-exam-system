package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Grade;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface GradeService {

    /**
     * 提交成绩
     * @param grade
     * @return
     */
    int submitGrade(Grade grade);

    /**
     * 分页获取指定学生成绩列表信息
     * @param sId
     * @param page
     * @param num
     * @return
     */
    public Map<String, Object> getGradeList(int sId, int page, int num);

    /**
     * 分页获取所有学生成绩列表信息
     * @param studentId
     * @param studentName
     * @param className
     * @param paperName
     * @param page
     * @param num
     * @return
     */
    public Map<String, Object> getAllGradeList(String studentId, String studentName, String className,
                                            String paperName, int page, int num);
}
