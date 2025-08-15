package com.sdp.poc.threading.base.system;

import com.sdp.poc.threading.base.logging.QLoggerThread;

public class Shutdown {
    public static void setHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

                QLoggerThread.stop();
            }
        }));

    }
}
