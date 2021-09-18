package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.CourseDaoInter;
import com.example.schoolwebsite.dao.GradeDaoInter;
import com.example.schoolwebsite.dao.StudentDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Grade;
import com.example.schoolwebsite.service.inter.GradeServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeServiceInter {

    @Autowired
    private GradeDaoInter gradeDaoInter;
    @Autowired
    private StudentDaoInter studentDaoInter;
    @Autowired
    private CourseDaoInter courseDaoInter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn add(Grade grade) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (grade!=null){
            if (grade.getStudent()!=null&&grade.getCourse()!=null){
                if (studentDaoInter.selectbyid(null,grade.getStudent().getId()).size()>0&&
                        courseDaoInter.selectbyid(grade.getCourse().getId()).size()>0){
                    if (StringTool.NotNullStringCheck(grade.getGrade())){
                        grade.setId(IdMaker.GradeIdMaker());
                        try{
                            if (gradeDaoInter.add(grade)) {
                                backReturn.setMsg("添加成功");
                                backReturn.setCode(1);
                            }
                        }catch (Exception e){
                            throw new Exception();
                        }
                    }else{
                        backReturn.setCode(0);
                        backReturn.setMsg("学生学科成绩不能为空");
                    }
                }else{
                    backReturn.setMsg("学生或课程信息无效，无法添加");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("学生及课程信息不能为空");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据无效，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(Integer gradeId) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(gradeId)){
            if (gradeDaoInter.selectbyid(gradeId).size()>0) {
                try{
                    if (gradeDaoInter.delete(gradeId)) {
                        backReturn.setMsg("删除成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据不存在或已被清除，删除失败");
                backReturn.setCode(0);
            }
        }else {
            backReturn.setMsg("传入参数为空，无法删除");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(Grade grade) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (grade!=null) {
            if (StringTool.NotNullStringCheck(grade.getGrade())){
                if (grade.getCourse()!=null){//Course判空
                    if (grade.getCourse().getId()==null) {
                        grade.setCourse(null);
                    }else{
                        if (courseDaoInter.selectbyid(grade.getCourse().getId()).size()<=0) {//Course有效性判定
                            grade.setCourse(null);
                        }
                    }
                }
                if (grade.getStudent()!=null){//Student判空
                    if (grade.getStudent().getId()==null){
                        grade.setStudent(null);
                    }else{
                        if (studentDaoInter.selectbyid(null,grade.getStudent().getId()).size()<=0) {//Student有效性判定
                            grade.setStudent(null);
                        }
                    }
                }
                try{
                    if (gradeDaoInter.update(grade)) {
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据信息不完整，修改失败");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入参数为空，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public BackReturn select(String studentName, String courseName) {
        BackReturn backReturn = new BackReturn();
        List<Grade> gradeList;
        if (StringTool.NullStringCheck(studentName, courseName)){
            gradeList = gradeDaoInter.selectbyname(null, null);
            if (gradeList.size()>0){
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(gradeList);
            }else {
                backReturn.setCode(-1);
                backReturn.setMsg("未查询到数据，系统异常或数据被清空");
            }
        }else{
            gradeList = gradeDaoInter.selectbyname(studentName, courseName);
            if (gradeList.size()>0){
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(gradeList);
            }else {
                backReturn.setCode(0);
                backReturn.setMsg("未查询到数据");
            }
        }
        return backReturn;
    }
}
