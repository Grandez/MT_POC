package com.sdp.poc.threading.db.manager.services;

import com.sdp.poc.threading.database.dao.*;
import com.sdp.poc.threading.db.manager.core.CtxDBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Service
public class Initializer {
    private CtxDBManager ctx = CtxDBManager.getInstance();

    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager em;

    @Autowired
    MasterDao masterDao;
    @Autowired
    Slave1Dao slave1Dao;
    @Autowired
    Slave2Dao slave2Dao;

    @Transactional
    public void initialize() {
        Integer master = 0;
        Integer slave1 = 0;
        Integer slave2 = 0;

//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();

        try {
//            tx.begin();
            // em.joinTransaction();
            master = masterDao.deleteAll();
            slave1 = slave1Dao.deleteAll();
            slave2 = slave2Dao.deleteAll();
//            tx.commit();
        } catch (RuntimeException e) {
//            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
//            em.close();
        }
    }

}
/*
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public void crearUsuarioManual(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }
}

 */
