package com.sdp.base.parg.client.db.entity;
/**
 * Entidad de SOLO LECTURA
 */

import javax.persistence.*;

@Entity
@Table(name = "PARG")
public class Parg {
    @Column(name="BLOCK")
    Integer  block;
    @Column(name="GRP")
    Integer  group;
    @Column(name="SUBGRP")
    Integer  items;
    @Column(name="NAME")
    String   name;
    @Column(name="VALUE")
    String   value;
    @Column(name="TYPE")
    Integer  type;
    @Column(name="TTL")
    Long     ttl;
    @Id
    @Column(name="ID")
    Long     id;

    public Integer getBlock() { return block; }
    public Integer getGroup() { return group; }
    public Integer getItems() { return items; }
    public String  getName()  { return name;  }
    public String  getValue() { return value; }
    public Integer getType()  { return type;  }
    public Long    getTtl()   { return ttl;   }

}
