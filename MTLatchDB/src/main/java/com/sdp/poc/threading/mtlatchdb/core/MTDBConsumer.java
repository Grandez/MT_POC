package com.sdp.poc.threading.mtlatchdb.core;

import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.logging.CLogger;
import com.sdp.poc.threading.mtlatch.base.ThreadBase;
import com.sdp.poc.threading.mtlatchdb.config.CtxDB;
import com.sdp.poc.threading.mtlatchdb.interfaces.IMTDBConsumer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MTDBConsumer extends ThreadBase implements Runnable {

    IMTDBConsumer consumer;
    CtxDB         ctx;
    Session       session;
    long          counter;

    public MTDBConsumer(CtxDB ctx, IMTDBConsumer consumer) {
        super(ctx.getLatch());
        this.ctx = ctx;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        Session session = consumer.getSession();
        QObject msg;
        boolean rc = true;
        int     chunk = ctx.getChunk();
        setThreadName("cons");
        CLogger.info("Iniciando hilo " + getNombre());
        Transaction tx = session.beginTransaction();

        while (true) {
            try {
                msg = ctx.getQueue().take();
                if (msg.isLastMessage()) {
                    System.out.println("Fin de mensajes");
                    break;
                }
                counter++;
                ctx.write();
                consumer.consumir(msg);
                if (counter % chunk == 0) {
                    tx.commit();
                    tx = session.beginTransaction();
                }
            } catch (InterruptedException e) {
                CLogger.info(" Interrumpido");
                break;
            } catch (Exception e) {
                CLogger.info(" Excepcion");
                CLogger.info(e.getLocalizedMessage());
                tx.rollback();
                tx = session.beginTransaction();
            }
        }
        try {
            if (tx.isActive()) tx.commit();
        } catch (Exception ex) {
            // Si da error el commit se ignora
        }

        ctx.getLatch().countDown();
    }
    protected void setThreadName () { setThreadName(this.getClass().getSimpleName()); }
    protected void setThreadName (String proceso) {
        String tname = Thread.currentThread().getName();
        String[] toks = tname.split("-");
        String name =  proceso + "-" + String.format("%03d", Integer.parseInt(toks[toks.length - 1]));
        Thread.currentThread().setName(name);
    }
    @Override
    protected void finalize() throws Throwable {
        if (session != null) session.close();
    }

}
