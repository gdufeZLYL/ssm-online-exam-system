package com.zzqnxx.exz.web;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.dto.AjaxResult;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Teacher;
import com.zzqnxx.exz.service.StudentService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value="/exam/admin")
public class AdminController {

    private static Log LOG = LogFactory.getLog(AccountController.class);
    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpSession session;

    //管理员登录页面
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView login() throws Exception {
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        ModelAndView modelAndView = new ModelAndView();
        if (teacher == null) {
            modelAndView.setViewName("accounts/loginAdmin");
        } else {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
            modelAndView.addObject("page", 1);
            modelAndView.addObject("sno", "''");
            modelAndView.addObject("name", "''");
            modelAndView.addObject("cname", "''");
            modelAndView.setViewName("admin/candidateInfo");
        }
        return modelAndView;
    }

    //第几页成绩列表
    @RequestMapping(value="/candidates", method= RequestMethod.GET)
    public ModelAndView candidates(HttpServletRequest request) {
        //获取参数
        String page = request.getParameter("page");
        String sno = request.getParameter("sno");
        String name = request.getParameter("name");
        String cname = request.getParameter("cname");
//        System.out.println("page = " + page);
//        System.out.println("sno = " + sno);
//        System.out.println("name = " + name);
//        System.out.println("cname = " + cname);

//        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
//        if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
//            modelAndView.setViewName("accounts/loginAdmin");
//            return modelAndView;
//        }
//        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
//        if (teacher != null) {
//            JSONObject teaJson = JSONObject.fromObject(teacher);
//            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
//        }
        if (StringUtils.isNotEmpty(page)) {
            modelAndView.addObject("page", page);
        } else {
            modelAndView.addObject("page", 1);
        }
        if (StringUtils.isNotEmpty(sno)) {
            modelAndView.addObject("sno", "'"+sno+"'");
        } else {
            modelAndView.addObject("sno", "''");
        }
        if (StringUtils.isNotEmpty(name)) {
            modelAndView.addObject("name", "'"+name+"'");
        } else {
            modelAndView.addObject("name", "''");
        }
        if (StringUtils.isNotEmpty(cname)) {
            modelAndView.addObject("cname", "'"+cname+"'");
        } else {
            modelAndView.addObject("cname", "''");
        }

        modelAndView.setViewName("admin/candidateInfo");
        return modelAndView;
    }

    //获取考生信息列表
    @RequestMapping(value="/api/getStudentList", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getPaperList(@RequestParam("studentId") String studentId, @RequestParam("studentName") String studentName,
                                   @RequestParam("className") String className, @RequestParam("page") int page, @RequestParam("num") int num) {
        LOG.info("page: " + page);
        LOG.info("num: " + num);
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> data = studentService.getStudentList(studentId, studentName, className, page, num);
        return new AjaxResult().setData(data);
    }

    //添加考生信息
    @RequestMapping(value="/api/addStudent", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addStudent(@RequestBody Student student) {
        AjaxResult ajaxResult = new AjaxResult();
        int stuId = studentService.addStudent(student);
        return new AjaxResult().setData(stuId);
    }

    //更新考生信息
    @RequestMapping(value="/api/updateStudent", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateStudent(@RequestBody Student student) {
        AjaxResult ajaxResult = new AjaxResult();
        int result = studentService.updateStudent(student);
        return new AjaxResult().setData(result);
    }

    //删除考生
    @DeleteMapping("/api/delStudent/{id}")
    public AjaxResult deleteStudent(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        int result = studentService.delStudent(id);
        return new AjaxResult().setData(result);
    }
}
