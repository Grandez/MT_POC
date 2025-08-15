package com.sdp.poc.threading.db.manager.config;

import com.sdp.poc.threading.mtlatchdb.config.CtxDB;
import com.sdp.poc.threading.mtlatchdb.config.MTLatchDBConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CtxDBManager extends CtxDB {
    private static CtxDBManager INSTANCE = null;
    private int    rows   = 1;
    private ACTION action = ACTION.INIT;

    static AnnotationConfigApplicationContext ctx = null;

    private CtxDBManager(String fileProps, Class<?> configClass) { super(fileProps, configClass); }

    public  static CtxDBManager getInstance()                     { return getInstance("application"); }
    public  static CtxDBManager getInstance(String fileProps)     { return getInstance(fileProps, MTLatchDBConfig.class); }
    public  static CtxDBManager getInstance(Class<?> configClass) { return getInstance("application", configClass); }

    public  static CtxDBManager getInstance(String fileProps, Class<?> configClass)   {
        if (INSTANCE == null) INSTANCE = new CtxDBManager(fileProps, configClass);
        return INSTANCE;
    }

    public int    getRows()                { return rows;      }
    public ACTION getAction()              { return action;    }

    public void   setRows(int rows)        { this.rows = rows; }
    public void   setAction(String action) { this.action = ACTION.valueOf(action); }
    public void   setAction(ACTION action) { this.action = action;                 }
    public AnnotationConfigApplicationContext getEmf() { return ctx; }
}
