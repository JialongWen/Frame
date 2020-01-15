package com.wjl.frame.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionConfiguation {

    @Autowired
    private DataSource dataSource;

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager createTracsation(){
        return new DataSourceTransactionManager(dataSource);
    }

}
