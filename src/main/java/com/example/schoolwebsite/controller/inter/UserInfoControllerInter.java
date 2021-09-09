package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.UserInfo;

public interface UserInfoControllerInter {
    BackReturn delete(String IdCardNumber);
    BackReturn update(UserInfo userInfo);
    BackReturn select(String IdCardNumber,String Password);
}
