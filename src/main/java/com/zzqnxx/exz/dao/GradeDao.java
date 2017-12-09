package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeDao {

    /**
     * 添加学生成绩
     * @param grade
     * @return
     */
    int insertGrade(@Param("grade") Grade grade);

    /**
     * 删除学生成绩
     * @param id
     * @return
     */
    int deleteGrade(int id);

    /**
     * 更新学生成绩
     * @param grade
     * @return
     */
    int updateGrade(@Param("grade") Grade grade);

    /**
     * 查询指定学生的成绩记录数量
     * @param sId
     * @return
     */
    int queryCountBySId(@Param("sId") int sId);

    /**
     * 查询指定学生的成绩
     * @param sId
     * @return
     */
    List<Grade> queryBySId(@Param("sId") int sId, @Param("offset") int offset,
                           @Param("limit") int limit);

    /**
     * 查询指定试卷的成绩记录数量
     * @param paperId
     * @return
     */
    int queryCountByPaperId(@Param("paperId") int paperId);

    /**
     * 查询指定试卷的成绩
     * @param paperId
     * @param offset
     * @param limit
     * @return
     */
    List<Grade> queryByPaperId(@Param("paperId") int paperId, @Param("offset") int offset,
                               @Param("limit") int limit);

    /**
     * 模糊查询指定学生指定试卷的成绩记录数量
     * @param paperId
     * @param studentId
     * @param studentName
     * @return
     */
    int queryCountByPaperId2StuId2StuName(@Param("paperId") int paperId, @Param("studentId") String studentId,
                                          @Param("studentName") String studentName);

    /**
     * 模糊查询指定学生指定试卷的成绩
     * @param paperId
     * @param studentId
     * @param studentName
     * @param offset
     * @param limit
     * @return
     */
    List<Grade> queryByPaperId2StuId2StuName(@Param("paperId") int paperId, @Param("studentId") String studentId,
                                             @Param("studentName") String studentName, @Param("offset") int offset,
                                             @Param("limit") int limit);
}
