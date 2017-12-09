package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    /**
     * 添加学生信息
     * @param student
     * @return
     */
    int insertStudent(@Param("student") Student student);

    /**
     * 删除学生信息(By id)
     * @param id
     * @return
     */
    int deleteStudent(int id);

    /**
     * 更新学生信息
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id") int id, @Param("password") String password);

    /**
     * 更新考生信息
     * @param student
     * @return
     */
    int updateStudent(@Param("student") Student student);

    /**
     * 查询学生(By studentId and password)
     * @param studentId
     * @param password
     * @return
     */
    Student selectStudentByStuIdAndPwd(@Param("studentId") String studentId,
                                       @Param("password") String password);

    /**
     * 查询学生(By studentId)
     * @param studentId
     * @return
     */
    Student selectStudentByStuId(@Param("studentId") String studentId);

    /**
     * 获取学生数量
     * @return
     */
    int queryAllCount(@Param("studentId") String studentId,
                      @Param("studentName") String studentName,
                      @Param("className") String className);

    /**
     * 分页获取学生列表
     * @param studentId
     * @param studentName
     * @param className
     * @return
     */
    List<Student> queryStuByStuIdAndStuNameAndClassName(@Param("studentId") String studentId,
                                                        @Param("studentName") String studentName,
                                                        @Param("className") String className,
                                                        @Param("offset") int offset,
                                                        @Param("limit") int limit);
}
