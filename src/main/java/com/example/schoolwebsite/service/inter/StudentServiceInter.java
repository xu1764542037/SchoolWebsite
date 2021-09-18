package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Student;

public interface StudentServiceInter {
    BackReturn add(Student student) throws Exception;
    BackReturn delete(String IdCardNumber) throws Exception;
    BackReturn update(Student student) throws Exception;
    BackReturn select(String studentname,Integer branch,String Class,Integer profession,Integer ClassId);
}
