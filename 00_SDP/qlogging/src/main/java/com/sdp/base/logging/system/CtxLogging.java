package com.sdp.base.logging.system;

public class CtxLogging {
    public int logLevel     = 9;
    public int infoLevel    = 9;
    public int debugLevel   = 9;
    public int warningLevel = 9;

    private String appName = "NONAME";
    private Long   pid;

    public int getLogLevel()     { return logLevel;     }
    public int getInfoLevel()    { return infoLevel;    }
    public int getDebugLevel()   { return debugLevel;   }
    public int getWarningLevel() { return warningLevel; }

    public void setLogLevel    (int level) { this.logLevel     = level; }
    public void setInfoLevel   (int level) { this.infoLevel    = level; }
    public void setDebugLevel  (int level) { this.debugLevel   = level; }
    public void setWarningLevel(int level) { this.warningLevel = level; }

    public String getAppName()  { return appName; }
    public long   getPid()      { return pid;     }
}
