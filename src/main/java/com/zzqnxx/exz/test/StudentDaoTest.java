package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.StudentDao;
import com.zzqnxx.exz.entity.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(StudentDaoTest.class);
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testInsertStudent() throws Exception {
        Student student = new Student();
        student.setStudentId("14251104202");
        student.setStudentName("李一");
        student.setGender("男");
        student.setIdCard("440105199911117914");
        student.setPassword("123456");
        student.setProfession("软件工程");
        int result = studentDao.insertStudent(student);
        LOG.info("result = " + result);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        int result = studentDao.deleteStudent(2);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdateStudent() throws Exception {
        int result = studentDao.updatePassword(2, "345678");
        LOG.info("result = " + result);
    }

    @Test
    public void testSelectStudentByStuIdAndPwd() throws Exception {
        Student student = studentDao.selectStudentByStuIdAndPwd("14251104201", "123");
        LOG.info("student = " + student);
    }

    @Test
    public void testSelectStudentByStuId() throws Exception {
        Student student = studentDao.selectStudentByStuId("14251104202");
        LOG.info("student = " + student);
    }
}
