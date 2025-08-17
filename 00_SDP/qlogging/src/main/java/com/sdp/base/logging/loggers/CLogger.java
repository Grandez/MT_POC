package com.sdp.base.logging.loggers;

import com.sdp.sal.ctes.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CLogger {
    public static void error(String msg, Object ... args) {
        System.err.println(Color.RED_BOLD.getColor() + String.format(msg, args) + Color.RESET.getColor());
    }
    public static void debug(String msg) {
        print(msg);
    }
    public static void warning(String msg, Object ...args) {
        print(Color.BLUE_BOLD, String.format(msg, args));
    }
    public static void info(String msg) {
        print(Color.BOLD, msg);
    }

    private static void print(Color color, String msg) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - ");
        String strdate = dateFormat.format(new Date()).toString();
        System.out.println(strdate + color.getColor() + msg + Color.RESET.getColor());
    }
    private static void print(String msg) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - ");
        String strdate = dateFormat.format(new Date()).toString();
        System.out.println(strdate + msg);
    }

}
