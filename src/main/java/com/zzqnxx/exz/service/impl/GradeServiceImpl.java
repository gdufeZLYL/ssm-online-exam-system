package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.GradeDao;
import com.zzqnxx.exz.entity.Grade;
import com.zzqnxx.exz.service.GradeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    private static Log LOG = LogFactory.getLog(GradeServiceImpl.class);
    @Autowired
    private GradeDao gradeDao;

    @Override
    public int submitGrade(Grade grade) {
        return gradeDao.insertGrade(grade);
    }

    @Override
    public Map<String, Object> getGradeList(int sId, int page, int num) {
        int count = gradeDao.queryCountBySId(sId);
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        List<Grade> grades = gradeDao.queryBySId(sId, num * (page - 1), num);
        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", grades.size());
        data.put("allSize", count);
        data.put("grades", grades);
        return data;
    }
}
