package com.zzqnxx.exz.web;

import com.zzqnxx.exz.common.Penguin;
import com.zzqnxx.exz.dto.AjaxResult;
import com.zzqnxx.exz.entity.Grade;
import com.zzqnxx.exz.entity.Post;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.entity.Subject;
import com.zzqnxx.exz.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/exam/student")
public class StudentController {

    private static Log LOG = LogFactory.getLog(AccountController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private HttpSession session;

    //首页
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public ModelAndView index() {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (!Penguin.IDENTITY_STUDENT.equals(identity)) {
            modelAndView.setViewName("accounts/login");
            return modelAndView;
        }
        Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        if (student != null) {
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
        }
        modelAndView.setViewName("students/home");
        return modelAndView;
        //return "students/papers";
    }

    //第几页成绩列表
    @RequestMapping(value="/scores/{page}", method= RequestMethod.GET)
    public ModelAndView scores(@PathVariable("page") int page) {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (!Penguin.IDENTITY_STUDENT.equals(identity)) {
            modelAndView.setViewName("accounts/login");
            return modelAndView;
        }
        Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        modelAndView.addObject("page", page);
        if (student != null) {
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
        }
        modelAndView.setViewName("students/scores");
        return modelAndView;
    }

    //第几页的试卷列表
    @RequestMapping(value="/papers/{page}", method= RequestMethod.GET)
    public ModelAndView papers(@PathVariable("page") int page) {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (!Penguin.IDENTITY_STUDENT.equals(identity)) {
            modelAndView.setViewName("accounts/login");
            return modelAndView;
        }
        Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        modelAndView.addObject("page", page);
        if (student != null) {
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
        }
        modelAndView.setViewName("students/papers");
        return modelAndView;
        //return "students/papers";
    }

    //根据试卷ID获取试卷试题
    @RequestMapping(value="/paper/{paperId}/{paperName}", method= RequestMethod.GET)
    public ModelAndView paper(@PathVariable("paperId") int paperId, @PathVariable("paperName") String paperName) {
        String identity = (String) session.getAttribute(Penguin.CURRENT_IDENTITY);
        ModelAndView modelAndView = new ModelAndView();
        if (!Penguin.IDENTITY_STUDENT.equals(identity)) {
            modelAndView.setViewName("accounts/login");
            return modelAndView;
        }
        Student student = (Student) session.getAttribute(Penguin.CURRENT_ACCOUNT);
        modelAndView.addObject("paperId", paperId);
        modelAndView.addObject("paperName", paperName);
        if (student != null) {
            JSONObject stuJson = JSONObject.fromObject(student);
            modelAndView.addObject("student", "'"+stuJson.toString()+"'");
        }
        modelAndView.setViewName("students/paper");
        return modelAndView;
        //return "students/papers";
    }

    //获取试卷列表
    @RequestMapping(value="/api/getPaperList", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getPaperList(@RequestParam("page") int page, @RequestParam("num") int num) {
        LOG.info("page: " + page);
        LOG.info("num: " + num);
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> data = paperService.getPaperList(page, num);
        return new AjaxResult().setData(data);
    }

    //获取试题列表
    @RequestMapping(value="/api/getPaper", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getPaper(@RequestParam("paperId") int paperId, @RequestParam("titleType") int titleType) {
        List<Subject> subjects = subjectService.getSbjsByPageIdAndType(paperId, titleType);
        Map<String, Object> data = new HashMap<>();
        data.put("subjects", subjects);
        data.put("sbSize", subjects.size());
        return new AjaxResult().setData(data);
    }

    @RequestMapping(value="/api/submitPaper", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult submitPaper(@RequestParam("paperId") int paperId, @RequestParam("answer") String answer) {
        try {
            LOG.info("submitPaper");
            JSONObject answerJson = JSONObject.fromObject(answer);
            List<Subject> sgSubjects = subjectService.getSbjsByPageIdAndType(paperId, 0);
            List<Subject> dbSubjects = subjectService.getSbjsByPageIdAndType(paperId, 1);
            int score = 0, singleScore = 0, mulScore = 0;
            for (Subject subject : sgSubjects) {
                if (subject.getAnswer().equals(answerJson.getString(""+subject.getId()))) {
                    singleScore += Penguin.SINGLE_SCORE;
                }
            }
            for (Subject subject : dbSubjects) {
                if (subject.getAnswer().equals(answerJson.getString(""+subject.getId()))) {
                    mulScore += Penguin.MUL_SCORE;
                }
            }
            score = singleScore + mulScore;
            Grade grade = new Grade();
            grade.setsId(accountService.id("1"));
            grade.setPaperId(paperId);
            grade.setScore(score);
            grade.setSingleScore(singleScore);
            grade.setMulScore(mulScore);
            grade.setOtherScore(0);
            grade.setAnswerJson(answer);
            int result = gradeService.submitGrade(grade);
            LOG.info("result = " + result);
            return new AjaxResult().setData(score);
        } catch (Exception e) {
            LOG.error(e, e);
            return new AjaxResult().setData(0);
        }
    }

    //获取自己成绩列表
    @RequestMapping(value="/api/getGradeList", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getGradeList(@RequestParam("sId") int sId, @RequestParam("page") int page, @RequestParam("num") int num) {
        LOG.info("page: " + page);
        LOG.info("num: " + num);
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> data = gradeService.getGradeList(sId, page, num);
        return new AjaxResult().setData(data);
    }

    //获取公告列表
    @RequestMapping(value="/api/getPosts", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getPosts() {
        List<Post> posts = postService.getPostList();
        Map<String, Object> data = new HashMap<>();
        data.put("posts", posts);
        data.put("postSize", posts.size());
        return new AjaxResult().setData(data);
    }
}
