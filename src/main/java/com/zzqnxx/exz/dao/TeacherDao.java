package com.zzqnxx.exz.dao;
import com.zzqnxx.exz.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherDao {

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    int insertTeacher(@Param("teacher") Teacher teacher);

    /**
     * 删除教师信息(By id)
     * @param id
     * @return
     */
    int deleteTeacher(int id);

    /**
     * 更新教师信息
     * @param id
     * @param password
     * @return
     */
    int updateTeacher(@Param("id") int id, @Param("password") String password);

    /**
     * 查询教师(By teacherId and password)
     * @param teacherId
     * @param password
     * @return
     */
    Teacher selectTeacherByTcIdAndPwd(@Param("teacherId") String teacherId,
                                      @Param("password") String password);

    /**
     * 查询教师(By teacherId)
     * @param teacherId
     * @return
     */
    Teacher selectTeacherByTcId(@Param("teacherId") String teacherId);
}
