package com.sdp.base.logging.system;

/**
 * Se configura el log manualmente con una session independiente
 * Si no es posible (falta log.properties, etc.) simplemente
 * el logger no usara la base de datos
 */

import com.sdp.base.logging.entity.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class HibernateConfig {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cargar el fichero de propiedades personalizado
            Properties props = new Properties();
            try (InputStream in = HibernateConfig.class.getClassLoader()
                    .getResourceAsStream("log.properties")) {
                if (in == null) {
                    throw new RuntimeException("No se encontró el fichero miapp-db.properties");
                }
                props.load(in);
            }

            // Configuración manual de Hibernate
            Configuration configuration = new Configuration();
            configuration.setProperties(props);

            // Añadir tus entidades anotadas
            configuration.addAnnotatedClass(Log.class);

            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Error al inicializar Hibernate: " + ex.getMessage());
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
