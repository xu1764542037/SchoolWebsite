package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Course{
    private Integer id;
    private String courseName;
    private String status;
    private String description;
}
