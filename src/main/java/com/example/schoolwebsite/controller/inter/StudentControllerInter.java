package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Student;

public interface StudentControllerInter {
    BackReturn add(Student student);
    BackReturn delete(String IdCardNumber);
    BackReturn update(Student student);
    BackReturn select(String studentname,Integer branch,String Class);
}
