package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.AdminDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
import com.example.schoolwebsite.entity.Admin;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.service.inter.AdminServiceInter;
import com.example.schoolwebsite.utils.AdminLoginRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceInter {

    @Autowired
    private AdminDaoInter adminDaoInter;
    @Autowired
    private UserInfoDaoInter userInfoDaoInter;

    @Override
    public BackReturn add(Admin admin) {
        BackReturn backReturn = new BackReturn();
        if (admin!=null){
            if (admin.getCode()!=null) {
                admin.setLogin(AdminLoginRandom.LoginRanDom());
                admin.setPassword(AdminLoginRandom.LoginRanDom().substring(AdminLoginRandom.LoginRanDom().length()-6));
                adminDaoInter.add(admin);
            }else{
                backReturn.setMsg("用户身份不能为空");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入参数为空，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(String LoginId) {
        BackReturn backReturn = new BackReturn();
        if (!"".equals(LoginId)) {
            if (userInfoDaoInter.checkuser(LoginId)){
                if (userInfoDaoInter.delete(LoginId)){
                    backReturn.setMsg("删除成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setCode(-1);
                    backReturn.setMsg("系统异常，删除失败");
                }
            }else{
                backReturn.setMsg("数据不存在或已被删除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setCode(0);
            backReturn.setMsg("传入参数为空，无法删除");
        }
        return null;
    }

    @Override
    public BackReturn update(Admin admin) {
        BackReturn backReturn = new BackReturn();
        if (!"".equals(admin.getLogin())){
            if (userInfoDaoInter.checkuser(admin.getLogin())){
                if (adminDaoInter.update(admin)){
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("该用户不存在，无法修改");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("修改条件不足，无法修改");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String Login, String Password){
        BackReturn backReturn = new BackReturn();
        List<Admin> adminList;
        if (Login==null&&Password==null){
            adminList = adminDaoInter.selectall();
            backReturn.setMsg("已获取所有管理员信息");
            backReturn.setCode(1);
            backReturn.setObj(adminList);
        }else{
            if (Login==null||Password==null){
                backReturn.setMsg("账号或密码不能为空");
                backReturn.setCode(0);
            }else{
                if (adminDaoInter.select(Login, Password)){
                    backReturn.setMsg("身份验证成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("身份验证失败，账号或密码错误，请检查后重试");
                    backReturn.setCode(0);
                }
            }
        }
        return backReturn;
    }
}
