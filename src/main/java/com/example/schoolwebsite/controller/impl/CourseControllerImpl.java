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
        BackReturn backReturn = new BackReturn();
        try{
            return courseService.add(course);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer courseId) {
        BackReturn backReturn = new BackReturn();
        try{
            return courseService.delete(courseId);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Course course) {
        BackReturn backReturn = new BackReturn();
        try{
            return courseService.update(course);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，修改失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String courseName) {
        return courseService.select(courseName);
    }
}
