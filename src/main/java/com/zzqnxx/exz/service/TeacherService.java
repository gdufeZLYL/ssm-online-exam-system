package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Teacher;

public interface TeacherService {

    /**
     * 登录验证
     * @param teacherId
     * @param password
     * @return
     */
    public Teacher checkLogin(String teacherId, String password);

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    public int updatePassword(int id, String password);
}
