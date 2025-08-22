package com.sdp.poc.parg.client.config;

import com.sdp.base.parg.client.db.entity.Parg;
import com.sdp.base.parg.core.Container;
import com.sdp.base.parg.core.base.FIELD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CtxParg {
    private static CtxParg INSTANCE;
    private String app;
    private Container container;

    String url = "jdbc:mariadb://localhost:3306/SDP";
    String user = "LOG";
    String password = "log";

    public CtxParg() { this("DEF"); }
    public CtxParg(String app) {
        this.app = app;

        List<Parg> parms = loadConfiguration();
//        container = new Container(parms);
    }
    public  static CtxParg getInstance() {
        return getInstance("DEF");
    }
    public  static CtxParg getInstance(String app) {
        if (INSTANCE == null) INSTANCE = new CtxParg(app);
        return INSTANCE;
    }
    private List<Parg> loadConfiguration() {
        List<Parg> parms = new ArrayList<>();
        try (
           Connection conn = DriverManager.getConnection(url, user, password);
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM PARG");
           ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                parms.add(createEntity(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parms;
    }

    private Parg createEntity(ResultSet rs) {
        Parg parm = new Parg();
        parm.setName(null);
        try {
            parm.setBlock(rs.getInt(FIELD.BLOCK));
            parm.setGroup(rs.getInt(FIELD.GROUP));
            parm.setItems(rs.getInt(FIELD.SUBGROUP));
            parm.setName(rs.getString(FIELD.NAME));
            parm.setValue(rs.getString(FIELD.VALUE));
            parm.setTtl(rs.getLong(FIELD.TTL));
        } catch (SQLException ex) {

        }

        return parm;

    }
}
