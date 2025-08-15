package com.sdp.poc.threading.mtlatch.core;

import com.sdp.poc.threading.base.config.CtxBase;
import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.logging.CLogger;
import com.sdp.poc.threading.mtlatch.base.ThreadBase;
import com.sdp.poc.threading.mtlatch.interfaces.IMTConsumer;

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
