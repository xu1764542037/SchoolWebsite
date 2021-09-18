package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.TeacherControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;
import com.example.schoolwebsite.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Teacher")
public class TeacherControllerImpl implements TeacherControllerInter {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Teacher teacher) {
        BackReturn backReturn = new BackReturn();
        try{
            return teacherService.add(teacher);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，添加失败");
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id")  String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        try{
            return teacherService.delete(IdCardNumber);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，删除失败");
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Teacher teacher) {
        BackReturn backReturn = new BackReturn();
        try{
            return teacherService.update(teacher);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，修改失败");
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String teachername,
                             @RequestParam(value = "branch",required = false) Integer branch) {
        return teacherService.select(teachername, branch);
    }
}
