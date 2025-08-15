package com.sdp.poc.threading.mtlatchdb.base;

import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.reflection.ClassScanner;
import com.sdp.poc.threading.mtlatch.base.MainMT;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.mtlatchdb.config.CtxDB;
import com.sdp.poc.threading.mtlatchdb.core.MotorDB;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import java.util.Set;

public abstract class MainDB extends MainMT {
    protected Motor motor;
    public void run(String name, CtxDB ctx, String[] args) {
        try {
            super.appInit(name, ctx, args);
            motor = new MotorDB(ctx);
            execute();
        } catch (SecurityException se) {
            ctx.rc |= RC.INTERRUPTED;
            System.err.println("Control-c pulsado");
        } catch (Exception se) {
            ctx.rc |= RC.CRITICAL;
            System.err.println(se.getLocalizedMessage());
        } finally {
            appEnd();
        }
    }
}
