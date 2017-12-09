package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.PaperDao;
import com.zzqnxx.exz.entity.Paper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaperDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(StudentDaoTest.class);
    @Autowired
    private PaperDao paperDao;

    @Test
    public void testInsertPaper() throws Exception {
        Paper paper = new Paper();
        paper.setPaperName("试卷十");
        int result = paperDao.insertPaper(paper);
        LOG.info("result = " + result);
    }

    @Test
    public void testDeletePaper() throws Exception {
        int result = paperDao.deletePaper(2);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdatePaper() throws Exception {
        int result = paperDao.updatePaper(2, "试卷二(测试)");
        LOG.info("result = " + result);
    }

    @Test
    public void testQueryAllCount() throws Exception {
        int count = paperDao.queryAllCount();
        LOG.info("count = " + count);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Paper> papers = paperDao.queryAll(11, 5);
        System.out.println(papers==null);
        for (Paper paper : papers) {
            LOG.info(paper);
        }
    }
}
