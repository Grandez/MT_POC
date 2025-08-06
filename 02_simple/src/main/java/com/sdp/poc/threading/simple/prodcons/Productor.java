package com.sdp.poc.threading.simple.prodcons;

import com.sdp.poc.threading.simple.core.CtxSimple;
import com.sdp.poc.threading.mtlatch.interfaces.IMTProducer;

public class Productor implements IMTProducer {
    Long last = 0L;
    int max   = 1000;
    CtxSimple ctx;
    
    public Productor() {
        ctx = CtxSimple.getInstance();
        max = ctx.getItems() * 1000;
    }
    @Override
    public Long producir() {
        if (++last > max) return null;
        ctx.read();
        return last;
    }
}
