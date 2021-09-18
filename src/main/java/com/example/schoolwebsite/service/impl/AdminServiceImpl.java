package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.AdminDaoInter;
import com.example.schoolwebsite.dao.UserInfoDaoInter;
import com.example.schoolwebsite.entity.Admin;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.service.inter.AdminServiceInter;
import com.example.schoolwebsite.utils.AdminLoginRandom;
import com.example.schoolwebsite.utils.MD5Class;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceInter {

    @Autowired
    private AdminDaoInter adminDaoInter;
    @Autowired
    private UserInfoDaoInter userInfoDaoInter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn add(Admin admin) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (admin!=null){
            if (admin.getCode()!=null) {
                if(admin.getCode()>4){
                    String str = AdminLoginRandom.LoginRanDom();
                    admin.setLogin(str);
                    admin.setPassword(MD5Class.NewPassword(str.substring(str.length()-6)));
                    try{
                        if (adminDaoInter.add(admin)) {
                            backReturn.setMsg("添加成功，账号:"+str+",密码："+str.substring(str.length()-6));
                            backReturn.setCode(1);
                        }
                    }catch (Exception e){
                        throw new Exception();
                    }

                }else{
                    backReturn.setMsg("管理员身份等级不足，无法添加");
                    backReturn.setCode(0);
                }
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
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(String LoginId) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(LoginId)) {
            if (userInfoDaoInter.checkuser(LoginId)){
                try{
                    if (userInfoDaoInter.delete(LoginId)){
                        backReturn.setMsg("删除成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            }else{
                backReturn.setMsg("数据不存在或已被删除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setCode(0);
            backReturn.setMsg("传入参数为空，无法删除");
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(Admin admin) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NotNullStringCheck(admin.getLogin())){
            if (userInfoDaoInter.checkuser(admin.getLogin())){
                if (admin.getPassword()!=null&&!"".equals(admin.getPassword())){
                    admin.setPassword(MD5Class.NewPassword(admin.getPassword()));
                }else{
                    backReturn.setMsg("密码不能为空");
                    backReturn.setCode(0);
                    return backReturn;
                }
                try{
                    if (adminDaoInter.update(admin)){
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
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
    @Transactional(readOnly = true)
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
                    if (adminDaoInter.select(Login, MD5Class.NewPassword(Password))){
                        backReturn.setMsg("身份验证成功");
                        backReturn.setCode(1);
                    }else{
                        backReturn.setMsg("身份验证失败");
                        backReturn.setCode(0);
                    }
            }
        }
        return backReturn;
    }
}
