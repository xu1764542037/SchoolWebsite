package com.example.schoolwebsite;

import com.example.schoolwebsite.entity.*;
import com.example.schoolwebsite.entity.Class;
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
    StudentServiceImpl studentService;


    @Test
    void contextLoads(){
        Student student = new Student();
        Class classes = new Class();
        classes.setId(97540);
        Profession profession = new Profession();
        profession.setId(339);
        Branch branch = new Branch();
        branch.setId(202154);
        student.setId(219754037);
        student.setStudentName("楚留香");
        student.setAge(28);
        student.setSex("男");
        student.setBranch(branch);
        student.setClasses(classes);
        student.setProfession(profession);

        System.out.println(studentService.select(null,null,"20"));
    }
    @Test
    void tests(){
        System.out.println(IdMaker.BranchIdMaker());
    }
}
