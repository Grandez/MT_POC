package com.sdp.poc.threading.base.logging;

public interface Logger {
    void timer(String label);
    void timer(String label,            MSGCODE grupo, MSGCODE item, Object... args);
    void timer(String label, int level, MSGCODE grupo, MSGCODE item, Object... args);

    void info (int level, MSGCODE grupo, MSGCODE item, Object... args);
    void info (int level, String msgcode, Object... args);
    void info (           MSGCODE grupo, MSGCODE item, Object... args);
    void info (           String msgcode, Object... args);

}
