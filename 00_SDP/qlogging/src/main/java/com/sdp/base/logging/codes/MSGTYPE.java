package com.sdp.base.logging.codes;

public enum MSGTYPE {
     MSG(0x04)
    ,ERR(0x08)
    ,INFO(0x10)
    ,WAR(0x20)
    ,LOG(0x40)
    ,DEBUG(0x80)
    ;
    private final int value;

    MSGTYPE(int value) { this.value = value; }

    public int getValue() { return value; }

}
