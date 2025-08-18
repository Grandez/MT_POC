package com.sdp.threading.mtlatch.core;

import com.sdp.base.config.CtxBase;
import com.sdp.base.logging.loggers.CLogger;
import com.sdp.base.logging.objects.QObject;
import com.sdp.threading.mtlatch.base.ThreadBase;
import com.sdp.threading.mtlatch.interfaces.IMTConsumer;

public class MTConsumer<Long> extends ThreadBase implements Runnable {

    IMTConsumer consumer;
    CtxBase ctx;
    int counter = 0;
    public MTConsumer(CtxBase ctx, IMTConsumer consumer) {
        super(ctx.getLatch());
        this.ctx = ctx;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        QObject msg;
        boolean rc = true;
        setThreadName("cons");
        CLogger.info("Iniciando hilo " + getNombre());
        try {
            while (true) {
                msg = ctx.getQueue().take();
                if (msg.isLastMessage()) break;
                counter++;
                consumer.consumir(msg);
            }
        } catch (InterruptedException e) {
            CLogger.info(" Interrumpido");
        }
        ctx.write(counter);
        ctx.getLatch().countDown();
    }
    protected void setThreadName () { setThreadName(this.getClass().getSimpleName()); }
    protected void setThreadName (String proceso) {
        String tname = Thread.currentThread().getName();
        String[] toks = tname.split("-");
        String name =  proceso + "-" + String.format("%03d", Integer.parseInt(toks[toks.length - 1]));
        Thread.currentThread().setName(name);
    }
}
