package com.example.schoolwebsite.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UserInfo {
    private String idCardNumber;
    private String password;
    private String code;
    private Date createTime;
}
