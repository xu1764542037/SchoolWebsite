package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.UserInfo;

public interface UserInfoServiceInter {
    BackReturn delete(String IdCardNumber);
    BackReturn update(UserInfo userInfo);
    BackReturn select(String IdCardNumber,String Password);
}
