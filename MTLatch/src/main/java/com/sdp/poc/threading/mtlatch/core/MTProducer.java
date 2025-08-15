package com.sdp.poc.threading.mtlatch.core;

import com.sdp.poc.threading.base.config.CtxBase;
import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.logging.CLogger;
import com.sdp.poc.threading.mtlatch.base.ThreadBase;
import com.sdp.poc.threading.mtlatch.interfaces.IMTProducer;

import java.util.concurrent.PriorityBlockingQueue;

public class MTProducer<T> extends ThreadBase implements Runnable {
    IMTProducer producer;
    CtxBase ctx;

    public MTProducer(CtxBase ctx, IMTProducer producer) {
        super();
        this.producer = producer;
        this.ctx = ctx;
    }

    public void run() {
        QObject msg;
        setThreadName("Prod");
        CLogger.info("Iniciando hilo " + getNombre());
        PriorityBlockingQueue<QObject> queue = ctx.getQueue();
        msg = producer.producir();
        while (msg != null) {
           ctx.read();
           queue.put(msg);
           msg = producer.producir();
        }
        // Notificar que acaben
        for (long l = 0; l < ctx.getNumThreads(); l++) ctx.getQueue().put(new QObject(Long.MAX_VALUE));
        ctx.getLatch().countDown();
    }

}
