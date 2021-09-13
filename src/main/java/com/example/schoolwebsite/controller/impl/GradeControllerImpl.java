package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.GradeControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Grade;
import com.example.schoolwebsite.service.impl.GradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Grade")
public class GradeControllerImpl implements GradeControllerInter {

    @Autowired
    private GradeServiceImpl gradeService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Grade grade) {
        return gradeService.add(grade);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer gradeId) {
        return gradeService.delete(gradeId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Grade grade) {
        return gradeService.update(grade);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "stuName",required = false,defaultValue = "") String studentName,
                             @RequestParam(value = "couName",required = false,defaultValue = "") String courseName){
        return gradeService.select(studentName, courseName);
    }
}
