package com.wjl.frame.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class TestDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(String name,int age) throws SQLException {
        String sql = "insert into tb_test(name,age) values(?,?)";
        int update = jdbcTemplate.update(sql, name, age);
        System.out.println("更新条数:"+update);
    }

}
