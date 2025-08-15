package com.sdp.poc.threading.base.parameters;

import com.sdp.poc.threading.base.logging.CLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;

public class Props extends Properties {
    public static Props load(String fileName) {
        if (fileName == null) return new Props();
        try {
            Props props = new Props();
            InputStream is = Props.class.getClassLoader().getResourceAsStream(fileName);
            if (is == null) throw new IOException("Fichero no encontrado");
            props.load(is);
            return props;
        } catch (IOException e) {
            CLogger.warning("Props: No se ha podido cargar el fichero de propiedades: " + fileName);
            CLogger.warning("Props: " + e.getLocalizedMessage());
        }
        return new Props();
    }
    public Integer getInteger(String key) { return getInteger(key, null); }
    public Integer getInteger(String key, Integer def) {
        String obj = (String) get(key);
        return (obj == null ? def : Integer.parseInt(obj));
    }

    /**
     *
     * @param key
     * @return false si no es cero o no empieza por n
     *         true en caso contrario
     */
    public Boolean getBoolean(String key) { return getBoolean(key, null); }
    public Boolean getBoolean(String key, Boolean def) {
        Object obj = get(key);
        if (obj == null) return (def == null) ? null : def;
        if (obj instanceof Integer) return ((Integer) obj) == 0 ? false : true;
        if (obj instanceof String)  {
            String str = (String) obj;
            if (str.length() == 0) return def;
            if (str.charAt(0) =='n') return false;
            if (str.charAt(0) =='N') return false;
            return true;
        }
        return def;
    }
    public String getString(String key) { return getString(key, null); }
    public String getString(String key, String def) {
        Object obj = get(key);
        if (obj == null) return (def == null) ? null : def;
        return (String) obj;
    }

}
