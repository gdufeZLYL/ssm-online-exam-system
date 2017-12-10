package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {

    /**
     * 分页获取试卷
     * @param page
     * @param num
     * @return
     */
    public Map<String, Object> getPaperList(int page, int num);

    /**
     * 检查试卷ID是否存在
     * @param paperId
     * @return
     */
    public boolean isPaperIdExist(int paperId);
}
