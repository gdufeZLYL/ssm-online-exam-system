package com.zzqnxx.exz.web;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.service.StudentService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/")
public class CommonController {

    private static Log LOG = LogFactory.getLog(CommonController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView index() throws Exception {
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

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() throws Exception {
        return "/welcome";
    }
}
