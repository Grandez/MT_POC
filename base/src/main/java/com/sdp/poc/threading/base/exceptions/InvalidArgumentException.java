package com.sdp.poc.threading.base.exceptions;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String fmt, Object... args) {
        super(String.format(fmt,args));
    }
}
