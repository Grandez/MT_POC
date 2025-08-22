package com.sdp.base.parg.core.components;

public class PrmGroup {
    private PrmSubGroup[] blocks = new PrmSubGroup[Short.MAX_VALUE];
    public PrmGroup add(Parg parm) {
        int idx = parm.getItems();
        if (blocks[idx] == null) blocks[idx] = new PrmSubGroup();
        blocks[idx].add(parm);
        return this;
    }
    public PrmSubGroup get(int block) {
        if (blocks[block] == null) blocks[block] = new PrmSubGroup();
        return blocks[block];
    }

}


