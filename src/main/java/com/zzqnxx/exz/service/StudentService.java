package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Student;

import java.util.Map;

public interface StudentService {

    /**
     * 添加考生信息
     * @param student
     * @return
     */
    public int addStudent(Student student);

    /**
     * 删除考生信息
     * @param id
     * @return
     */
    public int delStudent(int id);

    /**
     * 更新考生信息
     * @param student
     * @return
     */
    public int updateStudent(Student student);

    /**
     * 登录验证
     * @param studentId
     * @param password
     * @return
     */
    public Student checkLogin(String studentId, String password);

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    public int updatePassword(int id, String password);

    /**
     * 分页查询考生信息
     * @param page
     * @param num
     * @return
     */
    public Map<String, Object> getStudentList(String studentId, String studentName,
                                              String className,int page, int num);
}
