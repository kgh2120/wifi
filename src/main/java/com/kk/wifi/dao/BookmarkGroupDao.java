package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.dto.BookmarkGroupListResponse;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDao {

    private static final BookmarkGroupDao instance = new BookmarkGroupDao();

    public static BookmarkGroupDao getInstance(){
        return instance;
    }
    static {
        init();
    }
    private static Connection conn;

    public static void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
    }

    public void createBookmarkGroup(String name, int order){
        final String sql = "insert into bookmark_group (name, orders, created_at) values (?, ?, ?)";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, order);
            st.setString(3, LocalDateTime.now().toString());
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<BookmarkGroupListResponse> retrieveBookmarkGroupList(){
        final String sql = "select * from bookmark_group order by orders";
        PreparedStatement st = null;
        List<BookmarkGroupListResponse> list = new ArrayList<>();
        try{
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                BookmarkGroupListResponse response = new BookmarkGroupListResponse();
                response.setId(rs.getInt("id"));
                response.setName(rs.getString("name"));
                response.setOrders(rs.getInt("orders"));
                response.setCreated_at(rs.getString("created_at"));
                response.setModified_at(rs.getString("modified_at"));
                list.add(response);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void deleteBookmarkGroup(int id){
        final String sql = "delete from bookmark_group where id = ? ";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();;
        }
    }

    public void updateBookmarkGroup(int id, String name, int orders){
        final String sql = "update bookmark_group set name =?, orders =?, modified_at = ? where id = ?";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, orders);
            st.setString(3, LocalDateTime.now().toString());
            st.setInt(4, id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
