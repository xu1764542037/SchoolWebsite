package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.dao.ClassDaoInter;
import com.example.schoolwebsite.dao.ProfessionDaoInter;
import com.example.schoolwebsite.dao.StudentDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.entity.Student;
import com.example.schoolwebsite.service.inter.StudentServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInter {

    @Autowired
    private StudentDaoInter studentDaoInter;
    @Autowired
    private ProfessionDaoInter professionDaoInter;
    @Autowired
    private BranchDaoInter branchDaoInter;
    @Autowired
    private ClassDaoInter classDaoInter;

    @Override
    public BackReturn add(Student student) {
        BackReturn backReturn = new BackReturn();
        if (student!=null){
            if (student.getIdcardnumber()!=null
                    &&!"".equals(student.getIdcardnumber())
                    &&student.getAge()!=null
                    &&student.getAge()>0
                    &&!"".equals(student.getSex())
                    &&!"".equals(student.getStudentName())) {
                //分院判空
                if (student.getBranch()!=null) {
                    if (student.getBranch().getId()==null){
                        student.setBranch(null);
                    }
                }
                //专业判空
                if (student.getProfession()!=null){
                    if (student.getProfession().getId()==null){
                        student.setProfession(null);
                    }
                }
                //班级判空
                if (student.getClasses()!=null){
                    if (student.getClasses().getId()==null){
                        student.setClasses(null);
                    }
                }
                if (//验证分院、专业、班级信息有效性
                        professionDaoInter.selectbyid(student.getProfession().getId()).size()>0&&
                        branchDaoInter.selectbyid(student.getBranch().getId()).size()>0&&
                        classDaoInter.selectbyid(student.getClasses().getId()).size()>0
                ){
                    student.setId(IdMaker.StudentIdMaker(student.getClasses().getId()));
                    if (studentDaoInter.add(student)>=0) {
                        backReturn.setMsg("添加成功");
                        backReturn.setCode(1);
                    }else {
                        backReturn.setMsg("系统异常，添加失败");
                        backReturn.setCode(-1);
                    }
                }else{
                    backReturn.setMsg("添加失败，分院、专业或班级信息无效，请核验后重新添加");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setCode(0);
                backReturn.setMsg("学生年龄，性别，身份证号均不能为空");
            }
        }else{
            backReturn.setMsg("传入参数为空，添加失败");
            backReturn.setCode(0);
        }

        return backReturn;
    }

    @Override
    public BackReturn delete(Integer studentId, String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        if (studentId != null && !"".equals(IdCardNumber) &&studentId!=0){
            if (studentDaoInter.selectbyid(IdCardNumber,null).size()>0) {
                if (studentDaoInter.delete(studentId)>=0) {
                    backReturn.setMsg("删除成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，删除失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setCode(0);
                backReturn.setMsg("数据不存在或已被删除");
            }
        }else{
            backReturn.setMsg("传入数据无效，无法删除");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Student student) {
        BackReturn backReturn = new BackReturn();
        if (student!=null){
            if (student.getBranch()!=null) {
                if (student.getBranch().getId()==null){
                    student.setBranch(null);
                }
            }
            if (student.getProfession()!=null){
                if (student.getProfession().getId()==null){
                    student.setProfession(null);
                }
            }
            if (student.getClasses()!=null){
                if (student.getClasses().getId()==null){
                    student.setClasses(null);
                }
            }
            if (//验证分院、专业、班级信息有效性
                    professionDaoInter.selectbyid(student.getProfession().getId()).size()>0&&
                            branchDaoInter.selectbyid(student.getBranch().getId()).size()>0&&
                            classDaoInter.selectbyid(student.getClasses().getId()).size()>0
            ){
                if (studentDaoInter.update(student)>=0) {
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(0);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("添加失败，分院、专业或班级信息无效，请核验后再试");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据为空，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String studentname, Integer branch,String Class) {
        BackReturn backReturn = new BackReturn();
        List<Student> student;
        if (!"".equals(studentname)){
            if (branch!=null&&branch!=0){
                if (!"".equals(Class)){
                    student = studentDaoInter.selectbyname(studentname,branch,Class);
                }else{
                    student = studentDaoInter.selectbyname(studentname,branch,null);
                }
            }else{
                if (!"".equals(Class)){
                    student = studentDaoInter.selectbyname(studentname,null,Class);
                }else{
                    student = studentDaoInter.selectbyname(studentname,null,null);
                }
            }
            if (student.size()>0){
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(student);
            }else{
                backReturn.setMsg("未查询到指定数据");
                backReturn.setCode(0);
            }
        }else{
            if (branch!=null&&branch!=0){
                if (!"".equals(Class)){
                    student = studentDaoInter.selectbyname(null,branch,Class);
                }else{
                    student = studentDaoInter.selectbyname(null,branch,null);
                }
                if (student.size()>0){
                    backReturn.setMsg("已查询到指定数据");
                    backReturn.setCode(1);
                    backReturn.setObj(student);
                }else{
                    backReturn.setMsg("未查询到指定数据");
                    backReturn.setCode(0);
                }
            }else{
                if (!"".equals(Class)){
                    student = studentDaoInter.selectbyname(null,null,Class);
                    if (student.size()>0){
                        backReturn.setMsg("已查询到指定数据");
                        backReturn.setCode(1);
                        backReturn.setObj(student);
                    }else{
                        backReturn.setMsg("未查询到指定数据");
                        backReturn.setCode(0);
                    }
                }else{
                    student = studentDaoInter.selectbyname(null,null,null);
                }
                if (student.size()>0){
                    backReturn.setMsg("已查询到数据");
                    backReturn.setCode(1);
                    backReturn.setObj(student);
                }else{
                    backReturn.setMsg("未查询到数据，系统异常或数据被清空");
                    backReturn.setCode(-1);
                }
            }
        }
        return backReturn;
    }
}
