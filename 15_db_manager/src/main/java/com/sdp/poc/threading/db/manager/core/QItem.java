package com.sdp.poc.threading.db.manager.core;

/**
 * Objeto que se encola
 */
public class QItem {
    int id;
    int master = 1;
    int slave1 = 0;
    int slave2 = 0;

    public QItem(int id, int slave1, int slave2) {
        this.id = id;
        this.slave1 = slave1;
        this.slave2 = slave2;
    }
    public int nextId() { return id + slave1 + slave2 + 1; }
}
