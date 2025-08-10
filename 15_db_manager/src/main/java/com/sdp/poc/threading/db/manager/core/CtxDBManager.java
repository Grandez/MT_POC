package com.sdp.poc.threading.db.manager.core;

import com.sdp.poc.threading.base.CtxBase;

public class CtxDBManager extends CtxBase {
    private CtxDBManager() {}

    private static class CtxDBManagerInner          { private static final CtxDBManager INSTANCE = new CtxDBManager(); }
    public  static CtxDBManager getInstance() { return CtxDBManagerInner.INSTANCE; }

}
