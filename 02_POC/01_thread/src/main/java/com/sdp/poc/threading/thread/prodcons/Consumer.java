package com.sdp.poc.threading.thread.prodcons;

import com.sdp.base.config.CtxBase;
import com.sdp.base.otros.Operacion;
import com.sdp.sal.system.Rand;

public class Consumer implements Runnable {
    CtxBase ctx;

    public Consumer(CtxBase ctx) { this.ctx = ctx; }

    @Override
    public void run() {
        Operacion op = new Operacion();
        Rand r = new Rand(1,100);
        op.sumar(r.next(), r.next());
        op.multiplicar(r.next(), r.next());
        ctx.write();
        ctx.getLatch().countDown();
    }
}
