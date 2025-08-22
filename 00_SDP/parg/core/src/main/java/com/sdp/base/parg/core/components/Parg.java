package com.sdp.base.parg.core.components;
/**
 * Entidad de SOLO LECTURA
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name="VAL")
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
    public Long    getId()    { return id;    }

    public void setBlock(Integer block) { this.block = block; }
    public void setGroup(Integer group) { this.group = group; }
    public void setItems(Integer items) { this.items = items; }
    public void setName(String name)    { this.name = name;   }
    public void setValue(String value)  { this.value = value; }
    public void setType(Integer type)   { this.type = type;   }
    public void setTtl(Long ttl)        { this.ttl = ttl;     }
    public void setId(Long id)          { this.id = id;       }
}
