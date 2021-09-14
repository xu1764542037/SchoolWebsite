package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.CourseControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Course;
import com.example.schoolwebsite.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Course")
public class CourseControllerImpl implements CourseControllerInter {

    @Autowired
    private CourseServiceImpl courseService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Course course) {
        return courseService.add(course);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer courseId) {
        return courseService.delete(courseId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Course course) {
        return courseService.update(course);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String courseName) {
        return courseService.select(courseName);
    }
}
