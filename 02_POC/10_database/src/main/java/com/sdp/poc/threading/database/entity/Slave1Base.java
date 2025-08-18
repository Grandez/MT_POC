package com.sdp.poc.threading.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class Slave1Base {
    @Column(name="ID_PARENT")
    private Long idParent;
    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="AP1")
    private String ap1;
    @Column(name="AP2")
    private String ap2;
    @Column(name="TFNO")
    private Integer tfno;
    @Column(name="NIF")
    private String nif;
    @Column(name="MAIL")
    private String mail;
    @Column(name="CP")
    private Integer cp;
    @Column(name="TMS")
    private Timestamp tms;

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public Integer getTfno() {
        return tfno;
    }

    public void setTfno(Integer tfno) {
        this.tfno = tfno;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Timestamp getTms() {
        return tms;
    }

    public void setTms(Timestamp tms) {
        this.tms = tms;
    }
}
