package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.UserInfoControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.UserInfo;
import com.example.schoolwebsite.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("UserInfo")
public class UserInfoControllerImpl implements UserInfoControllerInter {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        try{
            return userInfoService.delete(IdCardNumber);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody UserInfo userInfo) {
        BackReturn backReturn = new BackReturn();
        try{
            return userInfoService.update(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，修改失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "id",required = false) String IdCardNumber,
                             @RequestParam(value = "password",required = false) String Password) {
        return userInfoService.select(IdCardNumber, Password);
    }
}
