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
        BackReturn backReturn = new BackReturn();
        try{
            return studentService.add(student);
        }catch (Exception e){
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id")  String IdCardNumber) {
        BackReturn backReturn = new BackReturn();
        try{
            return studentService.delete(IdCardNumber);
        }catch (Exception e){
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Student student) {
        BackReturn backReturn = new BackReturn();
        try{
            return studentService.update(student);
        }catch (Exception e){
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String studentname,
                             @RequestParam(value = "branch",required = false) Integer branch,
                             @RequestParam(value = "class",required = false) String Class,
                             @RequestParam(value = "classId",required = false) Integer ClassId,
                             @RequestParam(value = "profession",required = false)Integer profession) {
        return studentService.select(studentname, branch,Class,profession,ClassId);
    }
}
