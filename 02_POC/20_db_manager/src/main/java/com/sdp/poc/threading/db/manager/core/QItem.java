package com.sdp.poc.threading.db.manager.core;

/**
 * Objeto que se encola
 */
public class QItem {
    long id;
    int master = 1;
    int slave1 = 0;
    int slave2 = 0;

    public QItem(long id, int slave1, int slave2) {
        this.id = id;
        this.slave1 = slave1;
        this.slave2 = slave2;
    }

    public long getId()     { return id; }
    public int  getMaster() { return master; }
    public int  getSlave1() { return slave1; }
    public int  getSlave2() { return slave2; }

    public long nextId() { return id + slave1 + slave2 + 1; }
}
