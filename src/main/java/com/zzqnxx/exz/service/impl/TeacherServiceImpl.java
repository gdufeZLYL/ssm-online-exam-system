package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.TeacherDao;
import com.zzqnxx.exz.entity.Teacher;
import com.zzqnxx.exz.service.TeacherService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    private static Log LOG = LogFactory.getLog(StudentServiceImpl.class);
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher checkLogin(String teacherId, String password) {
        Teacher teacher = teacherDao.selectTeacherByTcId(teacherId);
        if (teacher != null && teacher.getPassword().equals(password)) {
            return teacher;
        }
        return null;
    }

    @Override
    public int updatePassword(int id, String password) {
        int result = teacherDao.updateTeacher(id, password);
        return result;
    }
}
