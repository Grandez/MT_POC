package com.sdp.poc.threading.database.dao;

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
    public int getLastId() {
        String sql = "SELECT MAX(id) FROM " + table;
        Object obj =  em.createQuery(sql).getSingleResult();
        if (obj == null) return 0;
        System.out.println(obj.toString());
        return 0;
    }

}
