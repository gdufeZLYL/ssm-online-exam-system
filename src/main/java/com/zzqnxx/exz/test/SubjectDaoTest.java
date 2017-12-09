package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.SubjectDao;
import com.zzqnxx.exz.entity.Subject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubjectDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(StudentDaoTest.class);
    @Autowired
    private SubjectDao subjectDao;

    @Test
    public void testInsertSubject() throws Exception {
        Subject subject = new Subject();
        subject.setTitle("主机A向主机B连续发送了两个TCP报文段," +
                "其序号分包是70和100,如果A发送的第一个报文段丢失了," +
                "但第二个报文段达到了B,B在第二个报文段到达后向A发送确认," +
                "那么这个确认号是多少?");
        subject.setOptionA("100");
        subject.setOptionB("101");
        subject.setOptionC("70");
        subject.setOptionD("71");
        subject.setAnswer("C");
        subject.setParse("略");
        subject.setTitleType(0);
        subject.setPaperId(1);
        int result = subjectDao.insertSubject(subject);
        LOG.info("result = " + result);
    }

    @Test
    public void testDeleteSubject() throws Exception {
        int result = subjectDao.deleteSubject(2);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdateSubject() throws Exception {
        Subject subject = new Subject();
        subject.setId(2);
        subject.setTitle("主机A向主机B连续发送了两个TCP报文段," +
                "其序号分包是70和100,如果A发送的第一个报文段丢失了," +
                "但第二个报文段达到了B,B在第二个报文段到达后向A发送确认," +
                "那么这个确认号是多少?(测试)");
        subject.setOptionA("100");
        subject.setOptionB("101");
        subject.setOptionC("70");
        subject.setOptionD("71");
        subject.setAnswer("C");
        subject.setParse("略");
        subject.setTitleType(0);
        subject.setPaperId(1);
        int result = subjectDao.updateSubject(subject);
        LOG.info("result = " + result);
    }

    @Test
    public void testQueryAllCount() throws Exception {
        int count = subjectDao.queryAllCount();
        LOG.info("count = " + count);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Subject> subjectList = subjectDao.queryAll(1, 5);
        for (Subject subject : subjectList) {
            LOG.info(subject);
        }
    }

    @Test
    public void testQueryByPaperId2TitleType() throws Exception {
        List<Subject> subjectList = subjectDao.queryByPaperId2TitleType(1, 1);
        for (Subject subject : subjectList) {
            LOG.info(subject);
        }
    }

    @Test
    public void testQueryCountByTitle() throws Exception {
        int count = subjectDao.queryCountByTitle("主机B");
        LOG.info("count = " + count);
    }

    @Test
    public void testQueryByTitle() throws Exception {
        List<Subject> subjectList = subjectDao.queryByTitle("主机B", 1, 5);
        for (Subject subject : subjectList) {
            LOG.info(subject);
        }
    }
}
