package com.sdp.threading.mtlatch.base;

import com.sdp.base.MainBase;
import com.sdp.base.config.CtxBase;
import com.sdp.threading.mtlatch.core.Motor;
import com.sdp.sal.mask.RC;

public abstract class MainMT extends MainBase {
    protected  abstract void execute();
    protected  Motor motor;
    
    public void run(String name, CtxBase ctx, String[] args) {
        runInternal(name,ctx,args,"application");
    }
    private void runInternal(String name, CtxBase ctx, String[] args, String fName) {
        try {
            appInit(name, ctx, args);
            motor = new Motor(ctx, fName);
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
