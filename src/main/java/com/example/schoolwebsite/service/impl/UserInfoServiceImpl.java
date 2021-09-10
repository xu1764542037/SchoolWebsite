package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.StudentDaoInter;
import com.example.schoolwebsite.dao.TeacherDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.UserInfo;
import com.example.schoolwebsite.service.inter.UserInfoServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BackReturn delete(String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        if (!"".equals(IdCardNumber)){
            if (userInfoDaoInter.selectbyid(IdCardNumber,null).size()>0){
                if (userInfoDaoInter.delete(IdCardNumber)){
                    backReturn.setCode(1);
                    backReturn.setMsg("删除成功");
                }else{
                    backReturn.setMsg("系统异常，删除失败");
                    backReturn.setCode(-1);
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
    public BackReturn update(UserInfo userInfo) {
        BackReturn backReturn = new BackReturn();
        if (!"".equals(userInfo.getIdCardNumber())) {
            if (userInfo.getPassword()==null&&userInfo.getCode()==null){
                backReturn.setMsg("无修改信息，修改成功");
                backReturn.setCode(1);
            }else {
                if (userInfoDaoInter.update(userInfo)) {
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
            }
        }else{
            backReturn.setMsg("修改条件为空，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String IdCardNumber,String password) {
        BackReturn backReturn = new BackReturn();
        List<UserInfo> userInfoList;
        if (IdCardNumber!=null&&!"".equals(IdCardNumber)&&password!=null&&!"".equals(password)){
            userInfoList = userInfoDaoInter.selectbyid(IdCardNumber,password);
            if (userInfoList.size()>0) {
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                switch (Integer.parseInt(userInfoList.get(0).getCode())) {
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
            backReturn.setMsg("登录信息不全，无法验证");
            backReturn.setCode(0);
        }
        return backReturn;
    }
}
