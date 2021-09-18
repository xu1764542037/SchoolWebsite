package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;
import com.example.schoolwebsite.service.inter.TeacherServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.MD5Class;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public BackReturn add(Teacher teacher) throws Exception {
        BackReturn backReturn = new BackReturn();
        //判空
        if (teacher!=null){
            //必要信息判空
            if (    teacher.getIdcardnumber()!=null//身份证
                    &&teacher.getBranch()!=null//所属分院
                    &&!"".equals(teacher.getSex())//性别
                    &&!"".equals(teacher.getTeacherName())//姓名
                    &&!"".equals(teacher.getIdcardnumber().getIdCardNumber())
                    &&teacher.getSalary()!=null//薪资
                    &&teacher.getSalary()!=0
                    &&teacher.getBranch().getId()!=null
                    &&teacher.getIdcardnumber().getCode()!=null){//身份
                //有效性判定
                if (branchDaoInter.selectbyid(teacher.getBranch().getId()).size()>0
                        &&!userInfoDaoInter.checkuser(teacher.getIdcardnumber().getIdCardNumber())){
                    //必要数据写入
                    teacher.setId(IdMaker.TeacherIdMaker(teacher.getBranch().getId()));
                    teacher.getIdcardnumber().setPassword(MD5Class.NewPassword(teacher.getIdcardnumber().getIdCardNumber().substring(teacher.getIdcardnumber().getIdCardNumber().length()-6)));
                    try{
                        if (teacherDaoInter.add(teacher)){
                            backReturn.setMsg("添加成功");
                            backReturn.setCode(1);
                        }
                    }catch (Exception e){
                        throw new Exception();
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
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(String idCardNumber) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(idCardNumber)){
            if (teacherDaoInter.selectbyid(idCardNumber,null).size()>0){
                try{
                    if (userInfoDaoInter.delete(idCardNumber)) {
                        backReturn.setMsg("删除成功");
                        backReturn.setCode(0);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("教师信息不存在或已被删除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入参数为空，删除失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(Teacher teacher) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(teacher.getId())){
            if (teacherDaoInter.selectbyid(null,teacher.getId()).size()>0) {
                if (teacher.getBranch()!=null){
                    if (teacher.getBranch().getId()==null||teacher.getBranch().getId()==0||branchDaoInter.selectbyid(teacher.getBranch().getId()).size()==0){
                        teacher.setBranch(null);
                    }
                }
                try{
                    if (teacherDaoInter.update(teacher)) {
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
        }
        return backReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public BackReturn select(String teachername, Integer branch) {
        BackReturn backReturn = new BackReturn();
        List<Teacher> teacherList;
        if (StringTool.NullStringCheck(teachername, branch)){
            teacherList = teacherDaoInter.selectbyname(null,null);
            if (teacherList.size()>0) {
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(teacherList);
            }else{
                backReturn.setCode(-1);
                backReturn.setMsg("未查询到数据，系统出错或数据已被清空");
            }
        }else{
            teacherList = teacherDaoInter.selectbyname(teachername,branch);
            if (teacherList.size()>0) {
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(teacherList);
            }else{
                backReturn.setCode(0);
                backReturn.setMsg("未查询到指定数据");
            }
        }
        return backReturn;
    }
}
