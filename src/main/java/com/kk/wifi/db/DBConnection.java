package com.kk.wifi.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn = null;

    public static void closeConnection(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        if (conn == null) {
            try {
               String dbFile = "C:\\Users\\kgh21\\OneDrive\\Desktop\\제로베이스\\과제\\wifi\\wifi.db";

                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
