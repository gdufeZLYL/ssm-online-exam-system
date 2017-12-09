package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Teacher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    /**
     * 获取当前用户的主键ID
     * @return
     */
    int id(String identity);

    /**
     * 获取当前学生用户
     * @return
     */
    Object account();

    /**
     * 获取cookies中key对应的value
     * @param cookies
     * @param key
     * @return
     */
    String getCookie(Cookie[] cookies, String key);

    /**
     * 删除cookies中的key-value
     * @param response
     * @param key
     */
    void removeCookie(HttpServletResponse response, String key);
}
