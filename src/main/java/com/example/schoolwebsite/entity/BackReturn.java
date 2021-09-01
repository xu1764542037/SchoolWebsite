package com.example.schoolwebsite.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BackReturn {
    private int code;
    private String Msg;
    private Object obj=null;
}
