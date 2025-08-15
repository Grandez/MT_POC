package com.sdp.poc.threading.sample.repository;

import com.sdp.poc.threading.sample.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }
    public void save(Usuario usuario, Session session) {
        session.save(usuario);
    }

    public List<Usuario> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Usuario", Usuario.class)
                .getResultList();
    }
}
