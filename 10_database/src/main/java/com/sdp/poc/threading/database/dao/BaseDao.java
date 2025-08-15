package com.sdp.poc.threading.database.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDao {
    @PersistenceContext
    private EntityManager em;

    String table;

    protected BaseDao(String table) {
        this.table = table;
    }

    public int deleteAll() {
        String sql = "DELETE FROM " + table;
        return em.createQuery(sql).executeUpdate();
    }
    public long getLastId(Session session) {
        String sql = "SELECT MAX(id) FROM " + table;
        Long obj =  (Long) session.createQuery(sql).getSingleResult();
        return (obj == null) ? 0 : obj.longValue();
    }

}
