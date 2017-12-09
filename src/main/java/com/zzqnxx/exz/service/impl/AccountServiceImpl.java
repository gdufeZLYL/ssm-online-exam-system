package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Teacher;
import com.zzqnxx.exz.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private HttpSession session;

    @Override
    public int id(String identity) {
        if ("1".equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            return student.getId();
        } else {
            Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            return teacher.getId();
        }
    }

    @Override
    public Object account() {
        return session.getAttribute(Penguin.CURRENT_ACCOUNT);
    }

    @Override
    public String getCookie(Cookie[] cookies, String key) {
        String value = null;
        if (cookies != null) {
            try {
                for (Cookie cookie : cookies) {
                    if (cookie.getName() != null && cookie.getName().equals(key)) {
                        if(StringUtils.isNotEmpty(cookie.getValue())) {
                            value = cookie.getValue();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    @Override
    public void removeCookie(HttpServletResponse response, String key) {
        try {
            Cookie tokenCookie = new Cookie(key, "-1");
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
