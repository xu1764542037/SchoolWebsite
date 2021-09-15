package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AllUserInfo {
    private String idCardNumber;
    private String name;
    private String sex;
    private String code;
    private Branch branch;
}
