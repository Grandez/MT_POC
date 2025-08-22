package com.sdp.base.parg.core;

import com.sdp.base.parg.core.components.Parg;
import com.sdp.base.parg.core.components.PrmBlock;

import java.util.List;

public class Container {
    private PrmBlock[] blocks = new PrmBlock[Short.MAX_VALUE];
    public Container(List<Parg> parms) {
        for (Parg parg : parms) {
            if (parg.getName() == null) continue;
            int idx = parg.getBlock();
            if (blocks[idx] == null) blocks[idx] = new PrmBlock();
            blocks[idx].add(parg);
        }
    }
    public PrmBlock get(int block) {
        if (blocks[block] == null) blocks[block] = new PrmBlock();
        return blocks[block];
    }
    public String getValue(int block, int group,int subgroup, String key) {
        return get(block).get(group).get(subgroup).get(key);
    }

}
