package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 根据试卷名称获取试卷信息
     * @param paperName
     * @return
     */
    List<Paper> queryByPaperName(@Param("paperName") String paperName);

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

    /**
     * 检查该试卷ID是否存在
     * @param paperId
     * @return
     */
    int checkPaperId(@Param("paperId") int paperId);

    /**
     * 获取试卷数量
     * @return
     */
    int queryCountByIdAndName(@Param("id") String id, @Param("paperName") String paperName);

    /**
     * 分页获取试卷信息
     * @param id
     * @param paperName
     * @param offset
     * @param limit
     * @return
     */
    List<Paper> queryByIdAndName(@Param("id") String id, @Param("paperName") String paperName,
                                 @Param("offset") int offset, @Param("limit") int limit);
}
