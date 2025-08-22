package com.sdp.base.parg.core.components;

import java.util.HashMap;

public class PrmSubGroup {
    private static PrmList[] grps = new PrmList[Short.MAX_VALUE];
    private static HashMap<String, Parg> parms = new HashMap<>();

    public PrmSubGroup add(Parg parm) {
        parms.put(parm.name, parm);
        return this;
    }
    public String get(String key) {
        Parg obj = parms.get(key);
        return (obj == null) ? null : obj.getValue();
    }
}
