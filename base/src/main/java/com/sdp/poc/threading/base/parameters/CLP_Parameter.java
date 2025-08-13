package com.sdp.poc.threading.base.parameters;

import java.util.Comparator;

public class CLP_Parameter implements Comparator<String> {
    String parm;
    String name;
    String value;
    CLP_TYPE type;

    public CLP_Parameter(String parm, String name, CLP_TYPE type) {
        this.parm = parm;
        this.name = name;
        this.type = type;
    }
    public CLP_Parameter(String parm, CLP_TYPE type) { this(parm, parm, type);          }
    public CLP_Parameter(String parm, String name)   { this(parm, name, CLP_TYPE.BOOL); }
    public CLP_Parameter(String parm)                { this(parm, parm, CLP_TYPE.BOOL); }

    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
