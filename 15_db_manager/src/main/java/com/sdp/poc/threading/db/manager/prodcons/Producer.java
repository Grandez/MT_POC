package com.sdp.poc.threading.db.manager.prodcons;

import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.system.Rand;
import com.sdp.poc.threading.db.manager.core.CtxDBManager;
import com.sdp.poc.threading.db.manager.core.QItem;
import com.sdp.poc.threading.mtlatch.interfaces.IMTProducer;

public class Producer implements IMTProducer {
    CtxDBManager ctx;
    long rows    = 100000;
    long current =      0;
    int id;
    Rand rnd;

    public Producer() {
        ctx = CtxDBManager.getInstance();
        rows = ctx.getRows() * 1000;
        rnd = new Rand(0,5);
        id = 1;
    }
    @Override
    public QObject producir() {
        if (++current == rows) return null;
        QItem item = new QItem(id, rnd.next(), rnd.next());
        id = item.nextId();
        return new QObject(current, item);
    }
}
