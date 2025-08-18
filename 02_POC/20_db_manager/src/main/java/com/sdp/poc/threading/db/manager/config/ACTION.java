package com.sdp.poc.threading.db.manager.config;

public enum ACTION {
    INIT ("init"), RESET("reset"), LOAD("load");

    private final String value;

    ACTION(String value) { this.value = value; }

    public String getValue() { return value; }

}
