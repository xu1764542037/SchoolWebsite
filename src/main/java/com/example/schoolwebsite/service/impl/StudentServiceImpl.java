package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.*;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.entity.Student;
import com.example.schoolwebsite.entity.UserInfo;
import com.example.schoolwebsite.service.inter.StudentServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.MD5Class;
import com.example.schoolwebsite.utils.StringTool;
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
    @Autowired
    private UserInfoDaoInter userInfoDaoInter;

    @Override
    public BackReturn add(Student student) {
        BackReturn backReturn = new BackReturn();
        if (student!=null){
            if (student.getIdcardnumber()!=null
                    &&!"".equals(student.getIdcardnumber().getIdCardNumber())
                    &&student.getAge()!=null
                    &&student.getAge()>0
                    &&!"".equals(student.getSex())
                    &&!"".equals(student.getStudentName())
                    &&student.getIdcardnumber().getCode()!=null) {
                //身份证后6位密码
                student.getIdcardnumber().setPassword(MD5Class.NewPassword(student.getIdcardnumber().getIdCardNumber().substring(student.getIdcardnumber().getIdCardNumber().length()-6)));
                //分院判空
                if (student.getBranch()!=null) {
                    if (student.getBranch().getId()==null){
                        backReturn.setMsg("分院信息不能为空");
                        backReturn.setCode(0);
                        return backReturn;
                    }
                }
                //专业判空
                if (student.getProfession()!=null){
                    if (student.getProfession().getId()==null){
                        backReturn.setMsg("专业信息不能为空");
                        backReturn.setCode(0);
                        return backReturn;
                    }
                }
                //班级判空
                if (student.getClasses()!=null){
                    if (student.getClasses().getId()==null){
                        backReturn.setMsg("班级信息不能为空");
                        backReturn.setCode(0);
                        return backReturn;
                    }
                }
                if (//验证分院、专业、班级信息有效性
                        professionDaoInter.selectbyid(student.getProfession().getId()).size()>0&&
                        branchDaoInter.selectbyid(student.getBranch().getId()).size()>0&&
                        classDaoInter.selectbyid(student.getClasses().getId()).size()>0&&
                        userInfoDaoInter.selectbyid(student.getIdcardnumber().getIdCardNumber(),null).size()==0
                ){
                    student.setId(IdMaker.StudentIdMaker(student.getClasses().getId()));
                    if (studentDaoInter.add(student)) {
                        backReturn.setMsg("添加成功");
                        backReturn.setCode(1);
                    }else {
                        backReturn.setMsg("系统异常，添加失败");
                        backReturn.setCode(-1);
                    }
                }else{
                    backReturn.setMsg("添加失败，分院、专业、班级信息无效或该身份证已被注册，请核验后重新添加");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setCode(0);
                backReturn.setMsg("身份、年龄、性别、身份证号均不能为空");
            }
        }else{
            backReturn.setMsg("传入参数为空，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(IdCardNumber)){
            if (studentDaoInter.selectbyid(IdCardNumber,null).size()>0) {
                if (userInfoDaoInter.delete(IdCardNumber)) {
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
            if (StringTool.NotNullStringCheck(student.getIdcardnumber().getIdCardNumber())){
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
                    if (studentDaoInter.update(student)) {
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }else{
                        backReturn.setMsg("系统异常，修改失败");
                        backReturn.setCode(-1);
                    }
                }else{
                    backReturn.setMsg("添加失败，分院、专业或班级信息无效，请核验后再试");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("无法验证被修改学生身份，检查身份证信息后重试");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据为空，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String studentname, Integer branch,String Class,Integer profession,Integer ClassId) {
        BackReturn backReturn = new BackReturn();
        List<Student> student;
        if (StringTool.NullStringCheck(studentname,Class,branch,profession,ClassId)){
            student = studentDaoInter.selectbyname(null,null,null,null,null);
            if (student.size()>0){
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(student);
            }else{
                backReturn.setMsg("系统异常，未查询到数据或数据已被清空");
                backReturn.setCode(-1);
            }
        }else{
            student = studentDaoInter.selectbyname(studentname, branch, Class,ClassId, profession);
            if (student.size()>0){
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(student);
            }else{
                backReturn.setMsg("未查询到指定数据");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }
}
