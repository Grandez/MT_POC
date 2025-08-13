package com.sdp.poc.threading.mtlatch.base;

import com.sdp.poc.threading.base.CtxBase;
import com.sdp.poc.threading.base.MainBase;
import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.mtlatch.core.Motor;

public abstract class MainMT extends MainBase {
    @Override
    protected abstract Props parseParms(String[] args);
    @Override
    protected abstract void loadConfig();
    @Override
    protected abstract void showHelp();
    protected  abstract void execute();
    protected  Motor motor;
    public void run(String name, CtxBase ctx, String[] args) {
        try {
            appInit(name, ctx, args);
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
