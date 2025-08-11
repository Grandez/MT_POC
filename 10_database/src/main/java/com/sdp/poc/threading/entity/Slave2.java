package com.sdp.poc.threading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "MT_SLAVE2")
public class Slave2 {
    @Id
    @Column(name="ID")
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
