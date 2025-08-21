package com.sdp.poc.threading.db.manager.prodcons;

import com.sdp.base.logging.interfaces.Logger;
import com.sdp.base.logging.objects.QObject;
import com.sdp.poc.threading.db.manager.config.CtxDBManagerThread;
import com.sdp.poc.threading.db.manager.core.QItem;
import com.sdp.poc.threading.db.manager.loader.LoadMaster;
import org.hibernate.Session;

public class Consumer implements IMTDBConsumer {
    CtxDBManagerThread ctx;
    LoadMaster         master;
    Logger logger;
    public Consumer() {
        ctx    = new CtxDBManagerThread();
        master = new LoadMaster(ctx);
        logger =  QLogger.getQLogger(ctx.getCtxBase());
    }

    public void consumir(QObject msg) {
        // System.out.println(Thread.currentThread().getName() + " - Recibe " + msg.id);
        QItem item = (QItem) msg.data;
        master.load(item.getId());
    }

    @Override
    public Session getSession() { return ctx.getSession(); }
}
