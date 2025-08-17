package com.sdp.base.logging.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG")
public class Log {
    @Column(name="APP")
    private String app;
    @Column(name="TMS")
    private Long tms;
    @Column(name="PID")
    private Long pid;
    @Column(name="THREAD")
    private String thread;
    @Column(name="CLASS")
    private String cls;
    @Column(name="METHOD")
    private String method;
    @Column(name="LINE")
    private Integer line;
    @Column(name="MSG")
    private String msg;
    @Column(name="TYPE")
    private Integer type;
    @Column(name="BLOCK")
    private Integer block;
    @Column(name="CODE")
    private Integer code;
    @Column(name="DATA")
    private String data;
    @Id
    @Column(name="UUID")
    private String uuid;

    public String  getApp()     { return app;    }
    public Long    getTms()     { return tms;    }
    public Long    getPid()     { return pid;    }
    public String  getThread()  { return thread; }
    public String  getCls()     { return cls;    }
    public String  getMethod()  { return method; }
    public Integer getLine()    { return line;   }
    public String  getMsg()     { return msg;    }
    public Integer getType()    { return type;   }
    public Integer getBlock()   { return block;  }
    public Integer getCode()    { return code;   }
    public String  getData()    { return data;   }
    public String  getUuid()    { return uuid;   }

    public void setApp   (String app)    { this.app = app; }
    public void setTms   (Long tms)      { this.tms = tms; }
    public void setPid   (Long pid)      { this.pid = pid; }
    public void setThread(String thread) {
        this.thread = thread;
    }
    public void setCls   (String cls)    { this.cls = cls;       }
    public void setMethod(String method) {
        this.method = method;
    }
    public void setLine  (Integer line)  { this.line = line;    }
    public void setMsg   (String msg)    { this.msg = msg;      }
    public void setType  (Integer type)  { this.type = type;    }
    public void setBlock (Integer block) { this.block = block;  }
    public void setCode  (Integer code)  { this.code = code;    }
    public void setData  (String data)   { this.data = data;    }
    public void setUuid  (String uuid)   { this.uuid = uuid;    }
}