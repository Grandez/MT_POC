package com.sdp.poc.threading.db.manager.prodcons;

import com.sdp.base.logging.objects.QObject;
import com.sdp.poc.threading.database.dao.Slave1Dao;
import com.sdp.poc.threading.database.dao.Slave2Dao;
import com.sdp.poc.threading.db.manager.config.CtxDBManagerThread;
import com.sdp.poc.threading.db.manager.core.QItem;
import com.sdp.poc.threading.database.dao.MasterDao;
import com.sdp.sal.system.Rand;
import com.sdp.threading.mtlatch.interfaces.IMTProducer;
import org.hibernate.Session;

public class Producer implements IMTProducer {
    CtxDBManagerThread ctx;
    Session session;
    long    id;
    Rand rnd;

    long rows    = 100000;
    long current =      0;

    public Producer() {
        ctx = new CtxDBManagerThread();
        rows = ctx.getRows() * 1000;
        rnd = new Rand(0,5);
        session = ctx.getSession();
        id = getLastId();
    }
    @Override
    public QObject producir() {
        if (++current > rows) return null;
        QItem item = new QItem(id, rnd.next(), rnd.next());
        id = item.nextId();
        return new QObject(current, item);
    }
    private long getLastId() {
        long id;
        Object dao = ctx.getBean(MasterDao.class);
        long id1 = ((MasterDao) dao).getLastId(session);
        dao = ctx.getBean(Slave1Dao.class);
        long id2 = ((Slave1Dao) dao).getLastId(session);
        dao = ctx.getBean(Slave2Dao.class);
        long id3 = ((Slave2Dao) dao).getLastId(session);
        id = id1 > id2 ? id1 : id2;
        if (id3 > id) id = id3;
        return ++id;
    }
}
