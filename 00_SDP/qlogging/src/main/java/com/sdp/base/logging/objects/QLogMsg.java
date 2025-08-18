package com.sdp.base.logging.objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class QLogMsg {
    public String app;
    public Long tms;
    public Long pid;
    public String thread;
    public String msg;
    public String data;
    public StackTraceElement source;

    public String  getApp()    { return app; }
    public Long    getTms()    { return tms; }
    public Long    getPid()    { return pid; }
    public String  getThread() { return thread; }
    public String  getData()   { return data;   }
    public String  getMsg()    { return msg;    }

    public void setApp(String app) { this.app = app; }
    public void setTms(Long tms)   { this.tms = tms; }
    public void setPid(Long pid)   { this.pid = pid; }
    public void setThread(String thread) { this.thread = thread; }
    public void setMsg(String msg)       { this.msg = msg;       }
    public void setData(String data)     { this.data = data;     }

    public StackTraceElement getSource() {return source; }
    public void              setSource(StackTraceElement source) { this.source = source; }
}
