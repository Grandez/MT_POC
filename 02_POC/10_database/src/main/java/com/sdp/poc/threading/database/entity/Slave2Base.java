package com.sdp.poc.threading.database.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class Slave2Base {
    @Column(name="ID_PARENT")
    private Long idParent;
    @Column(name="PATH")
    private String path;
    @Column(name="FICHERO")
    private String fichero;
    @Column(name="LARGO")
    private Integer largo;
    @Column(name="FECHA")
    private Timestamp fecha;
    @Column(name="UUID")
    private String uuid;
    @Column(name="TMS")
    private Timestamp tms;

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public Integer getLargo() {
        return largo;
    }

    public void setLargo(Integer largo) {
        this.largo = largo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getTms() {
        return tms;
    }

    public void setTms(Timestamp tms) {
        this.tms = tms;
    }
}
