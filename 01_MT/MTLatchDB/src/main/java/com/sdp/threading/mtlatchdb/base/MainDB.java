package com.sdp.threading.mtlatchdb.base;

import com.sdp.sal.mask.RC;
import com.sdp.threading.mtlatch.base.MainMT;
import com.sdp.threading.mtlatch.core.Motor;
import com.sdp.threading.mtlatchdb.config.CtxDB;
import com.sdp.threading.mtlatchdb.core.MotorDB;

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
