package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Student{
    private Integer id;
    private String studentName;
    private String Sex;
    private Integer Age;
    private Profession profession;
    private Class Classes;
    private String Tel;
    private String QQ;
    private String Address;
    private Date createTime;
    private String status;
    private String description;
}
