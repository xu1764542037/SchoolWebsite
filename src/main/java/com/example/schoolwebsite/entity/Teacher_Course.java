package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Teacher_Course{
    private Integer id;
    private Teacher teacher;
    private Course course;
    private String status;
    private String description;
}
