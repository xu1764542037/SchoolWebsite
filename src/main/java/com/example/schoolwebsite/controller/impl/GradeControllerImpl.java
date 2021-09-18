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
        BackReturn backReturn = new BackReturn();
        try{
            return gradeService.add(grade);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer gradeId) {
        BackReturn backReturn = new BackReturn();
        try {
            return gradeService.delete(gradeId);
        } catch (Exception e) {
            e.printStackTrace();
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;

        }
    }
    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Grade grade) {
        BackReturn backReturn = new BackReturn();
        try{
            return gradeService.update(grade);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，修改失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "stuName",required = false) String studentName,
                             @RequestParam(value = "couName",required = false) String courseName){
        return gradeService.select(studentName, courseName);
    }
}
