package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.CourseDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.entity.Course;
import com.example.schoolwebsite.service.inter.CourseServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInter {

    @Autowired
    private CourseDaoInter courseDaoInter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn add(Course course) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(course.getCourseName())){
            if (courseDaoInter.selectbyname(course.getCourseName()).size()>0){
                backReturn.setMsg("课程已存在，请勿重复添加");
                backReturn.setCode(0);
                return backReturn;
            }
            course.setId(IdMaker.CourseIdMaker());
            try{
                if (courseDaoInter.add(course)) {
                    backReturn.setMsg("添加成功");
                    backReturn.setCode(1);
                }
            }catch (Exception e){
                throw new Exception();
            }
        }else{
            backReturn.setMsg("无效数据或课程名为空，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(Integer courseId) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(courseId)){
            if (courseDaoInter.selectbyid(courseId).size()>0){
                try{
                    if (courseDaoInter.delete(courseId)) {
                        backReturn.setCode(1);
                        backReturn.setMsg("删除成功");
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据不存在或已被删除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("输入数据为空，无法删除");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(Course course) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(course.getCourseName(),course.getId())){
            if (courseDaoInter.selectbyid(course.getId()).size()>0) {
                try{
                    if (courseDaoInter.update(course)){
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据不存在，无法修改");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("无效的修改条件，修改失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public BackReturn select(String courseName) {
        BackReturn backReturn = new BackReturn();
        List<Course> courses;
        if (StringTool.NullStringCheck(courseName)){
            courses = courseDaoInter.selectbyname("");
            if (courses.size()>0){
                backReturn.setCode(0);
                backReturn.setMsg("已查询到数据");
                backReturn.setObj(courses);
            }else {
                backReturn.setMsg("未查询到数据，系统异常或数据被清空");
                backReturn.setCode(-1);
            }
        }else{
            courses = courseDaoInter.selectbyname(courseName);
            if (courses.size()>0){
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(courses);
            }else{
                backReturn.setMsg("未查询到指定数据");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }
}
