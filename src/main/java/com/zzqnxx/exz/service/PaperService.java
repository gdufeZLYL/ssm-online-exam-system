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
}
