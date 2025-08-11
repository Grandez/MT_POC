package com.sdp.base.logging;

public enum MSGCODE {
     NONE(0)
    ,SQL(10)
    ,SELECT (1)
    ,UPDATE(2)
    ,DELETE(3)
    ,CURSOR(4)
    ,tx(5)
    ;

    private final int value;

    MSGCODE(int value) { this.value = value; }

    public int getValue() { return value; }

}
