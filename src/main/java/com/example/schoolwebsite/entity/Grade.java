package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Grade{
    private Integer id;
    private Student student;
    private Course course;
    private Integer grade;
    private Date createTime;
    private String status;
    private String description;
}
