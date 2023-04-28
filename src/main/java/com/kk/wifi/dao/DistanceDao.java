package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.dto.HistoryResponse;
import com.kk.wifi.dto.NearWifiResponse;
import com.kk.wifi.vo.DistanceVO;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DistanceDao {

    private static final  DistanceDao instance = new DistanceDao();

    static {
        init();
    }
    public static DistanceDao getInstance(){

        return instance;
    }

    private static Connection conn;

    public static void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
    }

    public void save(List<DistanceVO> distances){
        try{
            String insertSql = "insert into distance (wifi_id, history_id, distance) "
                    + "values (? , ?, ?)";
            PreparedStatement st = null;
            st = conn.prepareStatement(insertSql);
            for (DistanceVO distance : distances) {
                st.setString(1, distance.getWifiId());
                st.setInt(2,distance.getHistoryId());
                st.setDouble(3,distance.getDistance());
                st.addBatch();
            }
            st.executeBatch();
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<NearWifiResponse> getNearWifi(int historyId){
        List<NearWifiResponse> list = new LinkedList<>();
        try{
            String selectSql = "select * from distance left join wifi on wifi.X_SWIFI_MGR_NO = distance.wifi_id where distance.history_id = ? "
                    + "order by distance.distance";
            PreparedStatement st = null;
            st = conn.prepareStatement(selectSql);
            st.setInt(1,historyId);

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NearWifiResponse response = new NearWifiResponse();
                Field[] fields = response.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if("distance".equals(field.getName())){
                        field.set(response,rs.getDouble(field.getName()));
                        continue;
                    }
                    field.set(response,rs.getString(field.getName()));
                }
                list.add(response);
            }
        }catch (Exception e){
            e.printStackTrace();

        }

        return list;

    }

}
