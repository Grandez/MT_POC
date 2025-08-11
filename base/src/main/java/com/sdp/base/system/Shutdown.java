package com.sdp.base.system;

import com.sdp.base.logging.QLogger;

public class Shutdown {
    public static void setHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

                QLogger.stop();
            }
        }));

    }
}
