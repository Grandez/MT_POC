package com.sdp.base.parg.client.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.sdp.base.parg")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public DataSource dataSource(
            @Value("${hibernate.url}") String url,
            @Value("${hibernate.username}") String username,
            @Value("${hibernate.password}") String password,
            @Value("${hibernate.driver}") String driver) {

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  @Value("${hibernate.dialect}") String dialect,
                                                  @Value("${hibernate.hbm2ddl}") String hbm2ddl,
                                                  @Value("${hibernate.show_sql}") String showSql) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.sdp.poc.threading");

        Properties props = new Properties();
        props.put("hibernate.dialect", dialect);
        props.put("hibernate.hbm2ddl.auto", hbm2ddl);
        props.put("hibernate.show_sql", showSql);
        factoryBean.setHibernateProperties(props);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
    public static SessionFactory buildSessionFactory() {
        try {
            // Configuración manual de Hibernate
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.setProperties(loadProps());

            // Añadir tus entidades anotadas
            configuration.addAnnotatedClass(Log.class);

            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new RuntimeException("Error al inicializar Hibernate: " + ex.getMessage(), ex);
        }
    }

    //    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
    private static Properties loadProps() throws IOException {
        Properties props = new Properties();
        try (InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("qlog.properties")) {
            if (in == null) {
                throw new RuntimeException("No se encontró el fichero miapp-db.properties");
            }
            props.load(in);
        }
        return props;
    }

}
