package com.sdp.poc.threading.base.logging;

import com.sdp.poc.threading.base.config.CtxBase;

public class QLogger {
    public static Logger getQLogger()            { return new QLoggerProd();    }
    public static Logger getQLogger(Object ctx)  { return new QLoggerProd(ctx); }
}
