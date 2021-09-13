package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.*;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Class;
import com.example.schoolwebsite.service.inter.Teacher_ClassServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Teacher_ClassServiceImpl implements Teacher_ClassServiceInter {

    @Autowired
    private Teacher_ClassDaoInter teacher_classDaoInter;
    @Autowired
    private TeacherDaoInter teacherDaoInter;
    @Autowired
    private ClassDaoInter classDaoInter;
    @Autowired
    private CourseDaoInter courseDaoInter;

    @Override
    public BackReturn add(Teacher_Class teacher_class) {
        BackReturn backReturn = new BackReturn();
        if (teacher_class!=null){
            if (teacher_class.getTeacher()!=null&&teacher_class.getClasses()!=null&&teacher_class.getCourse()!=null){
                if (    teacherDaoInter.selectbyid(null,teacher_class.getTeacher().getId()).size()>0
                        &&classDaoInter.selectbyid(teacher_class.getClasses().getId()).size()>0
                        &&courseDaoInter.selectbyid(teacher_class.getCourse().getId()).size()>0){
                    teacher_class.setId(IdMaker.Teacher_ClassIdMaker());
                    if (teacher_classDaoInter.add(teacher_class)){
                        backReturn.setMsg("添加成功");
                        backReturn.setCode(1);
                    }else{
                        backReturn.setMsg("系统异常，添加失败");
                        backReturn.setCode(-1);
                    }
                }else{
                    backReturn.setMsg("班级，教师或课程信息无效，请确认后重试");
                    backReturn.setCode(0);
                }
            }
        }else{
            backReturn.setMsg("传入数据为空，无法添加");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn BatchAdd(List<Teacher_Class> teacher_classes) {
        BackReturn backReturn = new BackReturn();
        boolean check = false;
        if (teacher_classes.size()!=0){
            for (int i = 0; i < teacher_classes.size(); i++) {
                if (teacher_classes.get(i).getCourse()!=null
                        &&teacher_classes.get(i).getClasses()!=null
                        &&teacher_classes.get(i).getTeacher()!=null) {
                    if (teacher_classes.get(i).getCourse().getId()==null
                            ||teacher_classes.get(i).getClasses().getId()==null
                            ||teacher_classes.get(i).getTeacher().getId()==null
                    ){
                        backReturn.setMsg("第"+i+"条数据内容为空");
                        backReturn.setCode(0);
                        break;
                    }else{
                        if (    teacherDaoInter.selectbyid(null,teacher_classes.get(i).getTeacher().getId()).size()>0
                                &&classDaoInter.selectbyid(teacher_classes.get(i).getClasses().getId()).size()>0
                                &&courseDaoInter.selectbyid(teacher_classes.get(i).getCourse().getId()).size()>0){
                            teacher_classes.get(i).setId(IdMaker.Teacher_ClassIdMaker());
                            check = true;
                        }else{
                            backReturn.setMsg("第"+i+"条数据无效，添加失败");
                            backReturn.setCode(0);
                            break;
                        }
                    }
                }else{
                    backReturn.setMsg("第"+i+"条数据为空");
                    backReturn.setCode(0);
                    break;
                }
            }
            if (check){
                if (teacher_classDaoInter.Batchadd(teacher_classes)) {
                    backReturn.setMsg("添加成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，添加失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("数据验证失败，添加失败");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据为空");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(Teacher_Class teacher_class) {
        BackReturn backReturn = new BackReturn();
        if (teacher_class!=null){
            if (teacher_class.getId()!=null){
                if (teacher_classDaoInter.delete(teacher_class)){
                    backReturn.setMsg("删除成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，删除失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("删除条件不足，删除失败");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入参数为空，删除失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Teacher_Class teacher_class) {
        BackReturn backReturn = new BackReturn();
        if (teacher_class!=null){
            if (teacher_class.getId()!=null){
                if (teacher_classDaoInter.update(teacher_class)){
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("修改条件不足，修改失败");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入参数为空，修改失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String teacherId,String courseId,String classId) {
        BackReturn backReturn = new BackReturn();
        List<Teacher_Class> teacher_classes;
        if ("".equals(teacherId)&&"".equals(courseId)&&"".equals(classId)){
            teacher_classes = teacher_classDaoInter.select(null,null,null);
            if (teacher_classes.size()>0){
                backReturn.setMsg("查询到数据");
                backReturn.setCode(0);
                backReturn.setObj(teacher_classes);
            }else{
                backReturn.setMsg("系统异常，未查询到数据或数据被清空");
                backReturn.setCode(-1);
            }
        }else{
            teacher_classes = teacher_classDaoInter.select(teacherId,courseId,classId);
            if (teacher_classes.size()>0){
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
            }else{
                backReturn.setMsg("未查询到数据");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }
}
