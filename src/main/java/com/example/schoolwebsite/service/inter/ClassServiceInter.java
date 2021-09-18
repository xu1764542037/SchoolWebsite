package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;

public interface ClassServiceInter {
    BackReturn add(Class classes) throws Exception;
    BackReturn delete(Integer classId) throws Exception;
    BackReturn update(Class classes) throws Exception;
    BackReturn select(String className,Integer Profession,Integer Branch);
    BackReturn CheckClassName(String classes);
}
