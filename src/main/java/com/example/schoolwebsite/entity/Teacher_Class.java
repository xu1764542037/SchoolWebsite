package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Teacher_Class {
    private Integer id;
    private Teacher teacher;
    private Class classes;
    private Course course;
    private Date createTime;
    private String status;
    private String description;
}
//通过控制层测试