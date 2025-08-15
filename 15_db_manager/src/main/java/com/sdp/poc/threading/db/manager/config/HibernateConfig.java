package com.sdp.poc.threading.db.manager.config;
/*
import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.sdp.poc.threading" })
public class HibernateConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.database-platform}")
    private String dialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.packages-to-scan}")
    private String packages;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan(packages);

        Properties props = new Properties();
        props.put(Environment.DIALECT, dialect);
        props.put(Environment.HBM2DDL_AUTO, hbm2ddl);
        props.put(Environment.SHOW_SQL, showSql);

        sf.setHibernateProperties(props);
        return sf;
    }
}
*/