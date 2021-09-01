package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Branch{
    private Integer id;
    private String branchName;
    private String status;
    private String description;
}
