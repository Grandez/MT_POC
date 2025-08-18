package com.sdp.base.logging.loggers;

import com.sdp.base.logging.config.CtxQLog;
import com.sdp.base.logging.objects.QObject;

public class QLoggerThread extends QLoggerBase {
    private static Thread thrLog = null;

    public static void start(String app) {
        thrLog = new Thread(new QLoggerCons(app));
        thrLog.start();
    }
    public static void start() {
        thrLog = new Thread(new QLoggerCons("NONAME"));
        thrLog.start();
    }
    public static void stop() {
        // Esto se llama desde diferentes sitios
        // Controlar que ya este hecho
        if (thrLog == null) return;
        try {
            qlog.put(new QObject(Long.MAX_VALUE));
            thrLog.join();
        } catch (InterruptedException e) {
            System.err.println("DEV: QLog InterruptedException");
        } finally {
            thrLog = null;
        }
    }
    public static QLoggerProd getLogger(CtxQLog ctx) { return new QLoggerProd(ctx); }
    public static QLoggerProd getLogger()            { return new QLoggerProd();    }
}
