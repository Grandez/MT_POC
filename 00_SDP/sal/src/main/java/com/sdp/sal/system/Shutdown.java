package com.sdp.sal.system;

public class Shutdown {
    public static void setHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

//JGG                QLoggerThread.stop();
            }
        }));

    }
}
