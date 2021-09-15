package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Course{
    private Integer id;
    private String courseName;
    private Date createTime;
    private String status;
    private String description;
}
//通过控制层测试