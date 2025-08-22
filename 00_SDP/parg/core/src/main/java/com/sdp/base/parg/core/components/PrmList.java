package com.sdp.base.parg.core.components;

import java.util.HashMap;

public class PrmList {
    HashMap<String, Object> parms = new HashMap<>();
    public void addParm(Parameter prm) { add(prm.getName(), prm); }

    private void add(String key, Parameter prm) {
        String toks[] = key.split("\\.");
        if (toks.length == 1) {
            parms.put(toks[0], prm);
        } else {
            
        }
    }
}
