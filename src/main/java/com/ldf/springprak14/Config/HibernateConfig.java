package com.ldf.springprak14.Config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public SessionFactory sessionFactory() {
//        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
//        builder.scanPackages("com.ldf.springprak14.Entity")
//                .addProperties(hibernateProperties());
//        return builder.buildSessionFactory();
//    }

//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        return new HibernateTransactionManager(sessionFactory());
//    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}
