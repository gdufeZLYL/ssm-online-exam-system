package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.PaperDao;
import com.zzqnxx.exz.entity.Paper;
import com.zzqnxx.exz.service.PaperService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("paperService")
public class PaperServiceImpl implements PaperService {
    private static Log LOG = LogFactory.getLog(PaperServiceImpl.class);
    @Autowired
    private PaperDao paperDao;

    public Map<String, Object> getPaperList(int page, int num) {
        int count = paperDao.queryAllCount();
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("papers", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("papers", new ArrayList<>());
            return data;
        }
        List<Paper> papers = paperDao.queryAll(num * (page - 1), num);
        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", papers.size());
        data.put("allSize", count);
        data.put("papers", papers);
        return data;
    }
}
