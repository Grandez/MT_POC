package com.sdp.poc.threading.db.manager.loader;

import com.sdp.poc.threading.base.system.Rand;
import com.sdp.poc.threading.database.entity.Master;
import com.sdp.poc.threading.db.manager.config.CtxDBManagerThread;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;

public class LoadMaster {
    CtxDBManagerThread ctx;
    Session session;
    String  chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public LoadMaster(CtxDBManagerThread ctx) {
       this.ctx = ctx;
       session = ctx.getSession();
    }
    public void load(Long id) {
        BigDecimal dec = new BigDecimal(100);
        Rand rnd  = new Rand();
        Rand rnd2 = new Rand(0,1);
        Rand rnd3 = new Rand(0,99);
        Rand letter = new Rand(0,25);

        Master master = new Master();
        master.setId(id);
        master.setEntero(rnd.next());
        master.setLargo(new Long(rnd.next()));
        master.setFecha(new Date());
        master.setLogico(rnd2.next() == 0 ? false : true);
        master.setTms(new Timestamp(System.currentTimeMillis()));
        master.setVchar("Registro " + id);

        long base = (rnd.next() * 100 + rnd3.next()) % 10000000000L;

        BigDecimal num = new BigDecimal(base);
        num = num.divide(dec, 2, RoundingMode.HALF_UP);
        master.setMoneda(num);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) str.append(chars.charAt(letter.next()));
        master.setFchar(str.toString());

        session.save(master);
    }
}
