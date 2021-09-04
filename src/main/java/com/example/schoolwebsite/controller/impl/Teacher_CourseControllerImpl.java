package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.Teacher_CourseControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Course;
import com.example.schoolwebsite.service.impl.Teacher_CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Teacher_Course")
public class Teacher_CourseControllerImpl implements Teacher_CourseControllerInter {

    @Autowired
    private Teacher_CourseServiceImpl teacher_courseService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Teacher_Course teacher_course) {
        return teacher_courseService.add(teacher_course);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer teacher_courseId) {
        return teacher_courseService.delete(teacher_courseId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Teacher_Course teacher_course) {
        return teacher_courseService.update(teacher_course);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name", defaultValue = "", required = false) String TeacherName, @RequestParam(value = "teacherName", defaultValue = "", required = false) String CourseName) {
        return teacher_courseService.select(TeacherName, CourseName);
    }
}