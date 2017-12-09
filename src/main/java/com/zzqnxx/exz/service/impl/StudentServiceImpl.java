package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.StudentDao;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private static Log LOG = LogFactory.getLog(StudentServiceImpl.class);
    @Autowired
    private StudentDao studentDao;

    /**
     * 添加考生信息
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        int result = studentDao.insertStudent(student);
        return result;
    }

    /**
     * 更新考生信息
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    /**
     * 登录验证
     * @param studentId
     * @param password
     * @return
     */
    public Student checkLogin(String studentId, String password) {
        Student student = studentDao.selectStudentByStuId(studentId);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }

    @Override
    public int delStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    @Override
    public int updatePassword(int id, String password) {
        int result = studentDao.updatePassword(id, password);
        return result;
    }

    @Override
    public Map<String, Object> getStudentList(String studentId, String studentName,
                                              String className,int page, int num) {
        int count = studentDao.queryAllCount(studentId, studentName, className);
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("students", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("students", new ArrayList<>());
            return data;
        }
        List<Student> students = studentDao.queryStuByStuIdAndStuNameAndClassName(studentId,
                studentName, className, num * (page - 1), num);
        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", students.size());
        data.put("allSize", count);
        data.put("students", students);
        return data;
    }
}
