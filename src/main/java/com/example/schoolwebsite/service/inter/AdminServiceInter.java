package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.Admin;
import com.example.schoolwebsite.entity.BackReturn;

public interface AdminServiceInter {
    BackReturn add(Admin admin);
    BackReturn delete(String LoginId);
    BackReturn update(Admin admin);
    BackReturn select(String Login,String Password);
}
