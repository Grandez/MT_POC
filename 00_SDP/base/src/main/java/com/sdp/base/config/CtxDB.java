package com.sdp.base.config;

import com.sdp.base.parg.client.config.CtxParg;

public class CtxDB extends CtxBase {
    private static CtxDB ctx;
    private        CtxParg parg;

    protected CtxDB(String app) {
        super();
        parg = new CtxParg(app);
    }
    public  static CtxDB getInstance() {
        return getInstance("DEF");
    }
    public  static CtxDB getInstance(String app) {
        if (ctx == null) ctx = new CtxDB(app);
        return ctx;
    }
//    private void loadConfiguration() {
//        try (
//           Connection conn = DriverManager.getConnection(url, user, password);
//           PreparedStatement ps = conn.prepareStatement("SELECT * FROM PARG");
//            ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                while (rs.next()) {
//                    createEntity(rs);
//                }
//
//                String parm = rs.getString("VAL");
//                System.out.println(parm);
//                parm = rs.getString("val");
//                System.out.println(parm);
//
///*
//
//                @Table(name = "PARG")
//                public class Parg {
//                    @Column(name="BLOCK")
//                    Integer  block;
//                    @Column(name="GRP")
//                    Integer  group;
//                    @Column(name="SUBGRP")
//                    Integer  items;
//                    @Column(name="NAME")
//                    String   name;
//                    @Column(name="VAL")
//                    String   value;
//                    @Column(name="TYPE")
//                    Integer  type;
//                    @Column(name="TTL")
//                    Long     ttl;
//                    @Id
//                    @Column(name="ID")
//                    Long     id;
//*/
//                rs.toString();
//            }
//
////            while (rs.next()) {
////                User u = new User(
////                        rs.getLong("id"),
////                        rs.getString("nombre"),
////                        rs.getString("email")
////                );
////                users.add(u);
////            }
////
//            rs.first();
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private Parg createEntity(ResultSet rs) {
//        Parg parm = new Parg();
//        try {
//            parm.setBlock(rs.getInt(FIELD.BLOCK));
//        } catch (SQLException ex) {
//
//        }
//
//        return parm;
//
//    }

}

