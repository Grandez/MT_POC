package com.sdp.poc.threading.database.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class MasterBase {
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

