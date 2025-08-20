package com.sdp.base.parg.core.components;

import java.util.HashMap;

public class SubGroup {
    private static short[] grps = new short[Short.MAX_VALUE];
    private static HashMap<String, Parm> parms = new HashMap<>();

    public SubGroup add(Parm parm) {
        parms.put(parm.name, parm);
        return this;
    }
}
