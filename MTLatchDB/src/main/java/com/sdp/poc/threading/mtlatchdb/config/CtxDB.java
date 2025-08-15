package com.sdp.poc.threading.mtlatchdb.config;

import com.sdp.poc.threading.base.config.CtxBase;
import org.hibernate.SessionFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CtxDB extends CtxBase {
     private static CtxDB INSTANCE = null;
     public static AnnotationConfigApplicationContext context;
     public static SessionFactory sf;

     protected CtxDB(String fileProps, Class<?> configClass) {
         super(fileProps);
         context = new AnnotationConfigApplicationContext(configClass);
         sf = context.getBean(SessionFactory.class);
     }

    public  static CtxDB getInstance()                     { return getInstance("application"); }
    public  static CtxDB getInstance(String propsName)     { return getInstance(propsName, MTLatchDBConfig.class); }
    public  static CtxDB getInstance(Class<?> configClass) { return getInstance("application", configClass); }

    public  static CtxDB getInstance(String propsName, Class<?> configClass) {
        if (INSTANCE == null) INSTANCE = new CtxDB(propsName, configClass);
        return INSTANCE;
    }
//    public void setSpringContext() {
//        initHibernate();
//        context = new AnnotationConfigApplicationContext(getPackages());
////        Props app = getAppProps();
////        String scan = app.getProperty("spring.packages.scan");
////        if (scan == null) scan = "";
////        String[] toks = scan.split(",");
////        if (toks.length == 0) toks = new String[]{""};
////
////        context = new AnnotationConfigApplicationContext(toks);
////        sf = new Configuration().addProperties(getAppProps()).buildSessionFactory();
//    }
    public Object getBean(Class cls) {
        Object obj =  context.getBean(cls);
        return obj;
    }
//    public static Session getSession() {
//        Session session = threadLocalSession.get();
//        if (session == null || !session.isOpen()) {
//            session = sf.openSession();
//            threadLocalSession.set(session);
//        }
//        return session;
//    }
//
//    public static void closeSession() {
//        Session session = threadLocalSession.get();
//        if (session != null && session.isOpen()) {
//            session.close();
//        }
//        threadLocalSession.remove();
//    }

    public static void shutdown() {
        sf.close();
    }
//    private void initHibernate() {
//        Configuration configuration = new Configuration();
//        configuration.setProperties(getAppProps());
//
//        String[] packages = getPackages();
//        for (int i = 0; i < packages.length; i++) {
//            Set<Class<?>> entities = ClassScanner.findClassesWithAnnotation(packages[i], Entity.class);
//            for (Class<?> entityClass : entities) configuration.addAnnotatedClass(entityClass);
//        }
//        CtxDB.sf = configuration.buildSessionFactory();
//    }
//    private String[] getPackages() {
//        String property = getAppProps().getString("hibernate.packages.scan");
//        return property.split(","); }

}
