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
        return teacherService.add(teacher);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id")  String IdCardNumber) {
        return teacherService.delete(IdCardNumber);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String teachername,
                             @RequestParam(value = "branch",required = false) Integer branch) {
        return teacherService.select(teachername, branch);
    }
}
