package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;
import com.example.schoolwebsite.service.inter.TeacherServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInter {

    @Autowired
    private TeacherDaoInter teacherDaoInter;
    @Autowired
    private BranchDaoInter branchDaoInter;

    @Override
    public BackReturn add(Teacher teacher) {
        BackReturn backReturn = new BackReturn();
        if (teacher.getIdcardnumber()!=null&&!"".equals(teacher.getIdcardnumber())){
            if (teacher.getTeacherName()!=null&&!"".equals(teacher.getTeacherName())){
                if (teacher.getSex()!=null&&!"".equals(teacher.getSex())){
                    if (teacher.getBranch()!=null){
                        if (teacher.getBranch().getId()!=null){
                            if (branchDaoInter.selectbyid(teacher.getBranch().getId()).size()>0) {
                                if (teacher.getSalary()!=null&&teacher.getSalary()!=0){
                                    teacher.setId(IdMaker.TeacherIdMaker(teacher.getBranch().getId()));
                                    if (teacherDaoInter.add(teacher)>=0) {
                                        backReturn.setMsg("添加成功");
                                        backReturn.setCode(1);
                                    }else{
                                        backReturn.setMsg("系统异常，添加失败");
                                        backReturn.setCode(-1);
                                    }
                                }else{
                                    backReturn.setMsg("教师基础薪资不能为空");
                                    backReturn.setCode(0);
                                }
                            }else{
                                backReturn.setMsg("分院不存在，无法添加");
                                backReturn.setCode(0);
                            }
                        }else{
                            backReturn.setMsg("分院信息无效");
                            backReturn.setCode(0);
                        }
                    }else{
                        backReturn.setMsg("教师所属分院不能为空");
                        backReturn.setCode(0);
                    }
                }else{
                    backReturn.setMsg("教师性别不能为空");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("教师姓名不能为空");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("教师身份证号不能为空");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(Integer teacherId,String idCardNumber) {
        BackReturn backReturn = new BackReturn();
        if (teacherDaoInter.selectbyid(idCardNumber,null).size()>0){
            if (teacherDaoInter.delete(teacherId)>=0) {
                backReturn.setMsg("删除成功");
                backReturn.setCode(0);
            }else{
                backReturn.setMsg("系统异常，删除失败");
                backReturn.setCode(-1);
            }
        }else{
            backReturn.setMsg("教师信息不存在或已被删除");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Teacher teacher) {
        BackReturn backReturn = new BackReturn();
        if (teacher.getId()!=null&&teacher.getId()!=0){
            if (teacherDaoInter.selectbyid(null,teacher.getId()).size()>0) {
                if (teacher.getBranch()!=null){
                    if (teacher.getBranch().getId()==null&&teacher.getBranch().getId()==0){
                        teacher.setBranch(null);
                    }
                }
                if (teacherDaoInter.update(teacher)>=0) {
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("数据不存在，无法修改");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String teachername, Integer branch) {
        BackReturn backReturn = new BackReturn();
        List<Teacher> teacherList;
        if (!"".equals(teachername)&&teachername!=null){
            if (branch!=null&&branch!=0){
                teacherList = teacherDaoInter.selectbyname(teachername,branch);
                if (teacherList.size()>0) {
                    backReturn.setMsg("已查询到指定数据");
                    backReturn.setCode(1);
                    backReturn.setObj(teacherList);
                }else{
                    backReturn.setCode(0);
                    backReturn.setMsg("未查询到指定数据");
                }
            }else{
                teacherList = teacherDaoInter.selectbyname(teachername,null);
                if (teacherList.size()>0) {
                    backReturn.setMsg("已查询到指定数据");
                    backReturn.setCode(1);
                    backReturn.setObj(teacherList);
                }else{
                    backReturn.setCode(0);
                    backReturn.setMsg("未查询到指定数据");
                }
            }
        }else{
            if (branch!=null&&branch!=0){
                teacherList = teacherDaoInter.selectbyname(null,branch);
                if (teacherList.size()>0) {
                    backReturn.setMsg("已查询到指定数据");
                    backReturn.setCode(1);
                    backReturn.setObj(teacherList);
                }else{
                    backReturn.setCode(0);
                    backReturn.setMsg("未查询到指定数据");
                }
            }else{
                teacherList = teacherDaoInter.selectbyname(null,null);
                if (teacherList.size()>0) {
                    backReturn.setMsg("已查询到数据");
                    backReturn.setCode(1);
                    backReturn.setObj(teacherList);
                }else{
                    backReturn.setCode(-1);
                    backReturn.setMsg("未查询到数据，系统出错或数据已被清空");
                }
            }
        }
        return backReturn;
    }
}
