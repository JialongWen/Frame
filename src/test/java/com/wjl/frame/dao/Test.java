package com.wjl.frame.dao;

import com.wjl.frame.service.TestService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test {

    @Autowired
    private TestService testService;

    @org.junit.jupiter.api.Test
    public void testDao() throws SQLException {
        testService.addTest();
    }

}
