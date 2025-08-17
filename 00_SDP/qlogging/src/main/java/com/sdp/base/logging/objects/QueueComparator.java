package com.sdp.base.logging.objects;

import java.util.Comparator;

public class QueueComparator implements Comparator<QObject> {
    @Override
    public int compare(QObject o1, QObject o2) { return Long.compare(o1.id, o2.id); }
}