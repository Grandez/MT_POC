package com.sdp.base.parg.core.components;

public class PrmBlock {
    private PrmGroup[] blocks = new PrmGroup[Short.MAX_VALUE];

    public PrmBlock add(Parg parm) {
        int idx = parm.getGroup();
        if (blocks[idx] == null) blocks[idx] = new PrmGroup();
        blocks[idx].add(parm);
        return this;
    }
    public PrmGroup get(int block) {
        if (blocks[block] == null) blocks[block] = new PrmGroup();
        return blocks[block];
    }

}
