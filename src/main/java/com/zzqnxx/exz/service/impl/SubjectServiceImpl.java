package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.SubjectDao;
import com.zzqnxx.exz.entity.Subject;
import com.zzqnxx.exz.service.SubjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    private static Log LOG = LogFactory.getLog(SubjectServiceImpl.class);
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> getSbjsByPageIdAndType(int pageId, int titleType) {
        try {
            return subjectDao.queryByPaperId2TitleType(pageId, titleType);
        } catch (Exception e) {
            LOG.error(e, e);
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, Object> getSbjsByTitleAndPaperName(String title, String paperName, int page, int num) {
        int count = subjectDao.queryCountByTitleAndPaperName(title, paperName);
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("subjects", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("subjects", new ArrayList<>());
            return data;
        }
        List<Subject> subjects = subjectDao.queryByTitleAndPaperName(title, paperName, num * (page - 1), num);
        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", subjects.size());
        data.put("allSize", count);
        data.put("subjects", subjects);
        return data;
    }
}
