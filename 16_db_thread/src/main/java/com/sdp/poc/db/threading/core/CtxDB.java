package com.sdp.poc.db.threading.core;

import com.sdp.poc.threading.base.CtxBase;

public class CtxDB extends CtxBase {
    private CtxDB() {}

    private static class CtxDBInner { private static final CtxDB INSTANCE = new CtxDB(); }
    public  static CtxDB getInstance() { return CtxDBInner.INSTANCE; }

}
