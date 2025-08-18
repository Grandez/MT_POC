package com.sdp.poc.threading.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "MT_MASTER")
public class Master extends MasterBase {
    @Id
    @Column(name="ID")
    Long id;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
}

