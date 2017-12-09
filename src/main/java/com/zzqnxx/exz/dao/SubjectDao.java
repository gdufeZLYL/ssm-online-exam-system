package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectDao {

    /**
     * 添加试题信息
     * @param subject
     * @return
     */
    int insertSubject(@Param("subject") Subject subject);

    /**
     * 删除试题信息
     * @param id
     * @return
     */
    int deleteSubject(int id);

    /**
     * 更新试题信息
     * @param subject
     * @return
     */
    int updateSubject(@Param("subject") Subject subject);

    /**
     * 获取试题信息数量
     * @return
     */
    int queryAllCount();

    /**
     * 分页获取试题信息
     * @param offset
     * @param limit
     * @return
     */
    List<Subject> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 获取试题信息(By paperId and titleType)
     * @param paperId
     * @return
     */
    List<Subject> queryByPaperId2TitleType(@Param("paperId") int paperId, @Param("titleType") int titleType);

    /**
     * 分页模糊查询匹配题目的试题数量
     * @param title
     * @return
     */
    int queryCountByTitle(@Param("title") String title);

    /**
     * 分页模糊查询匹配题目的试题
     * @param title
     * @param offset
     * @param limit
     * @return
     */
    List<Subject> queryByTitle(@Param("title") String title, @Param("offset") int offset,
                               @Param("limit") int limit);
}
