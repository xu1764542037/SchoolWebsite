package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Branch{
    private Integer id;
    private String branchName;
    private Date createTime;
    private String status;
    private String description;
}
//通过控制层测试