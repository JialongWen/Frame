package com.wjl.frame.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguation {

        @Value("${jdbc.driver}")
        private String jdbcDriver;
        @Value("${jdbc.url}")
        private String jdbcUrl;
        @Value("${jdbc.username}")
        private String jdbcUsername;
        @Value("${jdbc.password}")
        private String jdbcPassword;

        @Bean(name = "dataSource")
        public ComboPooledDataSource createDataSource() throws Exception {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(jdbcDriver);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(jdbcUsername);
            dataSource.setPassword(jdbcPassword);
            dataSource.setAutoCommitOnClose(false);
            return dataSource;
        }
}
