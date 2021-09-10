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
        return adminService.add(admin);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") String LoginId) {
        return adminService.delete(LoginId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "login",required = false) String Login,@RequestParam(value = "password",required = false) String Password) {
        return adminService.select(Login,Password);
    }
}
