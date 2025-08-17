package com.sdp.poc.threading.db.xa.service;

import com.example.xa.entities.Master;
import com.example.xa.entities.Slave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.UserTransaction;

public class XaService {
    private final SessionFactory sf1;
    private final SessionFactory sf2;
    private final UserTransaction utx;

    public XaService(SessionFactory sf1, SessionFactory sf2, UserTransaction utx) {
        this.sf1 = sf1;
        this.sf2 = sf2;
        this.utx = utx;
    }

    public void saveInBoth(Master master, Slave slave) throws Exception {
        try {
            utx.begin();

            Session s1 = sf1.getCurrentSession();
            Session s2 = sf2.getCurrentSession();

            s1.save(master);
            s2.save(slave);

            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw e;
        }
    }
}
