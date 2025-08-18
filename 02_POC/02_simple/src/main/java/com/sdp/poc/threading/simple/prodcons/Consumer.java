package com.sdp.poc.threading.simple.prodcons;

import com.sdp.base.logging.objects.QObject;
import com.sdp.base.otros.Operacion;
import com.sdp.poc.threading.simple.core.CtxSimple;
import com.sdp.sal.system.Rand;
import com.sdp.threading.mtlatch.interfaces.IMTConsumer;

public class Consumer implements IMTConsumer {
    CtxSimple ctx;

    public Consumer() {
        ctx = CtxSimple.getInstance();
    }

    @Override
    public void consumir(QObject msg) {
        Operacion op = new Operacion();
        Rand r = new Rand(1,100);
        op.sumar(r.next(), r.next());
        op.multiplicar(r.next(), r.next());
        ctx.write();
    }
}
