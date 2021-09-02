package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Profession;

public interface ProfessionServiceInter {
    BackReturn add(Profession profession);
    BackReturn delete(Integer professionId);
    BackReturn update(Profession profession);
    BackReturn select(String professionName);
}
