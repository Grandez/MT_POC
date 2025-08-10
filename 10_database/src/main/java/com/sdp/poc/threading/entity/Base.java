package com.sdp.poc.threading.entity;

// Auto generado

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Enteros
    private Byte tinyInt;
    private Short smallInt;
    private Integer mediumInt;  // MediumInt no existe en Java, usamos Integer
    private Integer normalInt;
    private Long bigInt;

    // Decimales y flotantes
    @Column(precision = 10, scale = 2)
    private BigDecimal decimalNum;

    private Float floatNum;
    private Double doubleNum;

    // Fechas y horas
    private java.sql.Date fecha;
    private Time hora;
    private Timestamp fechaHora;
    private Timestamp timestampVal;

    // Cadenas
    @Column(length = 10)
    private String charVal;

    @Column(length = 50)
    private String varcharVal;

    @Lob
    private String textVal;

    @Lob
    private String tinytextVal;

    @Lob
    private String mediumtextVal;

    @Lob
    private String longtextVal;

    // Binarios
    @Column(length = 16)
    private byte[] binaryVal;

    @Column(length = 32)
    private byte[] varbinaryVal;

    @Lob
    private byte[] blobVal;

    @Lob
    private byte[] tinyblobVal;

    @Lob
    private byte[] mediumblobVal;

    @Lob
    private byte[] longblobVal;

    // Enum mapeado como String
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('valor1', 'valor2', 'valor3')")
    private EnumValor enumVal;

    // Set mapeado como String con valores separados por coma
    private String setVal;

    // Booleano
    private Boolean boolVal;

    // Enum para enum_val
    public enum EnumValor {
        valor1, valor2, valor3
    }

    // Getters y setters (solo algunos por ejemplo)

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Byte getTinyInt() { return tinyInt; }
    public void setTinyInt(Byte tinyInt) { this.tinyInt = tinyInt; }

    public Short getSmallInt() { return smallInt; }
    public void setSmallInt(Short smallInt) { this.smallInt = smallInt; }

    public Integer getMediumInt() { return mediumInt; }
    public void setMediumInt(Integer mediumInt) { this.mediumInt = mediumInt; }

    public Integer getNormalInt() { return normalInt; }
    public void setNormalInt(Integer normalInt) { this.normalInt = normalInt; }

    public Long getBigInt() { return bigInt; }
    public void setBigInt(Long bigInt) { this.bigInt = bigInt; }

    public BigDecimal getDecimalNum() { return decimalNum; }
    public void setDecimalNum(BigDecimal decimalNum) { this.decimalNum = decimalNum; }

    public Float getFloatNum() { return floatNum; }
    public void setFloatNum(Float floatNum) { this.floatNum = floatNum; }

    public Double getDoubleNum() { return doubleNum; }
    public void setDoubleNum(Double doubleNum) { this.doubleNum = doubleNum; }

    public java.sql.Date getFecha() { return fecha; }
    public void setFecha(java.sql.Date fecha) { this.fecha = fecha; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp fechaHora) { this.fechaHora = fechaHora; }

    public Timestamp getTimestampVal() { return timestampVal; }
    public void setTimestampVal(Timestamp timestampVal) { this.timestampVal = timestampVal; }

    public String getCharVal() { return charVal; }
    public void setCharVal(String charVal) { this.charVal = charVal; }

    public String getVarcharVal() { return varcharVal; }
    public void setVarcharVal(String varcharVal) { this.varcharVal = varcharVal; }

    public String getTextVal() { return textVal; }
    public void setTextVal(String textVal) { this.textVal = textVal; }

    public String getTinytextVal() { return tinytextVal; }
    public void setTinytextVal(String tinytextVal) { this.tinytextVal = tinytextVal; }

    public String getMediumtextVal() { return mediumtextVal; }
    public void setMediumtextVal(String mediumtextVal) { this.mediumtextVal = mediumtextVal; }

    public String getLongtextVal() { return longtextVal; }
    public void setLongtextVal(String longtextVal) { this.longtextVal = longtextVal; }

    public byte[] getBinaryVal() { return binaryVal; }
    public void setBinaryVal(byte[] binaryVal) { this.binaryVal = binaryVal; }

    public byte[] getVarbinaryVal() { return varbinaryVal; }
    public void setVarbinaryVal(byte[] varbinaryVal) { this.varbinaryVal = varbinaryVal; }

    public byte[] getBlobVal() { return blobVal; }
    public void setBlobVal(byte[] blobVal) { this.blobVal = blobVal; }

    public byte[] getTinyblobVal() { return tinyblobVal; }
    public void setTinyblobVal(byte[] tinyblobVal) { this.tinyblobVal = tinyblobVal; }

    public byte[] getMediumblobVal() { return mediumblobVal; }
    public void setMediumblobVal(byte[] mediumblobVal) { this.mediumblobVal = mediumblobVal; }

    public byte[] getLongblobVal() { return longblobVal; }
    public void setLongblobVal(byte[] longblobVal) { this.longblobVal = longblobVal; }

    public EnumValor getEnumVal() { return enumVal; }
    public void setEnumVal(EnumValor enumVal) { this.enumVal = enumVal; }

    public String getSetVal() { return setVal; }
    public void setSetVal(String setVal) { this.setVal = setVal; }

    public Boolean getBoolVal() { return boolVal; }
    public void setBoolVal(Boolean boolVal) { this.boolVal = boolVal; }
}

