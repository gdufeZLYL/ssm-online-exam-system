package com.zzqnxx.exz.web;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.dto.AjaxResult;
import com.zzqnxx.exz.entity.Post;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Subject;
import com.zzqnxx.exz.entity.Teacher;
import com.zzqnxx.exz.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/exam/admin")
public class AdminController {

    private static Log LOG = LogFactory.getLog(AccountController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private PostService postService;

    @Autowired
    private HttpSession session;

    //管理员登录页面
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView login() throws Exception {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (identity == null) {
            modelAndView.setViewName("accounts/loginAdmin");
            return modelAndView;
        } else if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
            return modelAndView;
        }
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (teacher == null) {
            modelAndView.setViewName("accounts/loginAdmin");
        } else {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
            modelAndView.addObject("page", 1);
            modelAndView.addObject("sname", "''");
            modelAndView.addObject("pname", "''");
            modelAndView.setViewName("admin/postInfo");
//            modelAndView.setViewName("admin/subjectInfo");
//            modelAndView.setViewName("admin/candidateInfo");
//            modelAndView.setViewName("accounts/loginAdmin");
        }
        return modelAndView;
    }

    //获取公告列表
    @RequestMapping(value="/posts", method= RequestMethod.GET)
    public ModelAndView posts(HttpServletRequest request) {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (identity == null) {
            modelAndView.setViewName("accounts/loginAdmin");
            return modelAndView;
        } else if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
            return modelAndView;
        }
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (teacher != null) {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
        }
        modelAndView.setViewName("admin/postInfo");
        return modelAndView;
    }

    //第几页题目信息列表
    @RequestMapping(value="/subjects", method= RequestMethod.GET)
    public ModelAndView subjects(HttpServletRequest request) {
        //获取参数
        String page = request.getParameter("page");
        String sname = request.getParameter("sname");
        String pname = request.getParameter("pname");

        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (identity == null) {
            modelAndView.setViewName("accounts/loginAdmin");
            return modelAndView;
        } else if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
            return modelAndView;
        }
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (teacher != null) {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
        }
        if (StringUtils.isNotEmpty(page)) {
            modelAndView.addObject("page", page);
        } else {
            modelAndView.addObject("page", 1);
        }
        if (StringUtils.isNotEmpty(sname)) {
            modelAndView.addObject("sname", "'"+sname+"'");
        } else {
            modelAndView.addObject("sname", "''");
        }
        if (StringUtils.isNotEmpty(pname)) {
            modelAndView.addObject("pname", "'"+pname+"'");
        } else {
            modelAndView.addObject("pname", "''");
        }
        modelAndView.setViewName("admin/subjectInfo");
        return modelAndView;
    }

    //第几页考生信息列表
    @RequestMapping(value="/candidates", method= RequestMethod.GET)
    public ModelAndView candidates(HttpServletRequest request) {
        //获取参数
        String page = request.getParameter("page");
        String sno = request.getParameter("sno");
        String name = request.getParameter("name");
        String cname = request.getParameter("cname");

        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (identity == null) {
            modelAndView.setViewName("accounts/loginAdmin");
            return modelAndView;
        } else if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
            return modelAndView;
        }
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (teacher != null) {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
        }
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

    //第几页成绩信息列表
    @RequestMapping(value="/grades", method= RequestMethod.GET)
    public ModelAndView grades(HttpServletRequest request) {
        //获取参数
        String page = request.getParameter("page"); //当前第几页
        String sno = request.getParameter("sno");   //考生学号
        String name = request.getParameter("name"); //考生姓名
        String cname = request.getParameter("cname");   //班级名称
//        String pname = request.getParameter("pname");   //专业名称
        String ename = request.getParameter("ename");   //试卷名称

        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (identity == null) {
            modelAndView.setViewName("accounts/loginAdmin");
            return modelAndView;
        } else if (!Penguin.IDENTITY_TEACHER.equals(identity)) {
            Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
            modelAndView.setViewName("students/home");
            return modelAndView;
        }
        Teacher teacher = (Teacher) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (teacher != null) {
            JSONObject teaJson = JSONObject.fromObject(teacher);
            modelAndView.addObject("admin", "'"+teaJson.toString()+"'");
        }
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
//        if (StringUtils.isNotEmpty(pname)) {
//            modelAndView.addObject("pname", "'"+pname+"'");
//        } else {
//            modelAndView.addObject("pname", "''");
//        }
        if (StringUtils.isNotEmpty(ename)) {
            modelAndView.addObject("ename", "'"+ename+"'");
        } else {
            modelAndView.addObject("ename", "''");
        }
        modelAndView.setViewName("admin/gradeInfo");
        return modelAndView;
    }

    //获取公告列表
    @RequestMapping(value="/api/getPosts", method= RequestMethod.GET)
    @ResponseBody
    public AjaxResult getPosts() {
        List<Post> posts = postService.getPostList();
        Map<String, Object> data = new HashMap<>();
        data.put("posts", posts);
        data.put("postSize", posts.size());
        return new AjaxResult().setData(data);
    }

    //添加公告
    @RequestMapping(value="/api/addPost", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addPost(@RequestBody Post post) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int postId = postService.addPost(post);
            return ajaxResult.setData(postId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ajaxResult.setMessage("接口调用出错");
    }

    //获取试题列表
    @RequestMapping(value="/api/getSubjectList", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getSubjectList(@RequestParam("subjectTitle") String subjectTitle, @RequestParam("paperName") String paperName,
                                   @RequestParam("page") int page, @RequestParam("num") int num) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Map<String, Object> data = subjectService.getSbjsByTitleAndPaperName(subjectTitle,
                    paperName, page, num);
            return new AjaxResult().setData(data);
        } catch (Exception e) {
            LOG.info(e.getMessage(), e);
        }
        return new AjaxResult().setMessage("接口调用出错");
    }

    //添加试题
    @RequestMapping(value="/api/addSubject", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSubject(@RequestBody Subject subject) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            if (!paperService.isPaperIdExist(subject.getPaperId())) {
                return ajaxResult.setMessage("不存在该试卷ID所对应的试卷");
            }
            int subjectId = subjectService.addSubject(subject);
            return ajaxResult.setData(subjectId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ajaxResult.setMessage("接口调用出错");
    }

    //更新试题
    @RequestMapping(value="/api/updateSubject", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateSubject(@RequestBody Subject subject) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            if (subject.getId() == 0) {
                return ajaxResult.setMessage("试题ID不能为空!");
            }
            if (!paperService.isPaperIdExist(subject.getPaperId())) {
                return ajaxResult.setMessage("不存在该试卷ID所对应的试卷");
            }
            int result = subjectService.updateSubject(subject);
            return ajaxResult.setData(result);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ajaxResult.setMessage("接口调用出错");
    }

    //删除试题
    @DeleteMapping("/api/delSubject/{id}")
    public AjaxResult deleteSubject(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int result = subjectService.deleteSubject(id);
            return ajaxResult.setData(result);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ajaxResult.setMessage("接口调用出错");
    }

    //获取考生成绩列表
    @RequestMapping(value="/api/getGradeList", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getGradeList(@RequestParam("studentId") String studentId, @RequestParam("studentName") String studentName,
                                   @RequestParam("className") String className, @RequestParam("paperName") String paperName,
                                   @RequestParam("page") int page, @RequestParam("num") int num) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Map<String, Object> data = gradeService.getAllGradeList(studentId, studentName, className,
                    paperName, page, num);
            return new AjaxResult().setData(data);
        } catch (Exception e) {
            LOG.info(e.getMessage(), e);
        }
        return new AjaxResult().setMessage("接口调用出错");
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
