package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;

public interface TeacherServiceInter {
    BackReturn add(Teacher teacher) throws Exception;
    BackReturn delete(String IdCardNumber) throws Exception;
    BackReturn update(Teacher teacher) throws Exception;
    BackReturn select(String teachername,Integer branch);
}
