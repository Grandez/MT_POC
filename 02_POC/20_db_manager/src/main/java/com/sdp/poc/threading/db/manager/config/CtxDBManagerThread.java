package com.sdp.poc.threading.db.manager.config;

import com.sdp.poc.threading.base.config.CtxBase;
import com.sdp.poc.threading.mtlatchdb.ICtxDB;
import org.hibernate.Session;

public class CtxDBManagerThread implements ICtxDB {
    private CtxDBManager ctxDBManager;
    private Session      session;

    public CtxDBManagerThread() {
        this.ctxDBManager = CtxDBManager.getInstance();
        this.session = ctxDBManager.sf.openSession();
    }
    public int     getRows()              { return ctxDBManager.getRows(); }
    public Object  getBean( Class<?> cls) { return ctxDBManager.getBean(cls); }
    public Session getSession()           { return session; }
    public CtxBase getCtxBase()           { return (CtxBase) ctxDBManager; }
    @Override
    protected void finalize() {
        if (session != null) session.close();
        session = null;
    }
}
