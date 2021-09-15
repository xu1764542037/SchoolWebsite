package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Class{
    private Integer id;
    private String className;
    private Profession profession;
    private Date createTime;
    private Date endTime;
    private String status;
    private String description;
}
//通过控制层测试