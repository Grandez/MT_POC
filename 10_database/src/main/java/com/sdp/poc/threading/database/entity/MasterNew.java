package com.sdp.poc.threading.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MT_MASTER_NEW")
public class MasterNew extends MasterBase {
    @Id
    @Column(name="ID")
    Long id;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

}
