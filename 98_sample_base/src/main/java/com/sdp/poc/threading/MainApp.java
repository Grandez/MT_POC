package com.sdp.poc.threading;

import com.sdp.poc.threading.sample.config.AppConfig;
import com.sdp.poc.threading.sample.repository.UsuarioDao;
import com.sdp.poc.threading.sample.service.UsuarioService;
import com.sdp.poc.threading.sample.threads.UsuarioThread;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    @Autowired
    UsuarioService usuarioService;

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run2(args);
    }
    public void run(String[]args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        usuarioService = context.getBean(UsuarioService.class);

        usuarioService.crearUsuario("Javier");
        usuarioService.crearUsuario("Ana");

        usuarioService.listarUsuarios().forEach(u ->
                System.out.println("Usuario: " + u.getNombre()));

        context.close();
    }
    public void run2(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        UsuarioDao repo = context.getBean(UsuarioDao.class);

        Thread hilo1 = new UsuarioThread(sessionFactory, repo, "Javier");
        Thread hilo2 = new UsuarioThread(sessionFactory, repo, "Ana");

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
