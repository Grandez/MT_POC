package com.sdp.base.parg.client;

import com.sdp.base.parg.client.db.dao.PargDao;
import org.springframework.beans.factory.annotation.Autowired;

public class Parms {
    private static Parms parms;

    @Autowired
    PargDao dao;

    public static Parms getInstance() {
        if (parms == null) parms = new Parms();
        return parms;
    }
}
