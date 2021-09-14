package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;

public interface ClassServiceInter {
    BackReturn add(Class classes);
    BackReturn delete(Integer classId);
    BackReturn update(Class classes);
    BackReturn select(String className,Integer Profession,Integer Branch);
    BackReturn CheckClassName(String classes);
}
