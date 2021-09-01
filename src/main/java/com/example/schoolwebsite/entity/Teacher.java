package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Teacher {
    private Integer id;
    private String teacherId;
    private String Sex;
    private Integer Salary;
    private String Level;
    private Date createTime;
    private String status;
    private String description;

}
