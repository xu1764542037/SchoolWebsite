package com.example.schoolwebsite;

import com.example.schoolwebsite.utils.IdMaker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolwebsiteApplicationTests {

    @Test
    void contextLoads() {
        IdMaker.BranchIdMaker();
    }

}
