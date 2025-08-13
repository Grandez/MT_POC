package com.sdp.poc.threading.mtlatchdb.config;

import com.sdp.poc.threading.base.CtxBase;
// import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CtxDB extends CtxBase {
//    static AnnotationConfigApplicationContext ctx = null;

//    private CtxDB() {}

    private static class CtxDBInner          { private static final CtxDB INSTANCE = new CtxDB(); }
    public  static CtxDB getInstance() { return CtxDBInner.INSTANCE; }

//    public AnnotationConfigApplicationContext getSpringCtx() { return ctx; }
}
