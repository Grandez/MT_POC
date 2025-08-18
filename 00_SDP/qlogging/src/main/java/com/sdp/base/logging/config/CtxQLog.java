package com.sdp.base.logging.config;

import com.sdp.sal.system.PID;

public class CtxQLog {
    private static CtxQLog INSTANCE = null;

    // qlog.general = ALL
    public int logLevel     = 9;
    public int infoLevel    = 9;
    public int warningLevel = 9;
    public int debugLevel   = 9;
    public int traceLevel   = 9;

    private static String appName = "NONAME";
    private static Long   pid;

    public CtxQLog()               { this.pid = PID.getpid(); }
    public CtxQLog(String appName) { this.pid = PID.getpid(); this.appName = appName; }

    public  static CtxQLog getInstance()   {
        if (INSTANCE == null) INSTANCE = new CtxQLog();
        return INSTANCE;
    }

    public int getLogLevel()     { return logLevel;     }
    public int getInfoLevel()    { return infoLevel;    }
    public int getDebugLevel()   { return debugLevel;   }
    public int getTraceLevel()   { return traceLevel;   }
    public int getWarningLevel() { return warningLevel; }

    public void setLogLevel    (int level) { this.logLevel     = level; }
    public void setInfoLevel   (int level) { this.infoLevel    = level; }
    public void setDebugLevel  (int level) { this.debugLevel   = level; }
    public void setTraceLevel  (int level) { this.traceLevel   = level; }
    public void setWarningLevel(int level) { this.warningLevel = level; }

    public String getAppName()  { return appName; }
    public void   setAppName(String appName )  { this.appName = appName; }
    public long   getPid()      { return pid;     }

}
