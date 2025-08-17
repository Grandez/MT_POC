package com.sdp.base.logging.loggers;

import com.sdp.base.logging.objects.*;

import java.util.concurrent.PriorityBlockingQueue;

public abstract class QLoggerBase {
    protected final String SEP_TOK = ":";
    public static PriorityBlockingQueue<String> qlog = new PriorityBlockingQueue<>();
}
