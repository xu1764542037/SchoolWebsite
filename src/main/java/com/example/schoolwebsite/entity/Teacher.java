package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Teacher {
    private Integer id;
    private String idcardnumber;
    private String teacherName;
    private String sex;
    private Branch branch;
    private String level;
    private Integer salary;
    private String status;
    private String description;
}
