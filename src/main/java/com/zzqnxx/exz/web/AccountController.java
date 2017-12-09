package com.zzqnxx.exz.web;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.dto.AjaxResult;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Teacher;
import com.zzqnxx.exz.service.StudentService;
import com.zzqnxx.exz.service.TeacherService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/exam/accounts")
public class AccountController {

    private static Log LOG = LogFactory.getLog(AccountController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HttpSession session;

    //考生登录页面
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView login() throws Exception {
        Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        ModelAndView modelAndView = new ModelAndView();
        if (student == null) {
            modelAndView.setViewName("accounts/login");
        } else {
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
        }
        return modelAndView;
    }

    @RequestMapping(value="/api/logout", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult logout() throws Exception {
        session.removeAttribute(Penguin.CURRENT_ACCOUNT);
        session.removeAttribute(Penguin.CURRENT_IDENTITY);
        return new AjaxResult().setData(true);
    }

    @RequestMapping(value="/api/checklogin", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult checklogin(@RequestParam("username") String username, @RequestParam("password") String password,
                                 @RequestParam("identity") String identity) throws Exception {
        if ("1".equals(identity)) {
            Student student = studentService.checkLogin(username, password);
            if(student != null){
                //缓存当前学生信息
                session.setAttribute(Penguin.CURRENT_ACCOUNT, student);
                session.setAttribute(Penguin.CURRENT_IDENTITY, "student");
                return new AjaxResult().setData(student);
            }
            return new AjaxResult().setMessage("账号不存在或者密码错误,请检查");
        } else if ("2".equals(identity)) {
            Teacher teacher = teacherService.checkLogin(username, password);
            if(teacher != null){
                //缓存当前学生信息
                session.setAttribute(Penguin.CURRENT_ACCOUNT, teacher);
                session.setAttribute(Penguin.CURRENT_IDENTITY, "teacher");
                return new AjaxResult().setData(teacher);
            }
            return new AjaxResult().setMessage("账号不存在或者密码错误,请检查");
        } else {
            return new AjaxResult().setMessage("请选择身份登录");
        }
    }

//    @RequestMapping(value="/api/checklogin", method= RequestMethod.POST)
//    @ResponseBody
//    public AjaxResult checklogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        String username  = request.getParameter("username");
//        String password = request.getParameter("password");
//        String identity = request.getParameter("identity");
//        String remember = request.getParameter("remember");
//        if ("1".equals(identity)) {
//            Student student = studentService.checkLogin(username, password);
//            if(student != null){
//                //缓存当前学生信息
//                session.setAttribute(Penguin.CURRENT_ACCOUNT, student);
//                //cookie记住密码
//                if (StringUtils.isNotEmpty(remember) && "1".equals(remember)) {
//                    Cookie cookie = new Cookie(Penguin.CURRENT_ACCOUNT,username+"-"+password);
//                    cookie.setMaxAge(60*60*24*3);// 保存
//                    response.addCookie(cookie);
//                    LOG.info("存储用户的cookie:"+username);
//                }
//                return new AjaxResult().setData(student);
//            }
//            return new AjaxResult().setMessage("账号不存在或者密码错误,请检查");
//        } else if ("2".equals(identity)) {
//            return new AjaxResult().setMessage("暂未处理");
//        } else {
//            return new AjaxResult().setMessage("请选择身份登录");
//        }
//    }

    @RequestMapping(value="/api/updatePassword", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult updatePassword(@RequestParam("id") int id, @RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("identity") String identity) throws Exception {
        if ("1".equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            if (!student.getPassword().equals(oldPassword)) {
                return new AjaxResult().setData(false);
            }
            int result = studentService.updatePassword(id, newPassword);
            if (result == 1) {
                return new AjaxResult().setData(true);
            } else {
                return new AjaxResult().setData(false);
            }
        } else if ("2".equals(identity)) {
            return new AjaxResult().setMessage("暂未处理");
        }
        return new AjaxResult().setData(false);
    }
}
