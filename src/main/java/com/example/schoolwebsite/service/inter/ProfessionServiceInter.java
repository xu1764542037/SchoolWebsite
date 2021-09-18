package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Profession;

public interface ProfessionServiceInter {
    BackReturn add(Profession profession) throws Exception;
    BackReturn delete(Integer professionId) throws Exception;
    BackReturn update(Profession profession) throws Exception;
    BackReturn select(String professionName,Integer branch);
}
