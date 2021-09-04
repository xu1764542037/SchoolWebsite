package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.CourseDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.dao.Teacher_CourseDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Course;
import com.example.schoolwebsite.service.inter.Teacher_CourseServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacher_CourseServiceImpl implements Teacher_CourseServiceInter {

    @Autowired
    private TeacherDaoInter teacherDaoInter;
    @Autowired
    private CourseDaoInter courseDaoInter;
    @Autowired
    private Teacher_CourseDaoInter teacher_courseDaoInter;

    @Override
    public BackReturn add(Teacher_Course teacher_course) {
        BackReturn backReturn = new BackReturn();
        //参数判空
        if (teacher_course!=null){
            //Course对象是否存在
            if (teacher_course.getCourse()!=null){
                //CourseId值是否有效
                if(teacher_course.getCourse().getId()!=null&&teacher_course.getCourse().getId()!=0){
                    //CourseId是否位于Course表中
                    if (courseDaoInter.selectbyid(teacher_course.getCourse().getId()).size()<=0){
                        teacher_course.setCourse(null);
                    }
                }
            }
            //Teacher对象是否存在
            if (teacher_course.getTeacher()!=null){
                //CourseId值是否有效
                if(teacher_course.getTeacher().getId()!=null&&teacher_course.getTeacher().getId()!=0){
                    //TeacherId是否位于Teacher表中
                    if (teacherDaoInter.selectbyid(teacher_course.getTeacher().getIdcardnumber(),null).size()<=0){
                        teacher_course.setTeacher(null);
                    }
                }
            }
            //执行添加
            teacher_course.setId(IdMaker.Teacher_CourseIdMaker());
            if (teacher_courseDaoInter.add(teacher_course)>=0){
                backReturn.setMsg("添加成功");
                backReturn.setCode(1);
            }else{
                backReturn.setMsg("系统异常，添加失败");
                backReturn.setCode(-1);
            }

        }else{
            backReturn.setMsg("无效的数据，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(Integer teacher_courseId) {
        BackReturn backReturn = new BackReturn();
        if (teacher_courseId!=null&&teacher_courseId!=0){
            if (teacher_courseDaoInter.selectbyid(teacher_courseId).size()>0) {
                if (teacher_courseDaoInter.delete(teacher_courseId)>=0) {
                    backReturn.setMsg("删除成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，删除失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("数据不存在或已被删除，无法删除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据无效，无法删除");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Teacher_Course teacher_course) {
        BackReturn backReturn = new BackReturn();
        if (teacher_course.getId()!=null&&teacher_course.getId()!=0){//判断修改ID是否存在
            //判断Course对象是否存在
            if (teacher_course.getCourse()!=null){
                //判断CourseId值是否有效
                if(teacher_course.getCourse().getId()!=null&&teacher_course.getCourse().getId()!=0){
                    //判断CourseId是否位于Course表中
                    if (courseDaoInter.selectbyid(teacher_course.getCourse().getId()).size()<=0){
                        teacher_course.setCourse(null);
                    }
                }
            }
            //判断Teacher对象是否存在
            if (teacher_course.getTeacher()!=null){
                //判断CourseId值是否有效
                if(teacher_course.getTeacher().getId()!=null&&teacher_course.getTeacher().getId()!=0){
                    //判断TeacherId是否位于Teacher表中
                    if (teacherDaoInter.selectbyid(teacher_course.getTeacher().getIdcardnumber(),null).size()<=0){
                        teacher_course.setTeacher(null);
                    }
                }
            }
            //修改数据
            if (teacher_courseDaoInter.update(teacher_course)>=0){
                backReturn.setMsg("修改成功");
                backReturn.setCode(1);
            }else{
                backReturn.setCode(-1);
                backReturn.setMsg("系统异常，修改失败");
            }
        }else{
            backReturn.setMsg("传入数据无效，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String TeacherName,String CourseName) {
        BackReturn backReturn = new BackReturn();
        List<Teacher_Course> teacher_courseList;
        teacher_courseList = teacher_courseDaoInter.selectbyname(TeacherName,CourseName);
        if (teacher_courseList.size()>0){
            if ("".equals(TeacherName)&&"".equals(CourseName)){
                backReturn.setMsg("已查询到数据");
            }else{
                backReturn.setMsg("已查询到指定数据");
            }
            backReturn.setCode(1);
            backReturn.setObj(teacher_courseList);
        }else{
            if ("".equals(TeacherName)&&"".equals(CourseName)){
                backReturn.setMsg("系统异常，未查询到数据或数据已被清空");
                backReturn.setCode(-1);
            }else{
                backReturn.setMsg("未查询到指定数据，数据不存在或已被删除");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }
}
