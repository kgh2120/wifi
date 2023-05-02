package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.dto.BookmarkCreateRequest;
import com.kk.wifi.dto.BookmarkRetrieveResponse;
import com.kk.wifi.utils.LocalDateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {

    private static final BookmarkDao instance = new BookmarkDao();

    public static BookmarkDao getInstance(){
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

    public BookmarkRetrieveResponse retrieveSingleBookmark(int id){
        final String sql = "select bookmark_group.name as group_name, bookmark.id as id, wifi.X_SWIFI_MAIN_NM as wifi_name, "
                + " bookmark.created_at as created_at, bookmark.wifi_id as wifi_id, distance.history_id as history_id  from bookmark "
                + "join bookmark_group on bookmark_group.id = bookmark.group_id "
                + "join distance on distance.id = bookmark.distance_id and distance.wifi_id = bookmark.wifi_id "
                + "join wifi on wifi.X_SWIFI_MGR_NO = distance.wifi_id "
                + "where bookmark.id = ?; ";

        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            BookmarkRetrieveResponse response = new BookmarkRetrieveResponse();
            while(rs.next()){
                response.setId(rs.getInt("id"));
                response.setHistory_id(rs.getInt("history_id"));
                response.setWifi_id(rs.getString("wifi_id"));
                response.setWifi_name(rs.getString("wifi_name"));
                response.setGroup_name(rs.getString("group_name"));
                response.setCreated_at(rs.getString("created_at"));
            }
            return response;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<BookmarkRetrieveResponse> retrieveBookmarks(){
        final String sql = "select bookmark_group.name as group_name, bookmark.id as id, wifi.X_SWIFI_MAIN_NM as wifi_name, "
                + " bookmark.created_at as created_at, bookmark.wifi_id as wifi_id, distance.history_id as history_id  from bookmark "
                + "join bookmark_group on bookmark_group.id = bookmark.group_id "
                + "join distance on distance.id = bookmark.distance_id and distance.wifi_id = bookmark.wifi_id "
                + "join wifi on wifi.X_SWIFI_MGR_NO = distance.wifi_id ";


        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            List<BookmarkRetrieveResponse> list = new ArrayList<>();
            while(rs.next()){
                BookmarkRetrieveResponse response = new BookmarkRetrieveResponse();
                response.setId(rs.getInt("id"));
                response.setHistory_id(rs.getInt("history_id"));
                response.setWifi_id(rs.getString("wifi_id"));
                response.setWifi_name(rs.getString("wifi_name"));
                response.setGroup_name(rs.getString("group_name"));
                response.setCreated_at(rs.getString("created_at"));
                list.add(response);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void createBookmark(BookmarkCreateRequest bookmarkCreateRequest){
        final String sql = "insert into bookmark (group_id, distance_id, wifi_id, created_at) values (?, ?, ?, ?)";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, bookmarkCreateRequest.getBookmarkGroupId());
            st.setInt(2, bookmarkCreateRequest.getDistanceId());
            st.setString(3, bookmarkCreateRequest.getWifiId());
            st.setString(4, LocalDateTimeFormatter.formatNow());
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBookmark(int id){
        final String sql = "delete from bookmark where id = ?";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }




}
