package com.sdp.poc.threading.sample.threads;

import com.sdp.poc.threading.sample.entities.Usuario;
import com.sdp.poc.threading.sample.repository.UsuarioDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UsuarioThread extends Thread {

    private final SessionFactory sessionFactory;
    private final UsuarioDao repo;
    private final String nombreUsuario;

    public UsuarioThread(SessionFactory sessionFactory, UsuarioDao repo, String nombreUsuario) {
        this.sessionFactory = sessionFactory;
        this.repo = repo;
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void run() {
        Session session = sessionFactory.openSession(); // Sesión independiente
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Usuario usuario = new Usuario(nombreUsuario);
            repo.save(usuario, session);

            tx.commit();
            System.out.println("Usuario " + nombreUsuario + " guardado en hilo: " + Thread.currentThread().getName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

