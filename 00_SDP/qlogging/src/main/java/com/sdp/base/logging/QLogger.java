package com.sdp.base.logging;

import com.sdp.base.logging.loggers.QLoggerProd;
import com.sdp.base.logging.loggers.QLoggerThread;

public class QLogger {
    public static Logger getLogger()                       { return (Logger) new QLoggerProd();            }
    public static Logger getLogger(Object ctx)             { return (Logger) new QLoggerProd(ctx);         }
    public static Logger getLogger(int target)             { return (Logger) new QLoggerProd(target);      }
    public static Logger getLogger(int target, Object ctx) { return (Logger) new QLoggerProd(target, ctx); }

    public static void start() { QLoggerThread.start(); }
    public static void start(String app) { QLoggerThread.start(app); }
    public static void stop() { QLoggerThread.stop(); }
}
