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
            return branchService.add(branch);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer branchId) {
            return branchService.delete(branchId);

    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Branch branch) {
            return branchService.update(branch);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",defaultValue = "",required = false) String branchName) {
            return branchService.select(branchName);
    }
}
