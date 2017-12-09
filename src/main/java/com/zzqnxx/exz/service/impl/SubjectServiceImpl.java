package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.SubjectDao;
import com.zzqnxx.exz.entity.Subject;
import com.zzqnxx.exz.service.SubjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
