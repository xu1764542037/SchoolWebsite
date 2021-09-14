package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.ClassControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.service.impl.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Class")
public class ClassControllerImpl implements ClassControllerInter {

    @Autowired
    private ClassServiceImpl classService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Class classes) {
        return classService.add(classes);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer classId) {
        return classService.delete(classId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Class classes) {
        return classService.update(classes);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String className,
                             @RequestParam(value = "profession",required = false) Integer profession,
                             @RequestParam(value = "branch",required = false) Integer branch) {
        return classService.select(className,profession,branch);
    }
}
