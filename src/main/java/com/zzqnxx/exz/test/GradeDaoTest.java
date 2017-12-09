package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.GradeDao;
import com.zzqnxx.exz.entity.Grade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GradeDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(GradeDaoTest.class);
    @Autowired
    private GradeDao gradeDao;

    @Test
    public void testInsertGrade() throws Exception {
        Grade grade = new Grade();
        grade.setsId(1);
        grade.setPaperId(10);
        grade.setScore(80);
        grade.setSingleScore(40);
        grade.setMulScore(40);
        grade.setOtherScore(0);
        int result = gradeDao.insertGrade(grade);
        LOG.info("result = " + result);
    }

    @Test
    public void testDeleteGrade() throws Exception {
        int result = gradeDao.deleteGrade(2);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdateGrade() throws Exception {
        Grade grade = new Grade();
        grade.setId(3);
        grade.setsId(1);
        grade.setPaperId(3);
        grade.setScore(85);
        grade.setSingleScore(45);
        grade.setMulScore(40);
        grade.setOtherScore(0);
        int result = gradeDao.updateGrade(grade);
        LOG.info("result = " + result);
    }

    @Test
    public void testQueryCountBySId() throws Exception {
        int ans = gradeDao.queryCountBySId(1);
        LOG.info("ans = " + ans);
    }

    @Test
    public void testQueryBySId() throws Exception {
        List<Grade> grades = gradeDao.queryBySId(2, 0, 50);
        for (Grade grade : grades) {
            LOG.info(grade);
        }
    }

    @Test
    public void testQueryCountByPaperId() throws Exception {
        int ans = gradeDao.queryCountByPaperId(2);
        LOG.info("ans = " + ans);
    }

    @Test
    public void testQueryByPaperId() throws Exception {
        List<Grade> grades = gradeDao.queryByPaperId(1, 0, 50);
        for (Grade grade : grades) {
            LOG.info(grade);
        }
    }

    @Test
    public void testQueryCountByPaperId2StuId2StuName() throws Exception {
        int ans = gradeDao.queryCountByPaperId2StuId2StuName(1, "", "李");
        LOG.info("ans = " + ans);
    }

    @Test
    public void testQueryByPaperId2StuId2StuName() throws Exception {
        List<Grade> grades = gradeDao.queryByPaperId2StuId2StuName(1, "", "", 0, 50);
        for (Grade grade : grades) {
            LOG.info(grade);
        }
    }
}
