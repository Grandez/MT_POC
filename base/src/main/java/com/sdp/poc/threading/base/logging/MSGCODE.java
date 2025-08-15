package com.sdp.poc.threading.base.logging;

public enum MSGCODE {
     NONE(0)
    ,APP(0)
    ,MOD(1)
    ,THR(2)
    ,METHOD(5)
    ,TMR(1)
    ,SQL(10)
    ,SELECT (1)
    ,UPDATE(2)
    ,DELETE(3)
    ,CURSOR(4)
    ,TX(5)
    ;

    private final int value;

    MSGCODE(int value) { this.value = value; }

    public int getValue() { return value; }

}
