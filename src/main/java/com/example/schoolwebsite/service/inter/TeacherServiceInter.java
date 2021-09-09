package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;

public interface TeacherServiceInter {
    BackReturn add(Teacher teacher);
    BackReturn delete(String IdCardNumber);
    BackReturn update(Teacher teacher);
    BackReturn select(String teachername,Integer branch);
}
