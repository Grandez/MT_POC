package com.sdp.poc.threading.mtlatchdb.base;

import com.sdp.poc.threading.base.CtxBase;
import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.mtlatch.base.MainMT;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.mtlatchdb.config.CtxDB;

public abstract class MainMTDB extends MainMT {
    @Override
    protected abstract Props parseParms(String[] args);
    @Override
    protected abstract void loadConfig();
    @Override
    protected abstract void showHelp();
    protected  abstract void execute();
    protected Motor motor;
    public void run(String name, CtxDB ctx, String[] args) {
        try {
            super.appInit(name, ctx, args);
            motor = new Motor(ctx);
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
