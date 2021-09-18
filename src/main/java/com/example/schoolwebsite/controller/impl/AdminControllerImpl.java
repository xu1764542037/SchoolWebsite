package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.AdminControllerInter;
import com.example.schoolwebsite.entity.Admin;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.service.impl.AdminServiceImpl;
import com.example.schoolwebsite.service.inter.AdminServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Admin")
public class AdminControllerImpl implements AdminControllerInter {

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Admin admin) {
        BackReturn backReturn = new BackReturn();
        try{
            return adminService.add(admin);
        }catch (Exception e){
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") String LoginId) {
        BackReturn backReturn = new BackReturn();
        try{
            return adminService.delete(LoginId);
        }catch (Exception e){
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Admin admin) {
        BackReturn backReturn = new BackReturn();
        try{
            return adminService.update(admin);
        }catch (Exception e){
            backReturn.setMsg("系统异常，修改失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "login",required = false) String Login,@RequestParam(value = "password",required = false) String Password) {
        return adminService.select(Login,Password);
    }
}
