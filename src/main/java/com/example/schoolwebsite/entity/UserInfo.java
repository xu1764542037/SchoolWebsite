package com.example.schoolwebsite.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UserInfo {
    private String idCardNumber;
    private String password;
    private Integer code;
    private Date createTime;
}
//通过控制层测试