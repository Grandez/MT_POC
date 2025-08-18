package com.sdp.base.logging.codes;

public class MSG {
    public static String APP = "APP";
    public static String TMR = "TMR";

    public static String msg(String msg, int block, int type) {
        return String.format("%s%02d%03d", msg, block, type);
    }
}
