package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperDao {

    /**
     * 添加试卷信息
     * @param paper
     * @return
     */
    int insertPaper(@Param("paper") Paper paper);

    /**
     * 删除试卷信息
     * @param id
     * @return
     */
    int deletePaper(int id);

    /**
     * 更新试卷信息
     * @param id
     * @param paperName
     * @return
     */
    int updatePaper(@Param("id") int id, @Param("paperName") String paperName);

    /**
     * 获取试卷数量
     * @return
     */
    int queryAllCount();

    /**
     * 分页获取试卷信息
     * @param offset
     * @param limit
     * @return
     */
    List<Paper> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
