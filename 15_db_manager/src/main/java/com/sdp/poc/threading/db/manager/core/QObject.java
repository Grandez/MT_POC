package com.sdp.poc.threading.db.manager.core;

/**
 * Objeto que se encola
 */
public class QObject {
    int id;
    int master = 1;
    int slave1;
    int slave2;

    public QObject(int id, int slave1, int slave2) {
        this.id = id;
        this.slave1 = slave1;
        this.slave2 = slave2;
    }
}
