package com.sdp.poc.threading.db.manager.core;

import com.sdp.base.CtxBase;

public class CtxDBManager extends CtxBase {
    private int rows = 100;

    private CtxDBManager() {}

    private static class CtxDBManagerInner          { private static final CtxDBManager INSTANCE = new CtxDBManager(); }
    public  static CtxDBManager getInstance() { return CtxDBManagerInner.INSTANCE; }

    public int  getRows()         { return rows;      }
    public void setRows(int rows) { this.rows = rows; }
}
