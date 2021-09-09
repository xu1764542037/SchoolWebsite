package com.example.schoolwebsite;

import com.example.schoolwebsite.entity.*;
import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.service.impl.GradeServiceImpl;
import com.example.schoolwebsite.service.impl.ProfessionServiceImpl;
import com.example.schoolwebsite.service.impl.StudentServiceImpl;
import com.example.schoolwebsite.service.impl.TeacherServiceImpl;
import com.example.schoolwebsite.utils.IdMaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolwebsiteApplicationTests {

    @Autowired
    GradeServiceImpl gradeService;
    @Autowired
    ProfessionServiceImpl professionService;


    @Test
    void contextLoads(){
        Grade grade = new Grade();
        grade.setStatus("正常");
        grade.setId(20451573);
        grade.setGrade(100);
//        System.out.println(gradeService.select("李四",null));
        System.out.println(professionService.select(null,202112));
    }
    @Test
    void tests(){
        System.out.println(IdMaker.BranchIdMaker());
    }
}
