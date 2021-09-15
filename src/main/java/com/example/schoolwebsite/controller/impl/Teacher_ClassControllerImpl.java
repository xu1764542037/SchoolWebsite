package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.Teacher_ClassControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Class;
import com.example.schoolwebsite.service.impl.Teacher_ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Teacher_Class")
public class Teacher_ClassControllerImpl implements Teacher_ClassControllerInter {

    @Autowired
    private Teacher_ClassServiceImpl teacher_classService;


    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Teacher_Class teacher_class) {
        return teacher_classService.add(teacher_class);
    }

    @Override
    @PutMapping("/batchAdd")
    public BackReturn BatchAdd(@RequestBody List<Teacher_Class> teacher_classes) {
        BackReturn backReturn = new BackReturn();
        try{
            return teacher_classService.BatchAdd(teacher_classes);
        }catch (Exception e){
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }

    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id",required = false) Integer id,
                             @RequestParam(value = "TeaId",required = false) Integer TeacherId,
                             @RequestParam(value = "ClaId",required = false) Integer ClassId,
                             @RequestParam(value = "CouId",required = false) Integer CourseId) {
        return teacher_classService.delete(id, TeacherId, ClassId, CourseId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Teacher_Class teacher_class) {
        return teacher_classService.update(teacher_class);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "TeaId",required = false) Integer teacherId,
                             @RequestParam(value = "CouId",required = false) Integer courseId,
                             @RequestParam(value = "ClaId",required = false) Integer classId) {
        return teacher_classService.select(teacherId, courseId, classId);
    }
}
