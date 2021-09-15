package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Profession{
    private Integer id;
    private String professionName;
    private Branch branch;
    private Date createTime;
    private String status;
    private String description;
}
//通过控制层测试