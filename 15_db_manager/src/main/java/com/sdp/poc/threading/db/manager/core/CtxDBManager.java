package com.sdp.poc.threading.db.manager.core;

import com.sdp.poc.threading.base.CtxBase;
import com.sdp.poc.threading.db.manager.config.ACTION;
import com.sdp.poc.threading.db.manager.config.HibernateConfig;
import com.sdp.poc.threading.mtlatchdb.config.CtxDB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CtxDBManager extends CtxBase {
    private int    rows   = 100;
    private ACTION action = ACTION.INIT;

    static AnnotationConfigApplicationContext ctx = null;

    private CtxDBManager() {
        if (ctx == null) ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
    }

    private static class CtxDBManagerInner          { private static final CtxDBManager INSTANCE = new CtxDBManager(); }
    public  static CtxDBManager getInstance()       { return CtxDBManagerInner.INSTANCE; }

    public int    getRows()                { return rows;      }
    public ACTION getAction()              { return action;    }

    public void   setRows(int rows)        { this.rows = rows; }
    public void   setAction(String action) { this.action = ACTION.valueOf(action); }
    public void   setAction(ACTION action) { this.action = action;                 }
    public AnnotationConfigApplicationContext getEmf() { return ctx; }
}
