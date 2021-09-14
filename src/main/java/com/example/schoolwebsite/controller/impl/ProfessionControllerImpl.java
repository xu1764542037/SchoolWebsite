package com.example.schoolwebsite.controller.impl;

import com.example.schoolwebsite.controller.inter.ProfessionControllerInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Profession;
import com.example.schoolwebsite.service.impl.ProfessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("Profession")
public class ProfessionControllerImpl implements ProfessionControllerInter {

    @Autowired
    private ProfessionServiceImpl professionService;

    @Override
    @PutMapping("/add")
    public BackReturn add(@RequestBody Profession profession) {
            return professionService.add(profession);
    }

    @Override
    @GetMapping("/delete")
    public BackReturn delete(@RequestParam(value = "id") Integer professionId) {
            return professionService.delete(professionId);
    }

    @Override
    @PostMapping("/update")
    public BackReturn update(@RequestBody Profession profession) {
            return professionService.update(profession);
    }

    @Override
    @GetMapping("/select")
    public BackReturn select(@RequestParam(value = "name",required = false) String professionName,
                             @RequestParam(value = "branch",required = false) Integer branch) {
            return professionService.select(professionName,branch);
    }
}
