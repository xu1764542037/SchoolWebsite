package com.example.schoolwebsite.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Admin {
    String login;
    String password;
    Integer code;
    Date createTime;
}
//通过控制层测试