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
        BackReturn backReturn = new BackReturn();
        try{
            return classService.add(classes);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，添加失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer classId) {
        BackReturn backReturn = new BackReturn();
        try{
            return classService.delete(classId);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，删除失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Class classes) {
        BackReturn backReturn = new BackReturn();
        try{
            return classService.update(classes);
        }catch (Exception e){
            e.printStackTrace();
            backReturn.setMsg("系统异常，修改失败");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String className,
                             @RequestParam(value = "profession",required = false) Integer profession,
                             @RequestParam(value = "branch",required = false) Integer branch) {
        return classService.select(className,profession,branch);
    }
}
