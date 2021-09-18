package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.Admin;
import com.example.schoolwebsite.entity.BackReturn;

public interface AdminServiceInter {
    BackReturn add(Admin admin) throws Exception;
    BackReturn delete(String LoginId) throws Exception;
    BackReturn update(Admin admin) throws Exception;
    BackReturn select(String Login,String Password);
}
