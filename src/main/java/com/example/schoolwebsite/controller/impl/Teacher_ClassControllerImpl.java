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
    public BackReturn BatchAdd(List<Teacher_Class> teacher_classes) {
        return teacher_classService.BatchAdd(teacher_classes);
    }

    @Override
    @DeleteMapping("/delete")
    public BackReturn delete(Teacher_Class teacher_class) {
        return teacher_classService.delete(teacher_class);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(Teacher_Class teacher_class) {
        return teacher_classService.update(teacher_class);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(String teacherId, String courseId, String classId) {
        return teacher_classService.select(teacherId, courseId, classId);
    }
}
