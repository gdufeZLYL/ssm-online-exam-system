package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.GradeDao;
import com.zzqnxx.exz.dao.PaperDao;
import com.zzqnxx.exz.dao.StudentDao;
import com.zzqnxx.exz.entity.Grade;
import com.zzqnxx.exz.entity.Paper;
import com.zzqnxx.exz.entity.Student;
import com.zzqnxx.exz.service.GradeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    private static Log LOG = LogFactory.getLog(GradeServiceImpl.class);
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private PaperDao paperDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public int submitGrade(Grade grade) {
        return gradeDao.insertGrade(grade);
    }

    @Override
    public Map<String, Object> getGradeList(int sId, int page, int num) {
        int count = gradeDao.queryCountBySId(sId);
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        List<Grade> grades = gradeDao.queryBySId(sId, num * (page - 1), num);
        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", grades.size());
        data.put("allSize", count);
        data.put("grades", grades);
        return data;
    }

    @Override
    public Map<String, Object> getAllGradeList(String studentId, String studentName, String className, String paperName, int page, int num) {
        //满足的要求的学生
        List<Student> students = studentDao.queryByStuIdAndStuNameAndClassName(studentId,
                studentName, className);
        if (students == null) {
            students = new ArrayList<>();
        }
        //JDK8新特性-collectors
        //获取满足要求考生的ID列表
        List<Integer> sIds = students.stream().map(Student::getId).collect(Collectors.toList());
        //获取满足要求考生ID-考生关系
        Map<Integer, Student> sId2stu = students.stream().collect(Collectors.toMap(Student::getId,
                Function.identity()));

        //获取满足要求试卷
        List<Paper> papers = paperDao.queryByPaperName(paperName);
        if (papers == null) {
            papers = new ArrayList<>();
        }
        //获取满足要求试卷的ID列表
        List<Integer> paperIds = papers.stream().map(Paper::getId).collect(Collectors.toList());
        //获取满足要求试卷的ID-试卷关系
        Map<Integer, Paper> pId2paper = papers.stream().collect(Collectors.toMap(Paper::getId,
                Function.identity()));

        int count = gradeDao.queryCountBySIdsAndPIds(sIds, paperIds);
        Map<String, Object> data = new HashMap<>();
        if (count == 0) {
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        int pageNum = count % num == 0 ? count / num : count / num + 1;
        if (page > pageNum) {
            //超出页数
            data.put("pageNum", 0);
            data.put("currPage", 0);
            data.put("size", 0);
            data.put("allSize", 0);
            data.put("grades", new ArrayList<>());
            return data;
        }
        List<Grade> grades = gradeDao.queryBySIdsAndPIds(sIds, paperIds, num * (page - 1), num);

        for (Grade grade : grades) {
            int sId = grade.getsId();
            int paperId = grade.getPaperId();
            if (sId2stu.containsKey(sId)) {
                grade.setStudent(sId2stu.get(sId));
            }
            if (pId2paper.containsKey(paperId)) {
                grade.setPaper(pId2paper.get(paperId));
            }
        }

        data.put("pageNum", pageNum);
        data.put("currPage", page);
        data.put("size", grades.size());
        data.put("allSize", count);
        data.put("grades", grades);
        return data;
    }
}
