package com.sdp.base.logging.config;

import com.sdp.base.logging.entity.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    // private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            // Configuración manual de Hibernate
            Configuration configuration = new Configuration();
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
