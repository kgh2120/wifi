package com.kk.wifi.dao;

import com.kk.wifi.config.DBConnection;
import com.kk.wifi.vo.WifiVO;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WifiDao {

    private final static WifiDao instance = new WifiDao();
    public static WifiDao getInstance(){
        return instance;
    }

    private Connection conn;

    public void init() {
        conn = DBConnection.getConnection();
    }

    public void end() {
        DBConnection.closeConnection();
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
        System.out.println("LIST.size() => " + vo.size());

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

//        System.out.println("----Complete!!-----");
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

}
