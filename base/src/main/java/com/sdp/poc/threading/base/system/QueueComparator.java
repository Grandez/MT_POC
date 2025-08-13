package com.sdp.poc.threading.base.system;

import com.sdp.poc.threading.base.QObject;

import java.util.Comparator;

public class QueueComparator implements Comparator<QObject> {
    @Override
    public int compare(QObject o1, QObject o2) { return Long.compare(o2.id, o1.id); }
}