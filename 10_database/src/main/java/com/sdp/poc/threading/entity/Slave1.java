package com.sdp.poc.threading.entity;

import javax.persistence.Column;

public class Slave1 extends Base {
    @Column(name="idParent")
    private Integer idParent;

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }
}
