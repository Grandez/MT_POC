package com.sdp.base.parg.client.config;

import com.sdp.base.parg.client.db.entity.Parg;
import com.sdp.base.parg.core.base.FIELD;

import java.sql.*;

public class CtxParg {
    String url = "jdbc:mariadb://localhost:3306/SDP";
    String user = "LOG";
    String password = "log";

    public CtxParg()     { this("DEF"); }
    public CtxParg(String app) {
        Parg parg = null;
        try (
           Connection conn = DriverManager.getConnection(url, user, password);
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM PARG");
           ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
               parg = createEntity(rs);
           }

//            while (rs.next()) {
//                User u = new User(
//                        rs.getLong("id"),
//                        rs.getString("nombre"),
//                        rs.getString("email")
//                );
//                users.add(u);
//            }
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Parg createEntity(ResultSet rs) {
        Parg parm = new Parg();
        try {
            parm.setBlock(rs.getInt(FIELD.BLOCK));
            parm.setGroup(rs.getInt(FIELD.GROUP));
            parm.setName(rs.getString(FIELD.NAME));
            parm.setValue(rs.getString(FIELD.VALUE));
            parm.setType(rs.getInt(FIELD.TYPE));
            parm.setTtl(rs.getLong(FIELD.TTL));
        } catch (SQLException ex) {
           System.err.println("Error creando entity");
        }
        return parm;
    }

}
/*
public class AppMain {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/miapp";
        String user = "root";
        String password = "secret";

        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement("SELECT id, nombre, email FROM users");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        users.forEach(System.out::println);
    }
}

*/