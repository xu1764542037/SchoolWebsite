package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Student{
    private Integer id;
    private UserInfo idcardnumber;
    private String studentName;
    private String sex;
    private Integer age;
    private Profession profession;
    private Branch branch;
    private Class classes;
    private String tel;
    private String qq;
    private String address;
    private Date createTime;
    private String status;
    private String description;
}
