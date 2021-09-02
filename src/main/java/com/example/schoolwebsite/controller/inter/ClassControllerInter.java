package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;

public interface ClassControllerInter {
    BackReturn add(Class classes);
    BackReturn delete(Integer classId);
    BackReturn update(Class classes);
    BackReturn select(String className);
}
