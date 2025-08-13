package com.sdp.poc.threading.base;

/**
 * Objecto que se pasa a traves de colas en un entorno multihilo
 * Simplemente es un wrapper para envolver los datos
 * id maneja la prioridad
 */
public class QObject {
    public Long id;
    public Object data = null;
    public QObject(Long id)              { this.id = id; }
    public QObject(Long id, Object data) { this.id = id; this.data = data; }
    public boolean isLastMessage() {
        return (id < 0 || id == java.lang.Long.MAX_VALUE) ? true : false;
    }

}
