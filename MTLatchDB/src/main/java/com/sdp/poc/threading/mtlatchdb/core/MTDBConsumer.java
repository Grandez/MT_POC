package com.sdp.poc.threading.mtlatchdb.core;

import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.logging.*;
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
    Logger        logger;

    public MTDBConsumer(CtxDB ctx, IMTDBConsumer consumer) {
        super(ctx.getLatch());
        this.ctx      = ctx;
        this.consumer = consumer;
        this.logger   = QLogger.getQLogger(ctx);
    }

    @Override
    public void run() {
        setThreadName("cons");
        logger.timer(getNombre());
        logger.timer("tx");

        Session session = consumer.getSession();
        QObject msg;
        boolean rc = true;
        int     chunk = ctx.getChunk();

        CLogger.info("Iniciando hilo " + getNombre());
        Transaction tx = session.beginTransaction();
        while (true) {
            try {
                msg = ctx.getQueue().take();
                if (msg.isLastMessage()) break;
                counter++;
                ctx.write();
                consumer.consumir(msg);
                if (counter % chunk == 0) {
                    logger.timer("tx", MSGCODE.SQL, MSGCODE.TX, new Integer(chunk));
                    tx.commit();
                    tx = session.beginTransaction();
                    logger.timer("tx");
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
            logger.timer("tx", 4, MSGCODE.SQL, MSGCODE.TX, getNombre(), chunk);
        } catch (Exception ex) {
            // Si da error el commit se ignora
        }

        ctx.getLatch().countDown();
        logger.timer(getNombre(), 3, MSGCODE.TMR,MSGCODE.THR, getNombre());
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
