package com.ldf.springprak14.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public class AppConfig {
//    private final DataSource dataSource;
//
//    public AppConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public JdbcDaoImpl jdbcDao() {
//        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
//        jdbcDao.setDataSource(dataSource);
//        return jdbcDao;
//    }
//
//    @Bean
//    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        return jdbcTokenRepository;
//    }
}
