/**
 * Simplemente pone los mensajes en la cola
 * si procede en funcion del tipo de mensaje y de su nivel
 */
package com.sdp.base.logging.loggers;

import com.sdp.base.logging.Logger;
import com.sdp.base.logging.codes.MSGCODE;
import com.sdp.base.logging.codes.OUT;
import com.sdp.base.logging.system.CtxLogging;
import com.sdp.sal.mask.RC;

import java.util.EmptyStackException;
import java.util.Stack;

public class QLoggerProd extends QLoggerBase implements Logger {
    CtxLogging ctx;
    // Calcula tiempos entre bloques
    Stack<Long>   tmsValue = new Stack<Long>();
    Stack<String> tmsLabel = new Stack<String>();
    int tgt = OUT.FILE;

    public QLoggerProd()                       { this(OUT.FILE, new CtxLogging()); }
    public QLoggerProd(Object ctx)             { this(OUT.FILE, ctx);           }
    public QLoggerProd(int target)             { this(target, new CtxLogging());   }
    public QLoggerProd(int target, Object ctx) {
        super();
        this.ctx = (CtxLogging) ctx;
        this.tgt = target;
        tmsValue.push(System.currentTimeMillis());
        tmsLabel.push("BEG");
    }

    // Timers (INFO)
    public void timer(String label) {
        tmsValue.push(System.currentTimeMillis());
        tmsLabel.push(label);
    }
    public void timer(String label, MSGCODE grupo, MSGCODE item, Object... args) {
        timer(label, 0, grupo, item, args);
    }
    public void timer(String label, int level, MSGCODE grupo, MSGCODE item, Object... args) {
        long beg = 0;
        try {
            while(tmsLabel.pop().compareTo(label) != 0) tmsValue.pop();
            beg = tmsValue.pop();
            if (level < ctx.infoLevel)
                writeMessage(2, String.format("TMR%02d%03d", grupo.getValue(), item.getValue())
                        ,System.currentTimeMillis() - beg, args);
        } catch (EmptyStackException ex) {}
    }

    // Info
    public void info (int level, MSGCODE group, MSGCODE code, Object... args) {
        info(level, String.format("NFO%02d%03d", group, code), args);
    }
    public void info (           MSGCODE group, MSGCODE code, Object... args) {
        info(0, String.format("NFO%02d%03d", group, code), args);
    }
    public void info (           String code, Object... args) {
        info(0,code,args);
    }
    public void info (int level, String code, Object... args) {
       if (level < ctx.infoLevel) writeMessage(code, args);
    }
    // Log
    public void log (int level, MSGCODE group, MSGCODE code, Object... args) {
        log(level, String.format("LOG%02d%03d", group, code), args);
    }
    public void log (           MSGCODE group, MSGCODE code, Object... args) {
        log(0, String.format("LOG%02d%03d", group, code), args);
    }
    public void log (           String code, Object... args) {
        log(0,code,args);
    }
    public void log (int level, String code, Object... args) {
        if (level < ctx.logLevel) writeMessage(code, args);
    }
    // Debug
    public void debug (int level, MSGCODE group, MSGCODE code, Object... args) {
        debug(level, String.format("DEB%02d%03d", group, code), args);
    }
    public void debug (           MSGCODE group, MSGCODE code, Object... args) {
        debug(0, String.format("DEB%02d%03d", group, code), args);
    }
    public void debug (           String code, Object... args) {
        debug(0,code,args);
    }
    public void debug (int level, String code, Object... args) {
        if (level < ctx.debugLevel) writeMessage(code, args);
    }
    // Warning
    public void warn (int level, MSGCODE group, MSGCODE code, Object... args) {
        warn(level, String.format("DEB%02d%03d", group, code), args);
    }
    public void warn (           MSGCODE group, MSGCODE code, Object... args) {
        warn(0, String.format("DEB%02d%03d", group, code), args);
    }
    public void warn (           String code, Object... args) {
        warn(0,code,args);
    }
    public void warn (int level, String code, Object... args) {
        //ctx.rc |= RC.WARNING;
        if (level < ctx.warningLevel) writeMessage(code, args);
    }

    private void writeMessage(String code, Object ... parms) {
        writeMessage(0,code, parms);
    }
    private void writeMessage(int stack, String code, Object ... parms) {
        int i;
        StringBuffer head = mountHeader(code, stack);
        StringBuffer buff = new StringBuffer();
        buff.append(head);
        if (parms.length > 0) {
            for (i = 0; i < parms.length; i++) {
                if (parms[i] instanceof Object[]) {
                    Object[] p2 = (Object[]) parms[i];
                    for (int j = 0; j < p2.length; j++) buff.append(p2[j]).append(';');
                } else {
                    buff.append(parms[i]).append(';');
                }
            }
            buff.deleteCharAt(buff.length() - 1);
        }

        try {
            qlog.put(buff.toString());
        } catch (Exception e) {
            // Do nothing
            System.err.println("Dev: check");
        }

    }

    /**
     * Monta la parte de cabecera del mensaje
     * @return el buffer con la cabecera
     */
    private StringBuffer mountHeader(String code, int n) {
        int idx = 0;
        String clsName = this.getClass().getSimpleName();
        StringBuffer buff = new StringBuffer(512);
        buff.append(ctx.getAppName());
        buff.append(":").append(System.currentTimeMillis() / 1000); // epoch
        buff.append(":").append(ctx.getPid());
        buff.append(':').append(Thread.currentThread().getName());
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        for (idx = 1; idx < stack.length; idx++) if (!stack[idx].getClassName().contains(clsName)) break;
        StackTraceElement item = stack[idx];
        buff.append(":").append(item.getClassName());
        buff.append(":").append(item.getMethodName());
        buff.append(":").append(item.getLineNumber());

        buff.append(":").append(code).append(':');
        return buff;
    }
}
