package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Profession;

public interface ProfessionControllerInter {
    BackReturn add(Profession profession);
    BackReturn delete(Integer professionId);
    BackReturn update(Profession profession);
    BackReturn select(String professionName,Integer branch);
}
