package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.dto.WIfiDetailResponse;
import com.kk.wifi.dto.WifiLocationDto;
import com.kk.wifi.vo.WifiVO;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {

    private final static WifiDao instance = new WifiDao();
    public static WifiDao getInstance(){
        return instance;
    }
    static{
        init();
    }

    private static Connection conn;

    public static void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
    }

    public WIfiDetailResponse getWifiDetailInfo(int historyId, String wifiId){

        final String sql = "select * from wifi left join distance on wifi.X_SWIFI_MGR_NO = distance.wifi_id "
                + "where distance.history_id = ? and distance.wifi_id = ? ";
        PreparedStatement pstm;
        try{
            WIfiDetailResponse response = new WIfiDetailResponse();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, historyId);
            pstm.setString(2, wifiId);
            ResultSet rs = pstm.executeQuery();
            Field[] fields = response.getClass().getDeclaredFields();
            while(rs.next()){
                for (Field field : fields) {
                    field.setAccessible(true);
                    if("distance".equals(field.getName())){
                        field.set(response,rs.getDouble(field.getName()));
                        continue;
                    }
                    if("distanceId".equals(field.getName())){
                        field.set(response,rs.getInt("id"));
                        continue;
                    }
                    field.set(response,rs.getString(field.getName()));
                }
            }
            return response;
        }catch (Exception e){
            e.printStackTrace();

            return null;
        }finally {
//            if()pstm.close();
        }

    }

    public int countWifis(){
        int count = 0;
        try {
            String countSql = "select count(*) from wifi";
            PreparedStatement pstm = conn.prepareStatement(countSql);
            ResultSet rs = pstm.executeQuery();
            count = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }


    public void insertWifis(List<WifiVO> vo) {


        int count = 0;
        int batchLimit = 100;
        try {
            String insertSql = "insert into wifi (" + getWifiFieldsName() + ") "
                    + "values " + getInsertFieldQuestionMark(16);
            PreparedStatement st = null;
            st = conn.prepareStatement(insertSql);
            for (WifiVO wifiVO : vo) {

                insertFieldDataIntoPrepareStatement(st, wifiVO);
                st.addBatch();
                count++;
                if(count % 100 == 0)
                    st.executeBatch();
            }
            st.executeBatch();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertFieldDataIntoPrepareStatement(PreparedStatement st, WifiVO vo)
            throws IllegalAccessException, SQLException {
        Field[] fields = vo.getClass().getDeclaredFields();
        int idx = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            st.setString(idx++, (String) field.get(vo));
        }
    }

    private String getWifiFieldsName() {
        Class<WifiVO> wifiVOClass = WifiVO.class;
        Field[] fields = wifiVOClass.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {

            sb.append(field.getName()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String getInsertFieldQuestionMark(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < n - 1; i++) {
            sb.append("?, ");
        }
        sb.append("? )");
        return sb.toString();
    }

    public List<WifiLocationDto> getLocation() {

        final String selectSql = "select X_SWIFI_MGR_NO, LAT, LNT from wifi";
        List<WifiLocationDto> list = new ArrayList<>();
        try{
            PreparedStatement pstm = conn.prepareStatement(selectSql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String id = rs.getString("X_SWIFI_MGR_NO");
                String lnt = rs.getString("LAT");
                String lat = rs.getString("LNT");
                WifiLocationDto response = new WifiLocationDto(id, lat, lnt);
                list.add(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
