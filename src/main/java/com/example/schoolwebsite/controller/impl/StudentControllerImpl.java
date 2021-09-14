package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.StudentControllerInter;
import com.example.schoolwebsite.controller.inter.StudentControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Student;
import com.example.schoolwebsite.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Student")
public class StudentControllerImpl implements StudentControllerInter {

    @Autowired
    private StudentServiceImpl studentService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id")  String IdCardNumber) {
        return studentService.delete(IdCardNumber);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String studentname,
                             @RequestParam(value = "branch",required = false) Integer branch,
                             @RequestParam(value = "class",required = false) String Class,
                             @RequestParam(value = "profession",required = false)Integer profession) {
        return studentService.select(studentname, branch,Class,profession);
    }
}
