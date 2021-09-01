package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.BranchControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Branch")
public class BranchControllerImpl implements BranchControllerInter {
    @Autowired
    private BranchServiceImpl branchService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Branch branch) {
        BackReturn backReturn = new BackReturn();
        try{
            return branchService.add(branch);
        }catch (Exception e){
            backReturn.setMsg("系统异常异常，添加失败，请联系管理员");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer branchId) {
        BackReturn backReturn = new BackReturn();
        try{
            return branchService.delete(branchId);
        }catch (Exception e){
            backReturn.setMsg("系统异常，删除失败，请联系管理员");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Branch branch) {
        BackReturn backReturn = new BackReturn();
        try {
            return branchService.update(branch);
        } catch (Exception e) {
            backReturn.setMsg("系统异常，修改失败，请联系管理员");
            backReturn.setCode(-1);
            return backReturn;
        }
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",defaultValue = "",required = false) String branchName) {
        BackReturn backReturn = new BackReturn();
        try {
            branchService.select(branchName);
        } catch (Exception e) {
            backReturn.setCode(-1);
            backReturn.setMsg("系统异常，查询失败，请联系管理员");
        }
        return null;
    }
}
