package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.dto.HistoryResponse;
import com.kk.wifi.utils.LocalDateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    private static final  HistoryDao instance = new HistoryDao();

    static {
        init();
    }
    public static HistoryDao getInstance(){

        return instance;
    }

    private static Connection conn;

    public static void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
    }

    public int saveHistory(String lat, String lnt){
        int id = -1;
        final String now = LocalDateTimeFormatter.formatNow();
        String sql = "insert into history(lat, lnt, created_at) values(?, ?, ?) ";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, lat);
            pstm.setString(2, lnt);
            pstm.setString(3, now);

            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);

        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }


    public List<HistoryResponse> retrieveHistory(){
        final String sql = "select * from history ";
        List<HistoryResponse> list = new ArrayList<>();
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String lat = rs.getString("LAT");
                String lnt = rs.getString("LNT");
                String createdAt = rs.getString("created_at");
                HistoryResponse response = new HistoryResponse(id, lat, lnt, createdAt);
                System.out.println(response);
                list.add(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void deleteHistory(int id) {
        final String sql = "delete from history where id = ? ";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
