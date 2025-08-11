package com.sdp.poc.threading.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "MT_MASTER")
public class Master {
    @Id
    @Column(name="ID")
    Long id;
    @Column(name="ENTERO")
    Integer entero;
    @Column(name="LARGO")
    Long largo;
    @Column(name="MONEDA")
    BigDecimal moneda;
    @Column(name="FECHA")
    Date fecha;
    @Column(name="TMS")
    Timestamp tms;
    @Column(name="FCHAR")
    String fchar;
    @Column(name="VCHAR")
    String vchar;
    @Column(name="LOGICO")
    Boolean logico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntero() {
        return entero;
    }

    public void setEntero(Integer entero) {
        this.entero = entero;
    }

    public Long getLargo() {
        return largo;
    }

    public void setLargo(Long largo) {
        this.largo = largo;
    }

    public BigDecimal getMoneda() {
        return moneda;
    }

    public void setMoneda(BigDecimal moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp getTms() {
        return tms;
    }

    public void setTms(Timestamp tms) {
        this.tms = tms;
    }

    public String getFchar() {
        return fchar;
    }

    public void setFchar(String fchar) {
        this.fchar = fchar;
    }

    public String getVchar() {
        return vchar;
    }

    public void setVchar(String vchar) {
        this.vchar = vchar;
    }

    public Boolean getLogico() {
        return logico;
    }

    public void setLogico(Boolean logico) {
        this.logico = logico;
    }
}

