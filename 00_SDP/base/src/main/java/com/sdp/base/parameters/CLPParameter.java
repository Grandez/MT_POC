package com.sdp.base.parameters;

import java.util.Comparator;

public class CLPParameter implements Comparator<String> {
    String parm;
    String name;
    String value;
    CLPType type;

    public CLPParameter(String parm, String name, CLPType type) {
        this.parm = parm;
        this.name = name;
        this.type = type;
    }
    public CLPParameter(String parm, CLPType type) { this(parm, parm, type);          }
    public CLPParameter(String parm, String name)   { this(parm, name, CLPType.BOOL); }
    public CLPParameter(String parm)                { this(parm, parm, CLPType.BOOL); }

    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
