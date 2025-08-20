package com.sdp.base.parg.core.base;

public class TYPE {
    private static int TEXT       = 0x02;
    private static int NUMBER     = 0x04;
    private static int DT         = 0x08;
    private static int BOOL       = 0x10;

    public static int STRING     = 0X001 << 8 + TEXT;
    public static int CHAR       = 0X002 << 8 + TEXT;

    public static int BYTE       = 0X001 << 8 + NUMBER;
    public static int SHORT      = 0X002 << 8 + NUMBER;
    public static int INT        = 0X004 << 8 + NUMBER;
    public static int LONG       = 0X008 << 8 + NUMBER;
    public static int FLOAT      = 0X010 << 8 + NUMBER;
    public static int DOUBLE     = 0X020 << 8 + NUMBER;
    public static int DECIMAL    = 0X040 << 8 + NUMBER;
    public static int BIGINT     = 0X080 << 8 + NUMBER;
    public static int BIGLONG    = 0X100 << 8 + NUMBER;
    public static int BIGDECIMAL = 0X200 << 8 + NUMBER;

    public static int DATE       = 0X001 << 8 + DT;
    public static int TIME       = 0X002 << 8 + DT;
    public static int DATETIME   = 0X004 << 8 + DT;
    public static int LOCAL      = 0X008 << 8 + DT;
    public static int TMS        = 0X010 << 8 + DT;

    public static int BOOLEAN    = 0X001 << 8 + BOOL;


}
