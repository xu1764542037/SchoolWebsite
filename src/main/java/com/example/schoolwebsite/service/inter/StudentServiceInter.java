package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Student;

public interface StudentServiceInter {
    BackReturn add(Student student);
    BackReturn delete(Integer studentId,String IdCardNumber);
    BackReturn update(Student student);
    BackReturn select(String studentname,Integer branch,String Class);
}
