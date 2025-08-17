package com.sdp.base.logging.loggers;

import com.sdp.base.logging.objects.QObject;

import java.util.concurrent.PriorityBlockingQueue;

public abstract class QLoggerBase {
    protected final String SEP_TOK = ":";
    public static PriorityBlockingQueue<QObject> qdat = new PriorityBlockingQueue<>(100, new QueueComparator());
}
