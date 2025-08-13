package com.sdp.poc.threading.base;

import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.base.system.PID;
import com.sdp.poc.threading.base.system.QueueComparator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;

public class CtxBase { // implements ICABase {
    private String appName = "NONAME";
    public  int    rc  = 0;

    private Long    pid;
    private Long    beg;

    // Contadores
    private Long    in  = 0L;
    private Long    out = 0L;
    private Long    err = 0L;

    // Multihilo
    private int threads = 1;
    private int timeout = 0;
    private int chunk   = 1;

    // Logging
    private int logLevel   = 0;
    private int infoLevel  = 0;
    private int debugLevel = 0;

    private static PriorityBlockingQueue<QObject> qdat = new PriorityBlockingQueue<>(100, new QueueComparator());
    private static CountDownLatch latch;

    // Parte Configuraciones
    protected Props props;
    protected Props customProps = new Props();

    private static class ctxBaseInner { private static final CtxBase INSTANCE = new CtxBase(); }
    public  static CtxBase getInstance() { return ctxBaseInner.INSTANCE; }


    public String getAppName()  { return appName; }
    public int    getRC()       { return rc;      }
    public long   getPid()      { return pid;     }
    public long   getBeg()      { return beg;     }
    public long   getInput()    { return in;      }
    public long   getOutput()   { return out;     }
    public long   getErrors()   { return err;     }
    public void   setRC(int rc) { this.rc = rc;   }

    public void setNumThreads(int threads) { this.threads = threads; }
    public void sethreads    (int threads) { this.threads = threads; }
    public void setTimeout   (int timeout) { this.timeout = timeout; }
    public void setChunk     (int chunk)   { this.chunk   = chunk;   }

    public int getNumThreads() { return threads; }
    public int getThreads()    { return threads; }
    public int getTimeout   () { return timeout; }
    public int getChunk     () { return chunk;   }

    public void setAppName (String appName) { this.appName = appName; }

    public CtxBase() {
        this.pid = PID.getpid();
        this.beg = System.currentTimeMillis();
    }

    // Metodos para los contadores
    public synchronized void read()        { in++;       }
    public synchronized void write()       { out++;      }
    public synchronized void err()         { err++;      }
    public synchronized void read (long v) { in  += v;   }
    public synchronized void write(long v) { out += v;   }
    public synchronized void err  (long v) { err += v;   }

    public long getRead ()       { return in;  }
    public long getWrite()       { return out; }
    public long getErr  ()       { return err; }

    public void  setCommandLine(Props props) { this.customProps = props; }
    public Props getCommandLine()            { return customProps; }

    public PriorityBlockingQueue<QObject> getQueue() { return qdat; }
    public CountDownLatch              getLatch() { return latch; }
    public void                        setLatch(CountDownLatch latch) { this.latch = latch; }

    public int getLogLevel()   { return logLevel;   }
    public int getInfoLevel()  { return infoLevel;  }
    public int getDebugLevel() { return debugLevel; }

    public void setLogLevel(int logLevel)     { this.logLevel = logLevel;     }
    public void setInfoLevel(int infoLevel)   { this.infoLevel = infoLevel;   }
    public void setDebugLevel(int debugLevel) { this.debugLevel = debugLevel; }
}
