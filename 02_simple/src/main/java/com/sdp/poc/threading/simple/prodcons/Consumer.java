package com.sdp.poc.threading.simple.prodcons;

import com.sdp.poc.threading.base.otros.Operacion;
import com.sdp.poc.threading.base.system.Rand;
import com.sdp.poc.threading.mtlatch.interfaces.IMTConsumer;
import com.sdp.poc.threading.simple.core.CtxSimple;

public class Consumer implements IMTConsumer {
    CtxSimple ctx;

    public Consumer() {
        ctx = CtxSimple.getInstance();
    }

    public void consumir(long msg) {
        Operacion op = new Operacion();
        Rand r = new Rand(1,100);
        op.sumar(r.next(), r.next());
        op.multiplicar(r.next(), r.next());
        ctx.write();
    }
}
