package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.TeacherDao;
import com.zzqnxx.exz.entity.Teacher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(TeacherDaoTest.class);
    @Autowired
    private TeacherDao teacherDao;

    @Test
    public void testInsertTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setTeacherId("test");
        teacher.setPassword("zzqnxx");
        teacher.setTeacherName("李一");
        int result = teacherDao.insertTeacher(teacher);
        LOG.info("result = " + result);
    }

    @Test
    public void testDeleteTeacher() throws Exception {
        int result = teacherDao.deleteTeacher(2);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdateTeacher() throws Exception {
        int result = teacherDao.updateTeacher(2, "123456");
        LOG.info("result = " + result);
    }

    @Test
    public void testSelectTeacherByTcIdAndPwd() throws Exception {
        Teacher teacher = teacherDao.selectTeacherByTcIdAndPwd("admin", "zzqnxx");
        LOG.info("teacher = " + teacher);
    }
}
