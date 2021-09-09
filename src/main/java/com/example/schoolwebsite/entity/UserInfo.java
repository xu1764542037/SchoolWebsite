package com.example.schoolwebsite.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserInfo {
    private String idCardNumber;
    private String password;
    private String code;
}
