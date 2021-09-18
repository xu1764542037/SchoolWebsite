package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.AdminDaoInter;
import com.example.schoolwebsite.dao.StudentDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
import com.example.schoolwebsite.entity.*;
import com.example.schoolwebsite.service.inter.UserInfoServiceInter;
import com.example.schoolwebsite.utils.MD5Class;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoServiceInter {

    @Autowired
    private UserInfoDaoInter userInfoDaoInter;
    @Autowired
    private StudentDaoInter studentDaoInter;
    @Autowired
    private TeacherDaoInter teacherDaoInter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(String IdCardNumber) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(IdCardNumber)){
            if (userInfoDaoInter.checkuser(IdCardNumber)){
                try{
                    if (userInfoDaoInter.delete(IdCardNumber)){
                        backReturn.setCode(1);
                        backReturn.setMsg("删除成功");
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据不存在或已被删除，删除失败");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("删除条件为空，删除失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(UserInfo userInfo) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(userInfo.getIdCardNumber())) {
            if (userInfo.getPassword()==null&&userInfo.getCode()==null){
                backReturn.setMsg("无修改信息，修改成功");
                backReturn.setCode(1);
            }else {
                userInfo.setPassword(MD5Class.NewPassword(userInfo.getPassword()));//加密
                try{
                    if (userInfoDaoInter.update(userInfo)) {
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }
        }else{
            backReturn.setMsg("修改条件为空，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public BackReturn select(String IdCardNumber,String password) {
        BackReturn backReturn = new BackReturn();
        List<UserInfo> userInfoList;
        List<AllUserInfo> userInfos = new ArrayList<>();
        AllUserInfo oneUserInfo = new AllUserInfo();
        if (IdCardNumber!=null||password!=null){
            if (StringTool.NotNullStringCheck(IdCardNumber, password)){
                password = MD5Class.NewPassword(password);
                userInfoList = userInfoDaoInter.selectbyid(IdCardNumber,password);
                if (userInfoList.size()>0) {
                    backReturn.setMsg("已查询到指定数据");
                    backReturn.setCode(1);
                    switch (userInfoList.get(0).getCode()) {
                        case 1 : backReturn.setObj(studentDaoInter.selectbyid(IdCardNumber,null));
                            break;
                        case 2 : backReturn.setObj(teacherDaoInter.selectbyid(IdCardNumber,null));
                            break;
                        default: backReturn.setObj(null);
                    }
                }else{
                    backReturn.setMsg("未查询到指定数据");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("账号或密码不能为空");
                backReturn.setCode(0);
            }
        }else{
            userInfoList = userInfoDaoInter.selectbyid(null,null);
            for (UserInfo userInfo:userInfoList) {
                if (userInfo.getCode()==1){
                    List<Student> student = studentDaoInter.selectbyid(userInfo.getIdCardNumber(),null);
                    oneUserInfo.setIdCardNumber(student.get(0).getIdcardnumber().getIdCardNumber());
                    oneUserInfo.setName(student.get(0).getStudentName());
                    oneUserInfo.setSex(student.get(0).getSex());
                    oneUserInfo.setBranch(student.get(0).getBranch());
                    oneUserInfo.setCode(1);
                    oneUserInfo.setCodeDes("学生");
                    userInfos.add(oneUserInfo);
                    oneUserInfo=new AllUserInfo();
                    continue;
                }
                if (userInfo.getCode()==2){
                    List<Teacher> teachers = teacherDaoInter.selectbyid(userInfo.getIdCardNumber(),null);
                    oneUserInfo.setIdCardNumber(teachers.get(0).getIdcardnumber().getIdCardNumber());
                    oneUserInfo.setName(teachers.get(0).getTeacherName());
                    oneUserInfo.setSex(teachers.get(0).getSex());
                    oneUserInfo.setBranch(teachers.get(0).getBranch());
                    oneUserInfo.setCodeDes("教师");
                    oneUserInfo.setCode(2);
                    userInfos.add(oneUserInfo);
                    oneUserInfo=new AllUserInfo();
                    continue;
                }
                if (userInfo.getCode()==5){
                    oneUserInfo.setBranch(null);
                    oneUserInfo.setSex(null);
                    oneUserInfo.setCodeDes("管理员");
                    oneUserInfo.setCode(5);
                    userInfos.add(oneUserInfo);
                    oneUserInfo=new AllUserInfo();
                    continue;
                }
                if(userInfo.getCode()==6){
                    oneUserInfo.setBranch(null);
                    oneUserInfo.setSex(null);
                    oneUserInfo.setCodeDes("超级管理员");
                    oneUserInfo.setCode(6);
                    userInfos.add(oneUserInfo);
                    oneUserInfo=new AllUserInfo();
                }
            }
            if (userInfos.size()>0){
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(userInfos);
            }else{
                backReturn.setMsg("系统异常，未查询到数据或数据被清空");
                backReturn.setCode(-1);
            }
        }
        return backReturn;
    }
}
