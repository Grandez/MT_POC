package com.sdp.base.logging.interfaces;

public interface App {
    long getBeg();
    int getRC();
    long getInput();
    long getOutput();
    long getErrors();
    int getNumThreads();
    int getChunk();
    int getTimeout();
}
