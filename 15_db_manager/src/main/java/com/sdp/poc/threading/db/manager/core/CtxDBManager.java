package com.sdp.poc.threading.db.manager.core;

import com.sdp.base.CtxBase;
import com.sdp.poc.threading.db.manager.config.ACTION;

public class CtxDBManager extends CtxBase {
    private int    rows   = 100;
    private ACTION action = ACTION.INIT;

    private CtxDBManager() {}

    private static class CtxDBManagerInner          { private static final CtxDBManager INSTANCE = new CtxDBManager(); }
    public  static CtxDBManager getInstance()       { return CtxDBManagerInner.INSTANCE; }

    public int    getRows()                { return rows;      }
    public void   setRows(int rows)        { this.rows = rows; }
    public void   setAction(String action) { this.action = ACTION.valueOf(action); }
    public void   setAction(ACTION action) { this.action = action;                 }
    public ACTION getAction()              { return action; }
}
