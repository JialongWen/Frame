package com.wjl.frame.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JDBCTemolateConfiguation {

    @Autowired
    private DataSource dataSource;

    @Bean("jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

}
