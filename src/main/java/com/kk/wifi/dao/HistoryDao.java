package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class HistoryDao {

    private final static HistoryDao instance = new HistoryDao();
    public static HistoryDao getInstance(){
        return instance;
    }

    private Connection conn;

    public void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
    }

    public void saveHistory(String lat, String lnt){
        final String now = LocalDateTime.now().toString();
        String sql = "insert into history(lat, lnt, created_at) values(?, ?, ?) ";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, lat);
            pstm.setString(2, lnt);
            pstm.setString(3, now);

            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
