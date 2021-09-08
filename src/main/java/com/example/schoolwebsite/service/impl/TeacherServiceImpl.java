package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
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
    @Autowired
    private UserInfoDaoInter userInfoDaoInter;

    @Override
    public BackReturn add(Teacher teacher) {
        BackReturn backReturn = new BackReturn();
        //判空
        if (teacher!=null){
            //必要信息判空
            if (    teacher.getIdcardnumber()!=null
                    &&teacher.getBranch()!=null
                    &&!"".equals(teacher.getSex())
                    &&!"".equals(teacher.getTeacherName())
                    &&!"".equals(teacher.getIdcardnumber().getIdCardNumber())
                    &&teacher.getSalary()!=null
                    &&teacher.getSalary()!=0
                    &&teacher.getBranch().getId()!=null
                    &&!"".equals(teacher.getIdcardnumber().getCode())){
                //有效性判定
                if (branchDaoInter.selectbyid(teacher.getBranch().getId()).size()>0
                        &&userInfoDaoInter.selectbyid(teacher.getIdcardnumber().getIdCardNumber()).size()<=0){
                    //必要数据写入
                    teacher.setId(IdMaker.TeacherIdMaker(teacher.getBranch().getId()));
                    teacher.getIdcardnumber().setPassword(teacher.getIdcardnumber().getIdCardNumber().substring(teacher.getIdcardnumber().getIdCardNumber().length()-6));
                    if (teacherDaoInter.add(teacher)>=0&&userInfoDaoInter.add(teacher.getIdcardnumber())>=0){
                        backReturn.setMsg("添加成功");
                        backReturn.setCode(1);
                    }else{
                        backReturn.setMsg("系统异常，添加失败");
                        backReturn.setCode(0);
                    }
                }else{
                    backReturn.setMsg("该用户已被注册或分院信息无效，确认后再试");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("教师信息不全，无法添加");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据为空，添加失败");
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
